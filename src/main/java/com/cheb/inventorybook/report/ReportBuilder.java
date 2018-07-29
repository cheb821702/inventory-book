package com.cheb.inventorybook.report;

import com.cheb.inventorybook.report.dto.ReportBox;
import com.cheb.inventorybook.report.dto.ReportBoxTableDto;
import com.cheb.inventorybook.report.dto.ReportItem;
import com.cheb.inventorybook.report.template.ReportTemplateProvider;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRow;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReportBuilder {

    private static Pattern ptrn = Pattern.compile("\\$\\{\\w+}");
    private static Map<String,Map<String,Method>> varIndex = new HashMap<String, Map<String, Method>>();

    private ReportTemplateProvider reportTemplateProvider;
    private ReportBoxTableDto reportBoxTableDto;
    private String languageIso;

    public void createReport(OutputStream outputStream) {
        try {
            XWPFDocument doc = null;
            if(reportTemplateProvider != null && languageIso !=null) {
                doc= new XWPFDocument(reportTemplateProvider.getReportTemplateInputStream(languageIso));
            }
            if(reportBoxTableDto != null) {
                prepareIndex(reportBoxTableDto.getClass(),null);
                fillBoxTable(doc, reportBoxTableDto.getBoxes());
            }
            doc.write(outputStream);
        } catch (Exception e) {
            throw new RuntimeException("Can't create box list report.", e);
        }
    }

    public void setReportTemplateProvider(ReportTemplateProvider reportTemplateProvider) {
        this.reportTemplateProvider = reportTemplateProvider;
    }

    public void setLanguageIso(String languageIso) {
        this.languageIso = languageIso;
    }

    public void setReportBoxTableDto(ReportBoxTableDto reportBoxTableDto) {
        this.reportBoxTableDto = reportBoxTableDto;
    }

    private void prepareIndex(Class clazz, String prefix) {
        if(!varIndex.containsKey(clazz.getName())) {
            Map<String, Method> mapping = new HashMap<String, Method>();
            for (Method m : clazz.getMethods()) {
                if (m.isAnnotationPresent(ReportVariable.class)) {
                    String var = m.getAnnotation(ReportVariable.class).value();
                    if (Collection.class.isAssignableFrom(m.getReturnType())) {
                        m.getGenericReturnType();
                        Class cl = ((Class) ((ParameterizedType) m.getGenericReturnType()).getActualTypeArguments()[0]);
                        if (prefix != null) {
                            prepareIndex(cl, prefix + "_" + var);
                        } else {
                            prepareIndex(cl, var);
                        }

                    } else {
                        StringBuilder varBuilder = new StringBuilder("${");
                        if (prefix != null)
                            varBuilder.append(prefix).append("_");
                        varBuilder.append(var).append("}");
                        mapping.put(varBuilder.toString(), m);
                    }
                }
            }
            varIndex.put(clazz.getName(), mapping);
        }
    }

    private Map<String,Object> createData(Object entity) throws InvocationTargetException, IllegalAccessException {
        Map<String,Object> res = new HashMap<String, Object>();
        String clName = entity.getClass().getName();
        if(varIndex.containsKey(clName)) {
            for(Map.Entry<String,Method> map : varIndex.get(clName).entrySet()) {
                res.put(map.getKey(),map.getValue().invoke(entity));
            }
        }
        return res;
    }


    private void fillBoxTable(XWPFDocument doc, List<ReportBox> boxes) throws IOException, XmlException, InvocationTargetException, IllegalAccessException {
        XWPFTable table = doc.getTables().get(0);

        XWPFTableRow boxHeaderRow = table.getRow(2);
        XWPFTableRow boxBodyRow = table.getRow(3);
        XWPFTableRow boxFooterRow = table.getRow(4);

        int i = 5;
        for(ReportBox box : boxes) {

            Map<String,Object> boxData = createData(box);
            //create box header row
            XWPFTableRow  newRow = createRow(boxHeaderRow,table,boxData);
            table.addRow(newRow, i);
            i++;

            for(ReportItem item : box.getItems()) {
                Map<String,Object> itemData = createData(item);
                itemData.putAll(boxData);
                newRow = createRow(boxBodyRow,table,itemData);
                table.addRow(newRow, i);
                i++;
            }

            newRow = createRow(boxFooterRow,table,boxData);
            table.addRow(newRow, i);
            i++;
        }

        table.removeRow(4);
        table.removeRow(3);
        table.removeRow(2);
    }

    private XWPFTableRow createRow(XWPFTableRow templateRow, XWPFTable table, Map<String,Object> vars) throws IOException, XmlException {
        XWPFTableRow newRow = new XWPFTableRow(CTRow.Factory.parse(templateRow.getCtRow().newInputStream()), table);

        for (XWPFTableCell cell : newRow.getTableCells()) {
            for (XWPFParagraph paragraph : cell.getParagraphs()) {
                for (XWPFRun run : paragraph.getRuns()) {
                    String text = run.getText(0);
                    if (text != null) {
                        Matcher mtchr = ptrn.matcher(text);
                        while(mtchr.find()) {
                            String mark = mtchr.group();
                            if(vars.containsKey(mark)) {
                                text = text.replace(mark, String.valueOf(vars.get(mark)));
                            }
                            run.setText(text, 0);
                        }
                    }
                }
            }
        }
        return newRow;
    }
}
