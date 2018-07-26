package com.cheb.inventorybook.repositories;

import com.cheb.inventorybook.model.Department;
import com.cheb.inventorybook.model.projection.InlineLocalizationsDepartment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(excerptProjection = InlineLocalizationsDepartment.class)
public interface DepartmentRepository extends CrudRepository<Department, Long>{


}
