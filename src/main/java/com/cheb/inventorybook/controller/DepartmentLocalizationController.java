package com.cheb.inventorybook.controller;

import com.cheb.inventorybook.exception.InventoryBookValidationException;
import com.cheb.inventorybook.model.Department;
import com.cheb.inventorybook.model.DepartmentLocalization;
import com.cheb.inventorybook.repositories.DepartmentLocalizationRepository;
import com.cheb.inventorybook.service.ItemBoxesReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@RepositoryRestController
public class DepartmentLocalizationController {

    private final DepartmentLocalizationRepository repository;
    private final ItemBoxesReportService itemBoxesReportService;
    private final RepositoryEntityLinks entityLinks;

    @Autowired
    public DepartmentLocalizationController(DepartmentLocalizationRepository repository, ItemBoxesReportService itemBoxesReportService, RepositoryEntityLinks entityLinks) {
        this.repository = repository;
        this.itemBoxesReportService = itemBoxesReportService;
        this.entityLinks = entityLinks;
    }

    @GetMapping("/departments/localizations")
    public ResponseEntity<?> getDepartmentLocalizations(@RequestParam(value = "languageIso") String languageIso) {
        languageIso = languageIso.toLowerCase();
        if(!itemBoxesReportService.getReportLanguages().contains(languageIso)) {
            throw new InventoryBookValidationException("Department localization " + languageIso + " is not in available list "
                    + itemBoxesReportService.getReportLanguages().toString() + "." );
        }

        List<Resource<DepartmentLocalization>> localizations = new ArrayList<>();
        repository.findAllByLanguageIsoOrderByName(languageIso).forEach(departmentLocalization -> {
            Resource<DepartmentLocalization> resource = new Resource<>(departmentLocalization);
            resource.add(entityLinks.linkToSingleResource(Department.class, departmentLocalization.getDepartment().getId()));
            localizations.add(resource);
        });

        Resources<Resource<DepartmentLocalization>> resources = new Resources<>(localizations);
        resources.add(linkTo(methodOn(DepartmentLocalizationController.class).getDepartmentLocalizations(languageIso)).withSelfRel());
        return ResponseEntity.ok(resources);
    }
}
