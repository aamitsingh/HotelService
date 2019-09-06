package com;

import com.Exception.InvalidArgumentException;
import com.dao.InventoryDao;
import com.model.Hotel;
import com.model.Partner;
import com.model.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Created by amitsingh on 21/06/19.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@RestController
public class PriceOrderedHotelService implements  HotelService {

    @Autowired
    private InventoryDao inventoryDao;

    /**
     * Return hotel inventory sorted by price :  This will return the hotel inventory SORTED BY PRICE. All the hotel
     * will be sorted by the price as well as the partners inside the hotels will be sorted.
     *
     * @param : City Name
     * @return : List of hotels
     *
     */
    @Override
    @RequestMapping(value = "priceorderedhotel/cities/{city}", method = RequestMethod.GET)
    public List<Hotel> getHotelsForCity(@PathVariable("city") String cityName) throws InvalidArgumentException {

        //Get the offer for a particular city
        Map<String, Collection<Hotel>> hotels = inventoryDao.getHotels(cityName);
        return sortHotels(hotels.get(cityName));
    }

    private List<Hotel> sortHotels( Collection<Hotel> hotels){
        postProcessHotels(hotels);
        List<Hotel> hotelList = new ArrayList<>(hotels);
        Collections.sort(hotelList, (o1, o2)->(Double.compare(o1.getPrice(), o2.getPrice())));
        return hotelList;
    }

    /*
    Sort the hotels on the basis of Price in ascending order. e.g.

    [Hotel-1] : <Lowest Price>
    [Hotel-2] : <Second Lowest>
    [Hotel-3] : <Third Lowest>
     */

    /**
     * Sort the hotels on the basis of Price in ascending order, e.g.
     *   [Hotel-1] : <Lowest Price>
     *   [Hotel-2] : <Second Lowest>
     *   [Hotel-3] : <Third Lowest>
     *
     * @param : Hotels
     * @return -
     *
     */
    private void postProcessHotels(Collection<Hotel> hotels){
        for (Hotel hotel: hotels) {
            postProcessPartners(hotel.getPartners().values());
            List<Map.Entry<String, Partner>> partners = new ArrayList<>(hotel.getPartners().entrySet());

            Collections.sort(partners, Comparator.comparingDouble(o -> o.getValue().getPrice()));
            hotel.setPrice(partners.iterator().next().getValue().getPrice());
            hotel.getPartners().clear();
            for (Map.Entry<String,Partner> partner: partners) {
                hotel.getPartners().put(partner.getKey() ,partner.getValue());
            }
        }
    }


    /**
     * Sort all the partners in the hotels in the ascending order on the basis of Price. e.g
     *   [Hotel-1] : <Lowest Price : Partner-1> <Second Lowest Price : Partner-2> ... <Highest Price : Partner-N>
     *
     * @param : Partners
     * @return -
     *
     */
    private void postProcessPartners(Collection<Partner> partners){
        for (Partner partner: partners) {
            List<Map.Entry<String,Price>> prices = new ArrayList<>(partner.getPrices().entrySet());
            Collections.sort(prices, Comparator.comparingDouble(o -> o.getValue().getAmount()));
            partner.setPrice(prices.iterator().next().getValue().getAmount());
            partner.getPrices().clear();
            for (Map.Entry<String,Price> price: prices) {
                partner.getPrices().put(price.getKey() ,price.getValue());
            }
        }
    }
}
