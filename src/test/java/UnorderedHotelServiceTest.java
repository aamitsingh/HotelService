import com.Exception.InvalidArgumentException;
import com.UnorderedHotelService;
import com.dao.InventoryDao;
import com.dao.InventoryDaoImpl;
import com.model.Hotel;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static mockit.Deencapsulation.setField;

/**
 * Created by amitsingh on 26/06/19.
 */
public class UnorderedHotelServiceTest {

    private final String cityName = "Düsseldorf";
    private final CommonTestUtil commonTestUtil = new CommonTestUtil();

    /**
     *  This will test the basic hotel service that will return the valid Inventory Map. The result-set contains the
     *  hotel inventory for a city in the same order as that stored in the JSON. It will not apply any logic/processing on the data.
     *
     */
    @Test
    public void testHotelServiceReturnTheValidInventory() throws InvalidArgumentException {

        final UnorderedHotelService unorderedHotelService = new UnorderedHotelService();
        final InventoryDao inventoryDao = new InventoryDaoImpl();
        setField(unorderedHotelService, inventoryDao);
        setField(inventoryDao, commonTestUtil.getInventoryFromJSON());

        final List<Hotel> hotelServiceResult_Actual = unorderedHotelService.getHotelsForCity(cityName);

        /**
         *  Comparing that whether the map is aligned with the JSON or not. It will test whether properties (amounts in this case)
         *  are populated properly or not. */
        Assert.assertEquals(hotelServiceResult_Actual.get(0).getPartners().get("101").getPrices().get("1001").getAmount(), 160.0);
        Assert.assertEquals(hotelServiceResult_Actual.get(0).getPartners().get("101").getPrices().get("1002").getAmount(), 162.0);
        Assert.assertEquals(hotelServiceResult_Actual.get(0).getPartners().get("102").getPrices().get("1001").getAmount(), 150.0);
        Assert.assertEquals(hotelServiceResult_Actual.get(0).getPartners().get("102").getPrices().get("1002").getAmount(), 152.0);
        Assert.assertEquals(hotelServiceResult_Actual.get(1).getPartners().get("201").getPrices().get("2001").getAmount(), 60.0);
        Assert.assertEquals(hotelServiceResult_Actual.get(1).getPartners().get("201").getPrices().get("2002").getAmount(), 52.0);

    }
}
