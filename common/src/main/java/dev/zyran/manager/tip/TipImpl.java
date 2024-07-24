package dev.zyran.manager.tip;

import dev.zyran.api.tips.Tip;

import java.math.BigDecimal;
import java.time.Instant;

public class TipImpl implements Tip {

    private final String id;
    private final BigDecimal amount;
    private final Instant createdAt;

    public TipImpl(String id, BigDecimal amount, Instant createdAt) {
        this.id = id;
        this.amount = amount;
        this.createdAt = createdAt;
    }


    @Override
    public String id() {
        return id;
    }

    @Override
    public BigDecimal amount() {
        return amount;
    }

    @Override
    public Instant createdAt() {
        return createdAt;
    }
}
