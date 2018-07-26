package com.cheb.inventorybook.model;

import javax.persistence.*;

@Entity
public class Item {

    private Long id;
    private Integer inventoryNumber;
    private Float weight;
    private Float cost;
    private ItemGroup itemGroup;
    private Box box;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column()
    public Integer getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(Integer inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }

    @Column(nullable = false)
    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    @Column(nullable = false)
    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itemGroup_id", nullable = false)
    public ItemGroup getItemGroup() {
        return itemGroup;
    }

    public void setItemGroup(ItemGroup itemGroup) {
        this.itemGroup = itemGroup;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "box_id")
    public Box getBox() {
        return box;
    }

    public void setBox(Box box) {
        this.box = box;
    }
}
