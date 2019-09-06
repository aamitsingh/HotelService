package com.model;

import java.util.Map;

/**
 * Created by amitsingh on 21/06/19.
 */
public class Hotel {
    private String name;
    private String adr;
    private Map<String, Partner> partners;
    private Double price;

    public Hotel() {
    }

    public String getName() {
        return name;
    }

    public String getAdr() {
        return adr;
    }

    public Map<String, Partner> getPartners() {
        return partners;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
