package com.cheb.inventorybook.repositories;

import com.cheb.inventorybook.model.DepartmentLocalization;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DepartmentLocalizationRepository extends CrudRepository<DepartmentLocalization, Long> {

    List<DepartmentLocalization> findAllByLanguageIsoOrderByName(String languageIso);

}
