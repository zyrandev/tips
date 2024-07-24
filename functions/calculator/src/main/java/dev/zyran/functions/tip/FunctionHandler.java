package dev.zyran.functions.tip;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPResponse;
import dev.zyran.api.tips.EmployeeTipResumeItem;
import dev.zyran.internal.functions.JsonHTTPController;
import dev.zyran.internal.functions.JsonHTTPResponseBuilder;
import dev.zyran.manager.tip.TipCalculator;

import java.util.HashSet;
import java.util.Map;

public class FunctionHandler extends JsonHTTPController<CalculatorDTO> {

    public FunctionHandler() {
        super(CalculatorDTO.class);
    }

    @Override
    protected APIGatewayV2HTTPResponse handleInternalRequest(CalculatorDTO dto, Context context) {
        TipCalculator tipCalculator = new TipCalculator(
                new HashSet<>(dto.punishments()),
                dto.tip(),
                dto.employees()
        );
        Map<String, EmployeeTipResumeItem> calculate = tipCalculator.calculate();
        context.getLogger().log("Calculated: " + calculate);
        return JsonHTTPResponseBuilder.builder(calculate)
                .withStatusCode(200)
                .build();
    }
}
