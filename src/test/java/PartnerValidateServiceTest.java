import com.PartnerValidateService;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by amitsingh on 26/06/19.
 */

@Test
public class PartnerValidateServiceTest {

    /**
     * This test will validate the the JSON structure and check whether the mandatory (amount, dateFrom, dateTo)
     * field for partner offer is present or not.
     *
     */
    public void testCompareDataSourceWithJSONSchema() throws Exception {

        PartnerValidateService partnerValidateService = new PartnerValidateService();

        final boolean expected = true;
        final boolean actual = partnerValidateService.validatePartner();

        Assert.assertEquals(actual, expected);

    }
}
