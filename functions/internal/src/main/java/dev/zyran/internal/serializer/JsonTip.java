package dev.zyran.internal.serializer;

import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.annotation.JsonCreator;
import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.annotation.JsonProperty;
import dev.zyran.api.tips.Tip;
import dev.zyran.manager.tip.TipImpl;

import java.math.BigDecimal;
import java.time.Instant;

public class JsonTip extends TipImpl implements Tip {

    @JsonCreator
    public JsonTip(
            @JsonProperty("id") String id,
            @JsonProperty("amount") BigDecimal amount
    ) {
        super(id, amount, Instant.now());
    }

    @JsonProperty("id")
    @Override
    public String id() {
        return super.id();
    }

    @JsonProperty("amount")
    @Override
    public BigDecimal amount() {
        return super.amount();
    }

    @JsonProperty("createdAt")
    @Override
    public Instant createdAt() {
        return super.createdAt();
    }
}
