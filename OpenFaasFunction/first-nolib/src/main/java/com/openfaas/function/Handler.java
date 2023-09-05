package com.openfaas.function;

import com.openfaas.model.IRequest;
import com.openfaas.model.IResponse;
import com.openfaas.model.Response;

public class Handler extends com.openfaas.model.AbstractHandler {

    public IResponse Handle(IRequest req) {

        System.out.println("Started!");

        System.out.println(func1());
        System.out.println(func2());

        if (true) {
            System.out.println(func3());
        }

        for (int i = 0; i < 4; i++) {
            System.out.println(func4());
        }

        IResponse res = new Response();
        res.setBody("Finished");
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
