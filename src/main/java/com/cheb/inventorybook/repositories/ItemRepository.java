package com.cheb.inventorybook.repositories;

import com.cheb.inventorybook.model.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {

}
