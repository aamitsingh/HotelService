package com;

import com.util.ValidationUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

/**
 * Created by amitsingh on 25/06/19.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@RestController
public class PartnerValidateService {

    private final String SCHEMA_FILE = "src/main/schema/schema.json";
    private final String JSON_FILE = "src/main/resources/datasource.json";

    /**
     * Validate partners : Validate JSON structure and check whether the mandatory (amount, dateFrom, dateTo)
     * field for partner offer is present or not.
     *
     * @param -
     * @return Status whether the datasource(JSON) is valid or not.
     *
     */
    @RequestMapping(value = "/validatePartner", method = RequestMethod.GET)
    public boolean validatePartner() throws Exception   {

        if (ValidationUtils.isJsonValid(new File(SCHEMA_FILE), new File(JSON_FILE))) {
                return true;
            }

            return false;
    }

}
