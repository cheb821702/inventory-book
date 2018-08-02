package com.cheb.inventorybook.controller;

import com.cheb.inventorybook.exception.InventoryBookValidationException;
import com.cheb.inventorybook.model.ItemGroupLocalization;
import com.cheb.inventorybook.repositories.ItemGroupLocalizationRepository;
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
public class ItemGroupLocalizationController {

    private final ItemGroupLocalizationRepository repository;
    private final ItemBoxesReportService itemBoxesReportService;

    @Autowired
    public ItemGroupLocalizationController(ItemGroupLocalizationRepository repository, ItemBoxesReportService itemBoxesReportService) {
        this.repository = repository;
        this.itemBoxesReportService = itemBoxesReportService;
    }

    @GetMapping("/itemgroups/localizations")
    public ResponseEntity<List<ItemGroupLocalization>> getItemGroupLocalizations(@RequestParam(value = "languageIso") String languageIso) {
        languageIso = languageIso.toLowerCase();
        if(!itemBoxesReportService.getReportLanguages().contains(languageIso)) {
            throw new InventoryBookValidationException("ItemGroup localization " + languageIso + " is not in available list "
                    + itemBoxesReportService.getReportLanguages().toString() + "." );
        }
        return ResponseEntity.ok(repository.findAllByLanguageIsoOrderByName(languageIso));
    }
}
