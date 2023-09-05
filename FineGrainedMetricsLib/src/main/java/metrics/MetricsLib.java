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

    private static final InetAddress LOCAL_ADDRESS = getLocalAddress();

    private final Map<String, Long> startTime = new HashMap<>();
    private final Map<String, Long> incCounter = new HashMap<>();

    private final Map<String, Metric> metrics = new HashMap<>();

    private final List<String> noServiceIdsList = new ArrayList<>();

    private final boolean useService;
    private final String host;
    private final OkHttpClient client;

    public MetricsLib() {
        useService = Boolean.parseBoolean(getServiceEnv());
        if(useService) {
            this.host = getHostEnv();
            this.client = new OkHttpClient();
        } else {
            this.host = null;
            this.client = null;
        }
    }

    public MetricsLib(boolean sendDataToService, String serviceHost) {
        this.useService = sendDataToService;
        if(sendDataToService) {
            this.host = serviceHost;
            this.client = new OkHttpClient();
        } else {
            this.host = null;
            this.client = null;
        }
    }

    private String getServiceEnv() {
        final String serviceEnv = System.getenv("SERVICE");
        return serviceEnv != null ? serviceEnv : "true";
    }

    private String getHostEnv() {
        final String hostEnv = System.getenv("HOST");
        return hostEnv != null ? hostEnv : "http://192.168.1.244:8080/api/metrics";
    }

    public String startTime() {
        final long start = System.nanoTime();
        final String metricId = getMetricId("time");
        startTime.put(metricId, start);
        return metricId;
    }

    public Metric endTime(String id) {
        final long end = System.nanoTime();
        long time = end - startTime.get(id);
        final Metric metric = new Metric(id, time, getTimestampWithNanoSeconds());
        metrics.put(id, metric);
        return metric;
    }

    public Metric incCounter() {
        final String metricId = getMetricId("counter");
        incCounter.put(metricId, 1L);
        final Metric metric = new Metric(metricId, 1L, getTimestampWithNanoSeconds());
        metrics.put(metricId, metric);
        return metric;
    }

    public Metric incCounter(String id) {
        if(id == null || "".equals(id)) {
            return incCounter();
        }
        long counter = incCounter.merge(id, 1L, Long::sum);
        final Metric metric = new Metric(id, counter, getTimestampWithNanoSeconds());
        metrics.put(id, metric);
        return metric;
    }

    public String end() {
        final JSONArray array = new JSONArray(metrics.values());

        if(!useService) {
            metrics.clear();
            noServiceIdsList.clear();
            return array.toString();
        }

        RequestBody body = RequestBody.create(array.toString(),
                MediaType.parse("application/json"));

        Request request = new Request.Builder()
                .headers(Headers.of("Content-Type", "application/json"))
                .url(host).post(body).build();
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

    private String getMetricId(String metricName) {
        if(!useService) {
            return getMetricIdNoService(metricName);
        }

        final HttpUrl.Builder urlBuilder = HttpUrl.parse(host + "/id").newBuilder()
                .addQueryParameter("metric", metricName)
                .addQueryParameter("address", String.valueOf(LOCAL_ADDRESS));

        Request request = new Request.Builder()
                .headers(Headers.of("Content-Type", "application/json"))
                .url(urlBuilder.build().toString()).build();
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    public String getMetricIdNoService(String metricName) {
        String uniqueId = metricName;
        int index = 1;
        while(noServiceIdsList.contains(uniqueId)) {
            uniqueId = metricName + index;
            index++;
        }
        noServiceIdsList.add(uniqueId);
        return uniqueId;
    }

    private static long getTimestampWithNanoSeconds() {
        final Instant now = Instant.now();
        return now.getEpochSecond() * 1_000_000_000L + now.getNano();
    }

    private static InetAddress getLocalAddress(){
        try {
            Enumeration<NetworkInterface> b = NetworkInterface.getNetworkInterfaces();
            while(b.hasMoreElements()) {
                final List<InterfaceAddress> interfaceAddresses = b.nextElement().getInterfaceAddresses();
                interfaceAddresses.forEach(interfaceAddress -> System.out.println(interfaceAddress.getAddress()));
                for (InterfaceAddress f : interfaceAddresses) {
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
