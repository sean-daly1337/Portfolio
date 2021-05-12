package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.requests.ServerLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.MapLocationService;

public class MapController {

    @Autowired
    MapLocationService mapLocationService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getPages() {

        ModelAndView model = new ModelAndView("map");
        return model;

    }

    //return back json string
    @RequestMapping(value = "/getLocationByIpAddress", method = RequestMethod.GET)
    public @ResponseBody
    String getDomainInJsonFormat(@RequestParam String propertyId) {

        ObjectMapper mapper = new ObjectMapper();

        ServerLocation location = mapLocationService.getMapLocation(propertyId);

        String result = "";

        try {
            result = mapper.writeValueAsString(location);
        } catch (Exception e) {

            e.printStackTrace();
        }

        return result;

    }

}
