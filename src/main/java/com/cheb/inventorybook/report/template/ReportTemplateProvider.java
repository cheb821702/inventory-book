package com.cheb.inventorybook.report.template;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public interface ReportTemplateProvider {

    List<String> getAvailableReportLanguages();

    InputStream getReportTemplateInputStream(String language) throws FileNotFoundException;
}
