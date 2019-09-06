package com;

import com.Exception.InvalidArgumentException;
import com.dao.InventoryDao;
import com.model.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;



@SuppressWarnings("SpringJavaAutowiringInspection")
@RestController
public class UnorderedHotelService implements HotelService {

    @Autowired
    private InventoryDao inventoryDao;

    /**
     * Basic Hotel Service : This will return the valid Inventory Map. The Inventory Map contains the hotel inventory
     * for a city in the same order as that stored in the JSON. It will not apply any logic/processing on the data.
     *
     *
     * @param : City Name
     * @return - List<Hotel>
     *
     */
    @Override
    @RequestMapping(value = "/hotelservice/cities/{city}", method = RequestMethod.GET)
    public List<Hotel> getHotelsForCity(@PathVariable("city") String cityName) throws InvalidArgumentException {

        Map<String, Collection<Hotel>> hotels = inventoryDao.getHotels(cityName);
        return new ArrayList<>(hotels.get(cityName));
    }
}
