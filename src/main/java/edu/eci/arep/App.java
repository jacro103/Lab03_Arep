package edu.eci.arep;

import com.google.gson.JsonObject;

import edu.eci.arep.service.HttpMovieConnection;
import edu.eci.arep.service.GameStoreService;


public class App {

    public static void main(String[] args) {
        // Set the static file location
        Spark.staticFileLocation("target/classes/public");

        // Define the services
        Spark.get("/movie", (requestURI) -> {
            HttpMovieConnection service = new HttpMovieConnection();
            JsonObject response = service.get(requestURI.getQuery());
            return response.toString().getBytes();
        });
        Spark.post("/gamestore", (requestURI) -> {
            GameStoreService service = GameStoreService.getInstance();
            JsonObject response = service.addControl(requestURI.getQuery());
            return response.toString().getBytes();
        });

        // Start the server
        try {
            if (!HttpServer.getInstance().isRunning())
                HttpServer.getInstance().start();
        } catch (Exception e) {
            System.err.println("Error en el servidor");
            System.exit(1);
        }
    }

}