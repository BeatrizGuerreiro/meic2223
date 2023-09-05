import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.util.Base64;

public class OpenFaaSFunction {

    private static final String REGISTRY = "ghcr.io/beatrizguerreiro";
    private static final String HOST = "http://172.29.98.99:8080";
    private static final String SYSTEM_FUNCTIONS = "/system/functions";
    private static final String FUNCTION = "/function/%s";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "JFZWuPeycd7NvP8qpm9rc98kU2xOI5ejEQmXLzASGhXaC76iyNHpirFXiLqwnV7";


    public static void main(String[] args) throws Exception {
        //test1("first-nolib");
        //test2("image-nolib");

        //test1("first-lib");
        test2("image-lib");

        //test1("first-3metrics");
        //test1("first-18metrics");
        //test1("first-36metrics");
        //test1("first-75metrics");
        //test1("first-150metrics");
    }

    private static void test1(String function) throws IOException, InterruptedException {
        for (int i = 0; i < 100; i++) {
            invoke(function);
        }
    }

    private static void test2(String function) throws Exception {
        final String filename = "hd.jpg";
        final String image = readFile(filename);
        for (int i = 0; i < 100; i++) {
            final String bkImage = invoke(function, image);
            //writeFile(bkImage, filename);
        }
    }

    public static String listFunctions() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(HOST + SYSTEM_FUNCTIONS))
                .header("Authorization", "Basic " + Base64.getEncoder().encodeToString((USERNAME + ":" + PASSWORD).getBytes()))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public static void post(String functionName) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString("{\"image\":\"" + REGISTRY + "/" + functionName + ":latest\", \"service\": \"" + functionName + "\"}"))
                .uri(URI.create(HOST + SYSTEM_FUNCTIONS))
                .header("Authorization", "Basic " + Base64.getEncoder().encodeToString((USERNAME + ":" + PASSWORD).getBytes()))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static void invoke(String functionName) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.noBody())
                .uri(URI.create(HOST + String.format(FUNCTION, functionName)))
                .header("Authorization", "Basic " + Base64.getEncoder().encodeToString((USERNAME + ":" + PASSWORD).getBytes()))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

    public static String invoke(String functionName, String image) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(image))
                .uri(URI.create(HOST + String.format(FUNCTION, functionName)))
                .header("Authorization", "Basic " + Base64.getEncoder().encodeToString((USERNAME + ":" + PASSWORD).getBytes()))
                .header("Content-Type", "image/png")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        return response.body();
    }

    public static String readFile(String fileName) throws IOException {
        byte[] fileContent = FileUtils.readFileToByteArray(new File(Paths.get(fileName).toAbsolutePath().toString()));
        return Base64.getEncoder().encodeToString(fileContent);
    }

    public static void writeFile(String data, String filename) throws Exception {
        byte[] decodedBytes = Base64.getDecoder().decode(data);
        FileUtils.writeByteArrayToFile(new File("BK" + filename), decodedBytes);
        System.out.println("image created");
    }
}
