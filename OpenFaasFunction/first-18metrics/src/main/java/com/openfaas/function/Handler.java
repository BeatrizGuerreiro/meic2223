package com.openfaas.function;

import com.openfaas.model.AbstractHandler;
import com.openfaas.model.IRequest;
import com.openfaas.model.IResponse;
import com.openfaas.model.Response;
import java.lang.String;
import metrics.Metric;
import metrics.MetricsLib;

public class Handler extends AbstractHandler {
  public IResponse Handle(IRequest req) {
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

    String end = metrics.end();
    IResponse res = new Response();
    res.setBody(end);
    return res;
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
