package metrics;

import java.io.Serializable;

public class Metric implements Serializable {

    private final String name;
    private final long value;
    private final long timestamp;

    public Metric(String name, long value, long timestamp) {
        this.name = name;
        this.value = value;
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public long getValue() {
        return value;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
