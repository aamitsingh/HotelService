package com.dao;

import com.Exception.InvalidArgumentException;
import com.model.Hotel;

import java.util.Collection;
import java.util.Map;

/**
 * Created by amitsingh on 21/06/19.
 */
public interface InventoryDao {
    Map<String, Collection<Hotel>> getHotels(String city) throws InvalidArgumentException;
}
