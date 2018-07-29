package com.cheb.inventorybook.model;

import javax.persistence.*;
import java.util.Set;


@Entity
public class Department {

    private Long id;
    private Set<DepartmentLocalization> localizations;
    private Set<ItemGroup> itemGroups;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department", orphanRemoval = true)
    public Set<DepartmentLocalization> getLocalizations() {
        return localizations;
    }

    public void setLocalizations(Set<DepartmentLocalization> localizations) {
        this.localizations = localizations;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department", orphanRemoval = true)
    public Set<ItemGroup> getItemGroups() {
        return itemGroups;
    }

    public void setItemGroups(Set<ItemGroup> itemGroups) {
        this.itemGroups = itemGroups;
    }

    @PrePersist
    @PreUpdate
    public void updateDepartmentLocalization() {
        if(this.localizations != null)
            for(DepartmentLocalization localization : this.localizations) {
                localization.setDepartment(this);
            }
    }
}
