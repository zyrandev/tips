package dev.zyran.internal.serializer;

import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.annotation.JsonProperty;
import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.core.JsonParser;
import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.databind.DeserializationContext;
import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.databind.JsonDeserializer;
import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.databind.JsonNode;
import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dev.zyran.manager.tip.punishments.CalculablePunishment;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;

@JsonDeserialize(using = Punishment.Deserializer.class)
public class Punishment implements CalculablePunishment {

    private final CalculablePunishment punishment;

    public Punishment(CalculablePunishment punishment) {
        this.punishment = punishment;
    }

    @Override
    public BigDecimal calculate(BigDecimal total) {
        return punishment.calculate(total);
    }

    @JsonProperty("id")
    @Override
    public String id() {
        return punishment.id();
    }

    @JsonProperty("employee")
    @Override
    public String employee() {
        return punishment.employee();
    }

    @JsonProperty("reason")
    @Override
    public String reason() {
        return punishment.reason();
    }

    @JsonProperty("amount")
    @Override
    public String amount() {
        return punishment.amount();
    }

    @JsonProperty("createdAt")
    @Override
    public Instant createdAt() {
        return punishment.createdAt();
    }

    public static class Deserializer extends JsonDeserializer<Punishment> {

        @Override
        public Punishment deserialize(JsonParser parser, DeserializationContext context) throws IOException {
            JsonNode node = parser.getCodec().readTree(parser);
            return new Punishment(CalculablePunishment.of(
                    node.get("id").asText(),
                    node.get("employee").asText(),
                    node.get("reason").asText(),
                    node.get("amount").asText(),
                    Instant.now()
            ));
        }
    }
}
