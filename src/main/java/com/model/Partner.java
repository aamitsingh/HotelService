package com.model;

import java.util.Map;

/**
 * Created by amitsingh on 21/06/19.
 */
public class Partner {
    private String name;
    private String url;

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    private Double price;
    private Map<String, Price> prices;

    public Partner() {
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public Map<String, Price> getPrices() {
        return prices;
    }
}
