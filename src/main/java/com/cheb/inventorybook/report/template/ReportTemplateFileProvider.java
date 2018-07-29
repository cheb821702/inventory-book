package com.cheb.inventorybook.report.template;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ReportTemplateFileProvider implements ReportTemplateProvider {

    private static final int LANGUAGE_EXT_LENGTH = 2;

    private String reportTemplateFolderPath;
    private String reportTemplateFileName;
    private String reportTemplateFileExt;


    public ReportTemplateFileProvider() {
    }

    public ReportTemplateFileProvider(String reportTemplateFolderPath, String reportTemplateFileName, String reportTemplateFileExt) {
        this.reportTemplateFolderPath = reportTemplateFolderPath;
        this.reportTemplateFileName = reportTemplateFileName;
        this.reportTemplateFileExt = reportTemplateFileExt;
    }

    public List<String> getAvailableReportLanguages() {
        List<String> repLangs = new ArrayList<>();
        if(reportTemplateFolderPath != null) {
            File dir = new File(reportTemplateFolderPath);
            if(dir.exists() && dir.isDirectory()) {
                File [] files = dir.listFiles((dir1, name) -> name.endsWith(reportTemplateFileExt));
                for(File f : files) {
                    String name = FilenameUtils.removeExtension(f.getName());
                    if(name.startsWith(reportTemplateFileName)) {
                        String language = name.substring(reportTemplateFileName.length()).trim();
                        if(language.length() == LANGUAGE_EXT_LENGTH) {
                            repLangs.add(language.toLowerCase());
                        }
                    }
                }
            }
        }
       return repLangs;
    }

    public InputStream getReportTemplateInputStream(String language) throws FileNotFoundException {
        return new FileInputStream(getReportTemplateFilePath(language));
    }

    private String getReportTemplateFilePath(String language) {
        StringBuilder fPathBuilder = new StringBuilder(reportTemplateFolderPath);
        if(reportTemplateFolderPath.endsWith(File.separator))
            fPathBuilder.append(File.separator);
        fPathBuilder.append(reportTemplateFileName);
        fPathBuilder.append(language.toUpperCase());
        fPathBuilder.append(".");
        fPathBuilder.append(reportTemplateFileExt);
        return  fPathBuilder.toString();
    }

}
