package com.openfaas.function;

import com.openfaas.model.IRequest;
import com.openfaas.model.IResponse;
import com.openfaas.model.Response;
import metrics.MetricsLib;

public class Handler extends com.openfaas.model.AbstractHandler {

    public IResponse Handle(IRequest req) {
        System.out.println("Started!");
        final MetricsLib metrics = new MetricsLib();

        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Hello, world!\n");

        stringBuilder.append(metrics.startTime("func1func2Id")).append("\n");
        stringBuilder.append(func1()).append("\n");
        stringBuilder.append(func2()).append("\n");
        stringBuilder.append(metrics.endTime("func1func2Id")).append("\n");

        if(true) {
            stringBuilder.append(func3()).append("\n");
            stringBuilder.append(metrics.incCounter("func3Id")).append("\n");
        }

        String end = metrics.end();

        System.out.println(stringBuilder);
        Response res = new Response();
        res.setBody(stringBuilder + "\n" + end);

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
}
