package com.cheb.inventorybook.event;

import com.cheb.inventorybook.exception.InventoryBookValidationException;
import com.cheb.inventorybook.model.Department;
import com.cheb.inventorybook.model.DepartmentLocalization;
import com.cheb.inventorybook.model.ItemGroup;
import com.cheb.inventorybook.model.ItemGroupLocalization;
import com.cheb.inventorybook.service.ItemBoxesReportService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
//import org.springframework.data.rest.core.annotation.HandleBeforeSave;
//import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
//import org.springframework.stereotype.Component;

//@Component
//@RepositoryEventHandler
public class LocalizationEventHandler {

//    private final ItemBoxesReportService itemBoxesReportService;
//
//    @Autowired
//    public LocalizationEventHandler(ItemBoxesReportService itemBoxesReportService) {
//        this.itemBoxesReportService = itemBoxesReportService;
//    }
//
//    @HandleBeforeSave
//    @HandleBeforeCreate
//    public void handleDepartmentSave(Department department) {
//        for(String lang : itemBoxesReportService.getReportLanguages()) {
//            boolean exist = false;
//            for(DepartmentLocalization localization : department.getLocalizations()) {
//                if(lang.equals(localization.getLanguageIso())) {
//                    if(exist) {
//                        throw new InventoryBookValidationException("Department localization " + lang + " is added few times.");
//                    } else {
//                        exist = true;
//                    }
//                }
//            }
//            if(!exist) {
//                throw new InventoryBookValidationException("Department localization " + lang + " is required.");
//            }
//        }
//    }
//
//    @HandleBeforeSave
//    @HandleBeforeCreate
//    public void handleItemGroupSave(ItemGroup itemGroup) {
//        for(String lang : itemBoxesReportService.getReportLanguages()) {
//            boolean exist = false;
//            for(ItemGroupLocalization localization : itemGroup.getLocalizations()) {
//                if(lang.equals(localization.getLanguageIso())) {
//                    if(exist) {
//                        throw new InventoryBookValidationException("ItemGroup localization " + lang + " is added few times.");
//                    } else {
//                        exist = true;
//                    }
//                }
//            }
//            if(!exist) {
//                throw new InventoryBookValidationException("ItemGroup localization " + lang + " is required.");
//            }
//        }
//
//    }
}
