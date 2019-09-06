import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Hotel;
import com.model.Inventory;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by amitsingh on 26/06/19.
 */
public class CommonTestUtil {

    private final String DATASOURCE_TEST_FILEPATH = "src/test/resources/";

    /*
    Common utility function to populate the inventory map from the JSON.
     */
    public Map<String, Collection<Hotel>> getInventoryFromJSON() {

        Map<String, Collection<Hotel>> inventoryMap = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        Collection<File> files = FileUtils.listFiles(new File(DATASOURCE_TEST_FILEPATH), new String[]{"json"}, true);
        for (File file: files){
            Inventory inventory = null;
            try {
                inventory = objectMapper.readValue(file, Inventory.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            inventoryMap.put(inventory.getCity(), inventory.getHotels().values());
        }
        return inventoryMap;
    }
}
