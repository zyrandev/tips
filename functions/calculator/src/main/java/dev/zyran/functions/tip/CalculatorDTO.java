package dev.zyran.functions.tip;


import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.annotation.JsonCreator;
import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.annotation.JsonProperty;
import dev.zyran.internal.serializer.JsonTip;
import dev.zyran.internal.serializer.Punishment;

import java.util.List;

public class CalculatorDTO {

    private final List<String> employees;
    private final List<Punishment> punishments;
    private final JsonTip tip;

    @JsonCreator
    public CalculatorDTO(
            @JsonProperty("employees") List<String> employees,
            @JsonProperty("punishments") List<Punishment> punishments,
            @JsonProperty("tip") JsonTip tip
    ) {
        this.employees = employees;
        this.punishments = punishments;
        this.tip = tip;
    }

    @JsonProperty("employees")
    public List<String> employees() {
        return employees;
    }

    @JsonProperty("punishments")
    public List<Punishment> punishments() {
        return punishments;
    }

    @JsonProperty("tip")
    public JsonTip tip() {
        return tip;
    }

}
