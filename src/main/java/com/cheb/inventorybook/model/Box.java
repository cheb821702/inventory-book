package com.cheb.inventorybook.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Box {

    private Long id;
    private Integer number;
    private Set<Item> items;
    private Expedition expedition;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false, unique = true)
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @OneToMany(mappedBy = "box")
    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    @ManyToOne()
    @JoinColumn(name = "expedition_id", nullable = false)
    public Expedition getExpedition() {
        return expedition;
    }

    public void setExpedition(Expedition expedition) {
        this.expedition = expedition;
    }
}