package com.cheb.inventorybook.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class ItemGroup {

    private Long id;
    private Set<ItemLocalization> localizations;
    private Department department;
    private Set<Item> items;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itemGroup", orphanRemoval = true)
    public Set<ItemLocalization> getLocalizations() {
        return localizations;
    }

    public void setLocalizations(Set<ItemLocalization> localizations) {
        this.localizations = localizations;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itemGroup", orphanRemoval = true)
    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

}
