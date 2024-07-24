package dev.zyran.internal.functions;

import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.core.JsonProcessingException;
import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.databind.ObjectMapper;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPResponse;
import com.amazonaws.services.lambda.runtime.serialization.factories.JacksonFactory;

public final class JsonHTTPResponseBuilder {

    private JsonHTTPResponseBuilder() {
    }

    public static APIGatewayV2HTTPResponse.APIGatewayV2HTTPResponseBuilder builder(Object body) {
        ObjectMapper objectMapper = JacksonFactory.getInstance().getMapper();
        String raw;
        try {
            raw = objectMapper.writeValueAsString(body);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return APIGatewayV2HTTPResponse.builder()
                .withBody(raw);
    }

}
