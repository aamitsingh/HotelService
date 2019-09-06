import com.Exception.InvalidArgumentException;
import com.PriceOrderedHotelService;
import com.dao.InventoryDao;
import com.dao.InventoryDaoImpl;
import com.model.Hotel;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static mockit.Deencapsulation.setField;

/**
 * Created by amitsingh on 25/06/19.
 */

@Test
public class PriceOrderedHotelServiceTest {

    final String cityName = "Düsseldorf";
    final CommonTestUtil commonTestUtil = new CommonTestUtil();

    /**
     * This test will validate whether the PriceOrderedHotelService will return the valid Inventory where all the
     * hotels are sorted on the basis of Price
     *
     *     e.g.
     *          City: ABC
     *          [Hotel-1] : <Lowest Price>
     *          [Hotel-2] : <Second Lowest>
     *          [Hotel-3] : <Third Lowest>
     *
     */
    @Test
    public void testGetHotelsForCity_HotelPriceOrdered() throws InvalidArgumentException {

        PriceOrderedHotelService priceOrderedHotelService = new PriceOrderedHotelService();
        InventoryDao inventoryDao = new InventoryDaoImpl();
        setField(priceOrderedHotelService, inventoryDao);
        setField(inventoryDao, commonTestUtil.getInventoryFromJSON());

        final Double cheaperHotel_Expected = 52.0;
        final Double expensiveHotel_Expected = 150.0;

        final List<Hotel> priceOrderedResult_Actual = priceOrderedHotelService.getHotelsForCity(cityName);

        Assert.assertEquals(priceOrderedResult_Actual.get(0).getPrice(), cheaperHotel_Expected);
        Assert.assertEquals(priceOrderedResult_Actual.get(1).getPrice(), expensiveHotel_Expected);
    }

    /**
     * This test will validate whether the PriceOrderedHotelService will return the valid Inventory all the partners in
     * the hotels will be sorted in the ascending order on the basis of Price.
     *      e.g.
     *          City: ABC
     *          [Hotel-1] : <Lowest Price : Partner-1> <Second Lowest Price : Partner-2> ... <Highest Price : Partner-N>
     *
    */
    @Test
    public void testGetHotelsForCity_PartnersPriceOrdered() throws InvalidArgumentException {

        PriceOrderedHotelService priceOrderedHotelService = new PriceOrderedHotelService();
        InventoryDao inventoryDao = new InventoryDaoImpl();
        setField(priceOrderedHotelService, inventoryDao);
        setField(inventoryDao, commonTestUtil.getInventoryFromJSON());

        final Double cheaperHotelPartnerPrice_Expected = 150.0;
        final Double expensiveHotelPartnerPrice_Expected = 160.0;

        final List<Hotel> priceOrderedResult_Actual = priceOrderedHotelService.getHotelsForCity(cityName);

        Assert.assertEquals(priceOrderedResult_Actual.get(1).getPartners().get("102").getPrice().doubleValue(), cheaperHotelPartnerPrice_Expected);
        Assert.assertEquals(priceOrderedResult_Actual.get(1).getPartners().get("101").getPrice().doubleValue(), expensiveHotelPartnerPrice_Expected);
    }
}
