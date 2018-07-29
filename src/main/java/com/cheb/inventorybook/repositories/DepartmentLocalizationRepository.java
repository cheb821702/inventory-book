package com.cheb.inventorybook.repositories;

import com.cheb.inventorybook.model.DepartmentLocalization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface DepartmentLocalizationRepository extends CrudRepository<DepartmentLocalization, Long> {

    List<DepartmentLocalization> findAllByLanguageIsoOrderByName(String languageIso);

}
