package com.cheb.inventorybook.controller;

import com.cheb.inventorybook.exception.InventoryBookValidationException;
import com.cheb.inventorybook.model.ItemGroup;
import com.cheb.inventorybook.model.ItemGroupLocalization;
import com.cheb.inventorybook.repositories.ItemGroupLocalizationRepository;
import com.cheb.inventorybook.service.ItemBoxesReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RepositoryRestController
public class ItemGroupLocalizationController {

    private final ItemGroupLocalizationRepository repository;
    private final ItemBoxesReportService itemBoxesReportService;
    private final RepositoryEntityLinks entityLinks;

    @Autowired
    public ItemGroupLocalizationController(ItemGroupLocalizationRepository repository, ItemBoxesReportService itemBoxesReportService, RepositoryEntityLinks entityLinks) {
        this.repository = repository;
        this.itemBoxesReportService = itemBoxesReportService;
        this.entityLinks = entityLinks;
    }

    @GetMapping("/itemgroups/localizations")
    public ResponseEntity<?> getItemGroupLocalizations(@RequestParam(value = "languageIso") String languageIso) {
        languageIso = languageIso.toLowerCase();
        if(!itemBoxesReportService.getReportLanguages().contains(languageIso)) {
            throw new InventoryBookValidationException("ItemGroup localization " + languageIso + " is not in available list "
                    + itemBoxesReportService.getReportLanguages().toString() + "." );
        }

        List<Resource<ItemGroupLocalization>> localizations = new ArrayList<>();
        repository.findAllByLanguageIsoOrderByName(languageIso).forEach(itemGroupLocalization -> {
            Resource<ItemGroupLocalization> resource = new Resource<>(itemGroupLocalization);
            resource.add(entityLinks.linkToSingleResource(ItemGroup.class, itemGroupLocalization.getItemGroup().getId()));
            localizations.add(resource);
        });

        Resources<Resource<ItemGroupLocalization>> resources = new Resources<>(localizations);
        resources.add(linkTo(methodOn(ItemGroupLocalizationController.class).getItemGroupLocalizations(languageIso)).withSelfRel());
        return ResponseEntity.ok(resources);
    }
}
