package metrics;

import okhttp3.*;
import org.json.JSONArray;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.time.Instant;
import java.util.*;

public class MetricsLib {

    private static final String HOST = "http://192.168.1.8:8080/api/metrics";

    private final Map<String, Integer> startTime = new HashMap<>();
    private final Map<String, Long> incCounter = new HashMap<>();

    private final List<Metric> metrics = new ArrayList<>();

    public String startTime(String id) {
        final Instant start = Instant.now();
        startTime.put(id, start.getNano());
        return id + ": Start time = " + start;
    }

    public String endTime(String id) {
        final Instant end = Instant.now();
        int time = end.getNano() - startTime.get(id);
        metrics.add(new Metric("time:" + id + ":" + getLocalAddress(), time, end.getNano()));
        return id + ": End time = " + end;
    }

    public String incCounter(String id) {
        long counter = incCounter.merge(id, 1L, (k, v) -> ++v);
        metrics.add(new Metric("counter:" + id + ":" + getLocalAddress(), counter, Instant.now().getNano()));
        return id + ": Counter = " + counter;
    }

    public String end() {
        JSONArray array = new JSONArray(metrics);
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(array.toString(),
                MediaType.parse("application/json"));

        Request request = new Request.Builder()
                .headers(Headers.of("Content-Type", "application/json"))
                .url(HOST).post(body).build();
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        } finally {
            metrics.clear();
        }
    }

    private static InetAddress getLocalAddress(){
        try {
            Enumeration<NetworkInterface> b = NetworkInterface.getNetworkInterfaces();
            while(b.hasMoreElements()) {
                for (InterfaceAddress f : b.nextElement().getInterfaceAddresses()) {
                    if (f.getAddress().isSiteLocalAddress()) {
                        return f.getAddress();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return null;
    }
}
