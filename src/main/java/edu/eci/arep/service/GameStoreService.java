package edu.eci.arep.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


public class GameStoreService {

    private static GameStoreService instance;
    private Map<String, JsonObject> controls;
    private Map<String, Integer> GamePrices = new HashMap<>();

    private GameStoreService() {
        this.controls = new ConcurrentHashMap<>();
        GamePrices.put("halo", 120000);
        GamePrices.put("CallOfDuty", 135000);
        GamePrices.put("AssassinsCreed", 65000);
        GamePrices.put("GrandTheftAuto", 125000);
        GamePrices.put("LeagueOfLegends", 0);
    }


    public static GameStoreService getInstance() {
        if (instance == null) {
            instance = new GameStoreService();
        }
        return instance;
    }


    public JsonObject addControl(String URIStr) {
        String name = URIStr.split("=")[1];
        JsonObject control = makeRandomControl();
        control.addProperty("name", name);
        controls.put(name, control);
        return control;

    }


    public JsonObject getControls() {
        JsonObject response = new JsonObject();
        controls.forEach((k, v) -> {
            response.add(k, v);
        });
        return response;
    }

  
    private JsonObject makeRandomControl() {
        JsonObject control = new JsonObject();
        JsonArray gamert = new JsonArray();
        int numgames = (int) (Math.random() * 9) + 1;
        int price = 0;
        for (int i = 0; i < numgames; i++) {
            int gameIndex = (int) (Math.random() * GamePrices.size());
            String gam = (String) GamePrices.keySet().toArray()[gameIndex];
            int GPrice = GamePrices.get(gam);
            price += GPrice;
            gamert.add(gam);
        }
        control.addProperty("price", price);
        control.add("games", gamert);
        return control;
    }

}
