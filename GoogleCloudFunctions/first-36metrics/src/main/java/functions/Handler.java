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


    final String id1 = metrics.startTime();
    System.out.println(func1());
    System.out.println(func2());
    metrics.endTime(id1);


    final String id2 = metrics.startTime();
    System.out.println(func1());
    System.out.println(func2());
    metrics.endTime(id2);


    final String id3 = metrics.startTime();
    System.out.println(func1());
    System.out.println(func2());
    metrics.endTime(id3);


    final String id4 = metrics.startTime();
    System.out.println(func1());
    System.out.println(func2());
    metrics.endTime(id4);


    final String id5 = metrics.startTime();
    System.out.println(func1());
    System.out.println(func2());
    metrics.endTime(id5);


    final String id6 = metrics.startTime();
    System.out.println(func1());
    System.out.println(func2());
    metrics.endTime(id6);


    final String id7 = metrics.startTime();
    System.out.println(func1());
    System.out.println(func2());
    metrics.endTime(id7);


    final String id8 = metrics.startTime();
    System.out.println(func1());
    System.out.println(func2());
    metrics.endTime(id8);


    final String id9 = metrics.startTime();
    System.out.println(func1());
    System.out.println(func2());
    metrics.endTime(id9);


    final String id10 = metrics.startTime();
    System.out.println(func1());
    System.out.println(func2());
    metrics.endTime(id10);


    final String id11 = metrics.startTime();
    System.out.println(func1());
    System.out.println(func2());
    metrics.endTime(id11);


    if (true) {
      System.out.println(func3());
      metrics.incCounter();
    }


    if (true) {
      System.out.println(func3());
      metrics.incCounter();
    }


    if (true) {
      System.out.println(func3());
      metrics.incCounter();
    }


    if (true) {
      System.out.println(func3());
      metrics.incCounter();
    }


    if (true) {
      System.out.println(func3());
      metrics.incCounter();
    }


    if (true) {
      System.out.println(func3());
      metrics.incCounter();
    }


    if (true) {
      System.out.println(func3());
      metrics.incCounter();
    }


    if (true) {
      System.out.println(func3());
      metrics.incCounter();
    }


    if (true) {
      System.out.println(func3());
      metrics.incCounter();
    }


    if (true) {
      System.out.println(func3());
      metrics.incCounter();
    }


    if (true) {
      System.out.println(func3());
      metrics.incCounter();
    }


    if (true) {
      System.out.println(func3());
      metrics.incCounter();
    }


    Metric metric0 = null;
    for (int i = 0; i < 4; i++) {
      System.out.println(func4());
      metric0 = metrics.incCounter(metric0 != null ? metric0.getName() : null);
    }


    Metric metric1 = null;
    for (int i = 0; i < 4; i++) {
      System.out.println(func4());
      metric1 = metrics.incCounter(metric1 != null ? metric1.getName() : null);
    }


    Metric metric2 = null;
    for (int i = 0; i < 4; i++) {
      System.out.println(func4());
      metric2 = metrics.incCounter(metric2 != null ? metric2.getName() : null);
    }


    Metric metric3 = null;
    for (int i = 0; i < 4; i++) {
      System.out.println(func4());
      metric3 = metrics.incCounter(metric3 != null ? metric3.getName() : null);
    }


    Metric metric4 = null;
    for (int i = 0; i < 4; i++) {
      System.out.println(func4());
      metric4 = metrics.incCounter(metric4 != null ? metric4.getName() : null);
    }


    Metric metric5 = null;
    for (int i = 0; i < 4; i++) {
      System.out.println(func4());
      metric5 = metrics.incCounter(metric5 != null ? metric5.getName() : null);
    }


    Metric metric6 = null;
    for (int i = 0; i < 4; i++) {
      System.out.println(func4());
      metric6 = metrics.incCounter(metric6 != null ? metric6.getName() : null);
    }


    Metric metric7 = null;
    for (int i = 0; i < 4; i++) {
      System.out.println(func4());
      metric7 = metrics.incCounter(metric7 != null ? metric7.getName() : null);
    }


    Metric metric8 = null;
    for (int i = 0; i < 4; i++) {
      System.out.println(func4());
      metric8 = metrics.incCounter(metric8 != null ? metric8.getName() : null);
    }


    Metric metric9 = null;
    for (int i = 0; i < 4; i++) {
      System.out.println(func4());
      metric9 = metrics.incCounter(metric9 != null ? metric9.getName() : null);
    }


    Metric metric10 = null;
    for (int i = 0; i < 4; i++) {
      System.out.println(func4());
      metric10 = metrics.incCounter(metric10 != null ? metric10.getName() : null);
    }


    Metric metric11 = null;
    for (int i = 0; i < 4; i++) {
      System.out.println(func4());
      metric11 = metrics.incCounter(metric11 != null ? metric11.getName() : null);
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
