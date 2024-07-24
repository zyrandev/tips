package dev.zyran.internal.functions;

import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.databind.ObjectMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPResponse;

public abstract class JsonHTTPController<DTO> extends JsonController<APIGatewayV2HTTPEvent, APIGatewayV2HTTPResponse> {

    private final Class<DTO> dtoClass;

    public JsonHTTPController(Class<DTO> dtoClass) {
        super(APIGatewayV2HTTPEvent.class);
        this.dtoClass = dtoClass;
    }

    @Override
    protected APIGatewayV2HTTPResponse handleRequest(APIGatewayV2HTTPEvent request, Context context) {
        return null;
    }

    @Override
    protected APIGatewayV2HTTPResponse handleJsonRequest(ObjectMapper objectMapper, APIGatewayV2HTTPEvent req, Context context) {
        DTO dto = parseDTO(req, objectMapper);
        return handleInternalRequest(req, dto, context);
    }

    protected APIGatewayV2HTTPResponse handleInternalRequest(APIGatewayV2HTTPEvent event, DTO dto, Context context) {
        return handleInternalRequest(dto, context);
    }

    protected abstract APIGatewayV2HTTPResponse handleInternalRequest(DTO dto, Context context);

    protected DTO parseDTO(APIGatewayV2HTTPEvent req, ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(req.getBody(), dtoClass);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
