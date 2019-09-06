package com.dao;

import com.Exception.InvalidArgumentException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Hotel;
import com.model.Inventory;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by amitsingh on 21/06/19.
 */
@Component
public class InventoryDaoImpl implements InventoryDao{

    final String DATASOURCE_FILE_PATH = "src/main/resources/";
    private Map<String, java.util.Collection<Hotel>> inventoryMap;

    /**
     * This will load the JSON into memory when the application will start.
     *
     * @throws IOException
     */
    @PostConstruct
    public void init() throws IOException {
        inventoryMap = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        String filePath = DATASOURCE_FILE_PATH;
        Collection<File> files = FileUtils.listFiles(new File(filePath), new String[]{"json"}, true);
        for (File file: files){
            Inventory inventory = objectMapper.readValue(file, Inventory.class);
            inventoryMap.put(inventory.getCity(), inventory.getHotels().values());
        }
    }

    /**
     * Check whether the inventory is available for the requested city or not.
     *
     * @param city
     * @return
     * @throws InvalidArgumentException
     */
    public Map<String, Collection<Hotel>> getHotels(String city) throws InvalidArgumentException {

        if(inventoryMap.containsKey(city)){
            return inventoryMap;
        } else {
            throw new InvalidArgumentException("City Not Found");
        }
    }

}
