package functions;

import com.google.cloud.functions.HttpFunction;
import com.google.cloud.functions.HttpRequest;
import com.google.cloud.functions.HttpResponse;
import java.io.BufferedWriter;
import java.io.IOException;

public class Handler implements HttpFunction {
  
	@Override
	public void service(HttpRequest request, HttpResponse response) throws IOException {
		System.out.println("Started!");

        System.out.println(func1());
        System.out.println(func2());

        if (true) {
            System.out.println(func3());
        }

        for (int i = 0; i < 4; i++) {
            System.out.println(func4());
        }

		BufferedWriter writer = response.getWriter();
		writer.write("Finished");
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