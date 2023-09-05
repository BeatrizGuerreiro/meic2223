package functions;

import com.google.cloud.functions.HttpFunction;
import com.google.cloud.functions.HttpRequest;
import com.google.cloud.functions.HttpResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import metrics.Metric;
import metrics.MetricsLib;

public class Handler implements HttpFunction {
  
	@Override
	public void service(HttpRequest request, HttpResponse response) throws IOException {
		final MetricsLib metrics = new MetricsLib();

        final String id = metrics.startTime();
        System.out.println(func1());
        System.out.println(func2());
        metrics.endTime(id);

        if(true) {
            System.out.println(func3());
            metrics.incCounter();
        }

        Metric metric = null;
        for (int i = 0; i < 4; i++) {
            System.out.println(func4());
            metric = metrics.incCounter(metric != null ? metric.getName() : null);
        }

        String end = metrics.end();
        BufferedWriter writer = response.getWriter();
		writer.write(end);
	}
  
     public String func1() {
        return "function1";
    }

    public String func2() {
        return "function2";
    }

    public String func3() {
        return "function3";
    }

    public String func4() {
        return "function4";
    }
}