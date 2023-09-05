package functions;

import com.google.cloud.functions.HttpFunction;
import com.google.cloud.functions.HttpRequest;
import com.google.cloud.functions.HttpResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.String;
import metrics.Metric;
import metrics.MetricsLib;

public class Handler implements HttpFunction {
  public void service(HttpRequest request, HttpResponse response) throws IOException {
    System.out.println("Started!");
    final MetricsLib metrics = new MetricsLib();

    final String id0 = metrics.startTime();
    System.out.println(func1());
    System.out.println(func2());
    metrics.endTime(id0);


    if (true) {
      System.out.println(func3());
      metrics.incCounter();
    }


    Metric metric0 = null;
    for (int i = 0; i < 4; i++) {
      System.out.println(func4());
      metric0 = metrics.incCounter(metric0 != null ? metric0.getName() : null);
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
