package dev.zyran.internal.functions;

import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.databind.ObjectMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.amazonaws.services.lambda.runtime.serialization.factories.JacksonFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class JsonController<Req, Res> implements RequestStreamHandler {

    private final Class<Req> requestClass;

    public JsonController(Class<Req> requestClass) {
        this.requestClass = requestClass;
    }

    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        ObjectMapper objectMapper = JacksonFactory.getInstance().getMapper();
        Req req = objectMapper.readValue(inputStream, requestClass);
        Res res = handleJsonRequest(objectMapper, req, context);
        objectMapper.writeValue(outputStream, res);
        outputStream.flush();
    }

    protected Res handleJsonRequest(ObjectMapper objectMapper, Req req, Context context) {
        return handleRequest(req, context);
    }

    protected abstract Res handleRequest(Req request, Context context);

}
