package com.example.expensereport.models;

import javax.persistence.*;

@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 30)
    private String name;
    @Column(columnDefinition = "decimal(7,2) NOT NULL DEFAULT 0")
    private double itemLimit;

    public Item() {}


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getItemLimit() {
        return itemLimit;
    }
    public void setItemLimit(double itemLimit) {
        this.itemLimit = itemLimit;
    }
}
