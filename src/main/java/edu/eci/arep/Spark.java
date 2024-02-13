package edu.eci.arep;

import java.util.HashMap;
import java.util.Map;

import edu.eci.arep.service.AppService;



public class Spark {

    private static Spark instance;
    private final Map<String, AppService> getRoutes = new HashMap<>();
    private final Map<String, AppService> postRoutes = new HashMap<>();

  
    

    private Spark() {
    }


    
    public static Spark getInstance() {
        if (instance == null) {
            instance = new Spark();
        }
        return instance;
    }


    
    public static void staticFileLocation(String path) {
        HttpServer.getInstance().setStaticFileLocation(path);
    }


    
    public static void get(String path, AppService handler) {
        getInstance().getRoutes.put(path, handler);
    }


    
    public static void post(String path, AppService handler) {
        getInstance().postRoutes.put(path, handler);
    }


    
    public static AppService findHandler(String method, String path) {
        if ("GET".equalsIgnoreCase(method)) {
            return getInstance().getRoutes.get(path);
        } else if ("POST".equalsIgnoreCase(method)) {
            return getInstance().postRoutes.get(path);
        } else {
 
            return null;
        }
    }

}