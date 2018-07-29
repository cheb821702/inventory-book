package com.cheb.inventorybook.repositories;

import com.cheb.inventorybook.model.ItemGroupLocalization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface ItemGroupLocalizationRepository extends CrudRepository<ItemGroupLocalization, Long> {

    List<ItemGroupLocalization> findAllByLanguageIsoOrderByName(String languageIso);

}
