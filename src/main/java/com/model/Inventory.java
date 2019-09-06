package com.model;

import java.util.Map;

/**
 * Created by amitsingh on 21/06/19.
 */
public class Inventory {
    private String city;
    private int  id;
    private Map<String, Hotel> hotels;

    public Inventory() {
    }

    public String getCity() {
        return city;
    }

    public int getId() {
        return id;
    }

    public Map<String, Hotel> getHotels() {
        return hotels;
    }
}
