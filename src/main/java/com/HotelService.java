package com;

import com.Exception.InvalidArgumentException;
import com.model.Hotel;

import java.util.List;

/**
 * Created by amitsingh on 21/06/19.
 */

interface HotelService {
    /**
     * @param String cityName Name of the city to search for.
     * @return Returns the list of corresponding hotels.
     * @throws InvalidArgumentException if city name is unknown.
     */
    public List<Hotel> getHotelsForCity(final String cityName) throws InvalidArgumentException
    ;
}
