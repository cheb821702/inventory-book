package com.cheb.inventorybook.controller;

import com.cheb.inventorybook.exception.InventoryBookValidationException;
import com.cheb.inventorybook.service.ItemBoxesReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/api")
public class LocaleController {

    private final ItemBoxesReportService itemBoxesReportService;

    @Autowired
    public LocaleController(ItemBoxesReportService itemBoxesReportService) {
        this.itemBoxesReportService = itemBoxesReportService;
    }

    @GetMapping("/reports/languages")
    public ResponseEntity<?> getReportsLanguages() {
        List<String> reportLanguages = itemBoxesReportService.getReportLanguages();
        Resources<String> resources = new Resources<>(reportLanguages);
        resources.add(linkTo(methodOn(LocaleController.class).getReportsLanguages()).withSelfRel());
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/countries")
    public ResponseEntity<?> getCountries(@RequestParam(value = "languageIso") String languageIso) {
        languageIso = languageIso.toLowerCase();
        if(!itemBoxesReportService.getReportLanguages().contains(languageIso)) {
            throw new InventoryBookValidationException("Locale " + languageIso + " is not in available list "
                    + itemBoxesReportService.getReportLanguages().toString() + "." );
        }

        List<String> countries = itemBoxesReportService.getCountriesNamesByLocale(languageIso);
        Resources<String> resources = new Resources<>(countries);
        resources.add(linkTo(methodOn(LocaleController.class).getCountries(languageIso)).withSelfRel());
        return ResponseEntity.ok(resources);
    }
}
