package edu.eci.arep;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import edu.eci.arep.service.AppService;


public class HttpServer {

    public static final String TEXT_PLAIN = "text/plain";
    public static final String TEXT_HTML = "text/html";
    public static final String TEXT_CSS = "text/css";
    public static final String APPLICATION_JAVASCRIPT = "application/javascript";
    public static final String IMAGE_PNG = "image/png";
    public static final String IMAGE_JPG = "image/jpg";
    public static String baseStaticFileURI;
    private static HttpServer instance;
    private boolean running = false;

    private HttpServer() {
    }


    public static HttpServer getInstance() {
        if (instance == null) {
            instance = new HttpServer();
        }
        return instance;
    }

    public void start() throws IOException, URISyntaxException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

        running = true;
        while (running) {
            Socket clientSocket = null;
            try {
                System.out.println("\nListo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine;
            boolean firstLine = true;
            String uriStr = "";
            String httpMethod = "";

            while ((inputLine = in.readLine()) != null) {
                if (firstLine) {
                    System.out.println("Received: " + inputLine);
                    httpMethod = inputLine.split(" ")[0];
                    uriStr = inputLine.split(" ")[1];
                    firstLine = false;
                }
                if (!in.ready()) {
                    break;
                }
            }

            URI requestURI = new URI(uriStr);
            String contentType = TEXT_PLAIN;
            byte[] responseHeader;
            byte[] responseBody;

            try {
                if (requestURI.getPath().startsWith("/action")) {
                    responseBody = callService(requestURI, httpMethod);
                } else {
                    URI localURI = new URI(baseStaticFileURI + requestURI.getPath());
                    contentType = determineContentType(localURI);
                    responseBody = httpResponseBody(contentType, localURI);
                }
            } catch (IOException e) {
                URI localURI = new URI(baseStaticFileURI + "/404.html");
                contentType = TEXT_HTML;
                responseBody = httpResponseBody(TEXT_HTML, localURI);
            }
            responseHeader = httpResponseHeader(contentType);

            try (OutputStream os = clientSocket.getOutputStream()) {
                os.write(responseHeader);
                os.write(responseBody);
            } catch (IOException e) {
                System.out.println("Error sending response body");
            }

            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    }

    private byte[] callService(URI serviceURI, String httpMethod) throws IOException {
        String calledServiceURI = serviceURI.getPath().substring(7);
        AppService handlerService = Spark.findHandler(httpMethod, calledServiceURI);
        byte[] responseBody = handlerService.handle(serviceURI);
        return responseBody;
    }

    private String determineContentType(URI resourceURI) {
        String contentType = TEXT_PLAIN;
        String path = resourceURI.getPath();
        if (path.endsWith(".html")) {
            contentType = TEXT_HTML;
        } else if (path.endsWith(".css")) {
            contentType = TEXT_CSS;
        } else if (path.endsWith(".js")) {
            contentType = APPLICATION_JAVASCRIPT;
        } else if (path.endsWith(".png")) {
            contentType = IMAGE_PNG;
        } else if (path.endsWith(".jpg")) {
            contentType = IMAGE_JPG;
        }
        return contentType;
    }

    public byte[] httpResponseBody(String contentType, URI resourceURI) throws IOException {
        if (contentType.startsWith("image")) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BufferedImage image = ImageIO.read(new File(resourceURI.getPath()));
            String formatName = contentType.split("/")[1];
            ImageIO.write(image, formatName, baos);
            return baos.toByteArray();
        }
        return Files.readAllBytes(Paths.get(resourceURI.getPath()));
    }


    public byte[] httpResponseHeader(String contentType) {
        String responseHeader = "HTTP/1.1 200 OK\r\n"
                + "Accept-Ranges: bytes\r\n"
                + "Server: jose's Java HTTP Server\r\n"
                + "Content-Type: " + contentType + "\r\n"
                + "\r\n";
        return responseHeader.getBytes();
    }


    public boolean isRunning() {
        return running;
    }


    public void setStaticFileLocation(String path) {
        baseStaticFileURI = path;
    }

}
