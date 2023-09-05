import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.util.Base64;

public class GoogleCloudFunction {

    private static final String HOST = "https://europe-west1-tfm-faas-monitoring.cloudfunctions.net/%s";

    public static void main(String[] args) throws Exception {
        test1("first-nolib");
        test2("image-nolib");

        test1("first-noservice");
        test2("image-noservice");

        //test1("first-service");
        //test2("image-service");

        test1("first-3metrics");
        test1("first-18metrics");
        test1("first-36metrics");
        test1("first-75metrics");
        test1("first-150metrics");
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

    public static void invoke(String functionName) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.noBody())
                .uri(URI.create(String.format(HOST, functionName)))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

    public static String invoke(String functionName, String image) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(image))
                .uri(URI.create(String.format(HOST, functionName)))
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
