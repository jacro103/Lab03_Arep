package edu.eci.arep.controller;

import com.google.gson.JsonObject;

import edu.eci.arep.annotation.Component;
import edu.eci.arep.annotation.HttpMethod;
import edu.eci.arep.annotation.RequestMapping;
import edu.eci.arep.service.GameStoreService;


@Component
public class GameController {


    @RequestMapping(path = "/controls", method = HttpMethod.GET)
    public static JsonObject getControls(String calledServiceURI) {
        return GameStoreService.getInstance().getControls();
    }
}
    