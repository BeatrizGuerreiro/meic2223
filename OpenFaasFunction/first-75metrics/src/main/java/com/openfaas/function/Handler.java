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


    final String id12 = metrics.startTime();
    System.out.println(func1());
    System.out.println(func2());
    metrics.endTime(id12);


    final String id13 = metrics.startTime();
    System.out.println(func1());
    System.out.println(func2());
    metrics.endTime(id13);


    final String id14 = metrics.startTime();
    System.out.println(func1());
    System.out.println(func2());
    metrics.endTime(id14);


    final String id15 = metrics.startTime();
    System.out.println(func1());
    System.out.println(func2());
    metrics.endTime(id15);


    final String id16 = metrics.startTime();
    System.out.println(func1());
    System.out.println(func2());
    metrics.endTime(id16);


    final String id17 = metrics.startTime();
    System.out.println(func1());
    System.out.println(func2());
    metrics.endTime(id17);


    final String id18 = metrics.startTime();
    System.out.println(func1());
    System.out.println(func2());
    metrics.endTime(id18);


    final String id19 = metrics.startTime();
    System.out.println(func1());
    System.out.println(func2());
    metrics.endTime(id19);


    final String id20 = metrics.startTime();
    System.out.println(func1());
    System.out.println(func2());
    metrics.endTime(id20);


    final String id21 = metrics.startTime();
    System.out.println(func1());
    System.out.println(func2());
    metrics.endTime(id21);


    final String id22 = metrics.startTime();
    System.out.println(func1());
    System.out.println(func2());
    metrics.endTime(id22);


    final String id23 = metrics.startTime();
    System.out.println(func1());
    System.out.println(func2());
    metrics.endTime(id23);


    final String id24 = metrics.startTime();
    System.out.println(func1());
    System.out.println(func2());
    metrics.endTime(id24);


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


    Metric metric12 = null;
    for (int i = 0; i < 4; i++) {
      System.out.println(func4());
      metric12 = metrics.incCounter(metric12 != null ? metric12.getName() : null);
    }


    Metric metric13 = null;
    for (int i = 0; i < 4; i++) {
      System.out.println(func4());
      metric13 = metrics.incCounter(metric13 != null ? metric13.getName() : null);
    }


    Metric metric14 = null;
    for (int i = 0; i < 4; i++) {
      System.out.println(func4());
      metric14 = metrics.incCounter(metric14 != null ? metric14.getName() : null);
    }


    Metric metric15 = null;
    for (int i = 0; i < 4; i++) {
      System.out.println(func4());
      metric15 = metrics.incCounter(metric15 != null ? metric15.getName() : null);
    }


    Metric metric16 = null;
    for (int i = 0; i < 4; i++) {
      System.out.println(func4());
      metric16 = metrics.incCounter(metric16 != null ? metric16.getName() : null);
    }


    Metric metric17 = null;
    for (int i = 0; i < 4; i++) {
      System.out.println(func4());
      metric17 = metrics.incCounter(metric17 != null ? metric17.getName() : null);
    }


    Metric metric18 = null;
    for (int i = 0; i < 4; i++) {
      System.out.println(func4());
      metric18 = metrics.incCounter(metric18 != null ? metric18.getName() : null);
    }


    Metric metric19 = null;
    for (int i = 0; i < 4; i++) {
      System.out.println(func4());
      metric19 = metrics.incCounter(metric19 != null ? metric19.getName() : null);
    }


    Metric metric20 = null;
    for (int i = 0; i < 4; i++) {
      System.out.println(func4());
      metric20 = metrics.incCounter(metric20 != null ? metric20.getName() : null);
    }


    Metric metric21 = null;
    for (int i = 0; i < 4; i++) {
      System.out.println(func4());
      metric21 = metrics.incCounter(metric21 != null ? metric21.getName() : null);
    }


    Metric metric22 = null;
    for (int i = 0; i < 4; i++) {
      System.out.println(func4());
      metric22 = metrics.incCounter(metric22 != null ? metric22.getName() : null);
    }


    Metric metric23 = null;
    for (int i = 0; i < 4; i++) {
      System.out.println(func4());
      metric23 = metrics.incCounter(metric23 != null ? metric23.getName() : null);
    }


    Metric metric24 = null;
    for (int i = 0; i < 4; i++) {
      System.out.println(func4());
      metric24 = metrics.incCounter(metric24 != null ? metric24.getName() : null);
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
