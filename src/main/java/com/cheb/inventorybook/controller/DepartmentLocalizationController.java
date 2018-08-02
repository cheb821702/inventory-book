package com.cheb.inventorybook.controller;

import com.cheb.inventorybook.exception.InventoryBookValidationException;
import com.cheb.inventorybook.model.DepartmentLocalization;
import com.cheb.inventorybook.repositories.DepartmentLocalizationRepository;
import com.cheb.inventorybook.service.ItemBoxesReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DepartmentLocalizationController {

    private final DepartmentLocalizationRepository repository;
    private final ItemBoxesReportService itemBoxesReportService;

    @Autowired
    public DepartmentLocalizationController(DepartmentLocalizationRepository repository, ItemBoxesReportService itemBoxesReportService) {
        this.repository = repository;
        this.itemBoxesReportService = itemBoxesReportService;
    }

    @GetMapping("/departments/localizations")
    public ResponseEntity<List<DepartmentLocalization>> getDepartmentLocalizations(@RequestParam(value = "languageIso") String languageIso) {
        languageIso = languageIso.toLowerCase();
        if(!itemBoxesReportService.getReportLanguages().contains(languageIso)) {
            throw new InventoryBookValidationException("Department localization " + languageIso + " is not in available list "
                    + itemBoxesReportService.getReportLanguages().toString() + "." );
        }
        return ResponseEntity.ok(repository.findAllByLanguageIsoOrderByName(languageIso));
    }
}
