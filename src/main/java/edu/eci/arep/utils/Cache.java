package edu.eci.arep.utils;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import com.google.gson.JsonObject;


public class Cache {

    private static Cache instance;
    private ConcurrentMap<String, JsonObject> data;

    private Cache() {
        this.data = new ConcurrentHashMap<>();
    }


    public static Cache getInstance() {
        if (instance == null) {
            instance = new Cache();
        }
        return instance;
    }


    public void add(String movie, JsonObject json) {
        data.putIfAbsent(movie, json);
    }


    public JsonObject get(String movie) {
        return data.get(movie);
    }

   
    public boolean contains(String movie) {
        return data.containsKey(movie);
    }

}