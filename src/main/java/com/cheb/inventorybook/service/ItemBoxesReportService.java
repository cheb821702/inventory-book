package com.cheb.inventorybook.service;

import com.cheb.inventorybook.report.dto.ReportBoxTableDto;

import java.util.List;
import java.util.Locale;

public interface ItemBoxesReportService {

    byte[] createReport(ReportBoxTableDto dto, String language);
    List<Locale> getLanguageLocales();
    List<Locale> getCountryLocales();
    List<String> getCountriesNamesByLocale(String language);
    List<String> getReportLanguages();
}
