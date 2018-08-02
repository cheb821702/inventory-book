package com.cheb.inventorybook.controller;

import com.cheb.inventorybook.exception.InventoryBookValidationException;
import com.cheb.inventorybook.service.ItemBoxesReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api")
public class LocaleController {

    private final ItemBoxesReportService itemBoxesReportService;

    @Autowired
    public LocaleController(ItemBoxesReportService itemBoxesReportService) {
        this.itemBoxesReportService = itemBoxesReportService;
    }

    @GetMapping("/reports/languages")
    public ResponseEntity<List<String>> getReportsLanguages() {
        return ResponseEntity.ok(itemBoxesReportService.getReportLanguages());
    }

    @GetMapping("/countries")
    public ResponseEntity<List<String>> getCountries(@RequestParam(value = "languageIso") String languageIso) {
        languageIso = languageIso.toLowerCase();
        if(!itemBoxesReportService.getReportLanguages().contains(languageIso)) {
            throw new InventoryBookValidationException("Locale " + languageIso + " is not in available list "
                    + itemBoxesReportService.getReportLanguages().toString() + "." );
        }
        return ResponseEntity.ok(itemBoxesReportService.getCountriesNamesByLocale(languageIso));
    }
}
