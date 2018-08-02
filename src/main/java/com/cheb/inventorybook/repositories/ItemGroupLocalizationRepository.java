package com.cheb.inventorybook.repositories;

import com.cheb.inventorybook.model.ItemGroupLocalization;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemGroupLocalizationRepository extends CrudRepository<ItemGroupLocalization, Long> {

    List<ItemGroupLocalization> findAllByLanguageIsoOrderByName(String languageIso);

}
