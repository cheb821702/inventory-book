package com.cheb.inventorybook.service;

import com.cheb.inventorybook.exception.InventoryBookValidationException;
import com.cheb.inventorybook.report.ReportBuilder;
import com.cheb.inventorybook.report.dto.ReportBoxTableDto;
import com.cheb.inventorybook.report.template.ReportTemplateProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class ItemBoxesReportServiceDocxImpl implements ItemBoxesReportService {

    private List<Locale> languageLocales;
    private List<Locale> countryLocales;
    private List<String> reportLanguages;

    @Autowired
    private ReportTemplateProvider reportTemplateProvider;

    @PostConstruct
    public void init() {
        initLocales();
        initListAvailableReportLanguages();
    }

    public byte[] createReport(ReportBoxTableDto dto, String language) {
        try {
            ReportBuilder reportBuilder = new ReportBuilder();
            reportBuilder.setReportTemplateProvider(reportTemplateProvider);
            reportBuilder.setLanguageIso(language);
            reportBuilder.setReportBoxTableDto(dto);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            reportBuilder.createReport(outputStream);
            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Can't generate report for " + language + " language.", e);
        }
    }

    public List<Locale> getLanguageLocales() {
        if(languageLocales == null) initLocales();
        return languageLocales;
    }

    public List<Locale> getCountryLocales() {
        if(countryLocales == null) initLocales();
        return countryLocales;
    }

    public List<String> getCountriesNamesByLocale(String language) {
        for ( Locale languageLocale : getLanguageLocales() ) {
            if( languageLocale.getLanguage().equals(language)) {
                List<String> countries = new ArrayList<>();
                getCountryLocales().forEach(locale -> {
                    countries.add(locale.getDisplayCountry(languageLocale));
                });
                return countries;
            }
        }
        throw new InventoryBookValidationException("OS doesn't contain locale for language " + language);
    }

    public List<String> getReportLanguages() {
        if(reportLanguages == null) initListAvailableReportLanguages();
        return reportLanguages;
    }

    private void initLocales() {
        List<Locale> lan = new ArrayList<>();
        List<Locale> ctr = new ArrayList<>();
        for(Locale l : Locale.getAvailableLocales()) {
            if(!l.getLanguage().isEmpty())
                if(l.getCountry().isEmpty()) {
                    lan.add(l);
                } else {
                    ctr.add(l);
                }
        }
        languageLocales = lan;
        countryLocales = ctr;
    }

    private void initListAvailableReportLanguages() {
        reportLanguages = reportTemplateProvider.getAvailableReportLanguages();
    }
}
