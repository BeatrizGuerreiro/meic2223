package com.openfaas.function;

import com.openfaas.model.IRequest;
import com.openfaas.model.IResponse;
import com.openfaas.model.Response;
import metrics.Metric;
import metrics.MetricsLib;

public class Handler extends com.openfaas.model.AbstractHandler {

    public IResponse Handle(IRequest req) {
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