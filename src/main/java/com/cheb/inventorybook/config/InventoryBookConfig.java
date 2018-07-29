package com.cheb.inventorybook.config;

import com.cheb.inventorybook.report.template.ReportTemplateFileProvider;
import com.cheb.inventorybook.report.template.ReportTemplateProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.cheb.inventorybook.service"})
//@ServletComponentScan("com.cheb.inventorybook.web")
public class InventoryBookConfig {

    @Value("${account-book.report.template-file-name:ItemsListTemplate}")
    public String REPORT_TEMPLATE_FILE_NAME;
    @Value("${account-book.report.template-file-ext:docx}")
    public String REPORT_TEMPLATE_FILE_EXT;
    @Value("${account-book.report.template-folder-path}")
    public String REPORT_TEMPLATE_FOLDER_PATH;

    @Bean
    public ReportTemplateProvider getReportTemplateProvider() {
        return new ReportTemplateFileProvider(REPORT_TEMPLATE_FOLDER_PATH, REPORT_TEMPLATE_FILE_NAME, REPORT_TEMPLATE_FILE_EXT);
    }

}
