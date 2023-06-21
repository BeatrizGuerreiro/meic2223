import com.openfaas.model.IResponse;
import com.openfaas.model.Request;
import org.junit.Test;
import static org.junit.Assert.*;

import com.openfaas.function.Handler;
import com.openfaas.model.IHandler;

import java.util.HashMap;

public class HandlerTest {
    @Test public void handlerIsNotNull() {
        IHandler handler = new Handler();
        assertTrue("Expected handler not to be null", handler != null);

        Request request = new Request("", new HashMap<>());
        IResponse response = handler.Handle(request);
        System.out.println(response.getBody());
    }
}
