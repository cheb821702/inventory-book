package com.cheb.inventorybook.model.projection;

import com.cheb.inventorybook.model.Department;
import com.cheb.inventorybook.model.DepartmentLocalization;
import org.springframework.data.rest.core.config.Projection;

import java.util.Set;

@Projection(name="inlineLocalizationsDepartment", types = { Department.class })
public interface InlineLocalizationsDepartment {

    Set<DepartmentLocalization> getLocalizations();

}
