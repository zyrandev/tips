package dev.zyran.manager.tip.punishments;

import dev.zyran.api.tips.TipPunishment;

import java.math.BigDecimal;
import java.time.Instant;

public abstract class AbstractPunishment implements TipPunishment, CalculablePunishment {

    protected final String id;
    protected final String employee;
    protected final String reason;
    protected final String amount;
    protected final Instant createdAt;

    protected AbstractPunishment(String id, String employee, String reason, String amount, Instant createdAt) {
        this.id = id;
        this.employee = employee;
        this.reason = reason;
        this.amount = amount;
        this.createdAt = createdAt;
    }

    public abstract BigDecimal calculate(BigDecimal total);

    @Override
    public String id() {
        return id;
    }

    @Override
    public String employee() {
        return employee;
    }

    @Override
    public String reason() {
        return reason;
    }

    @Override
    public String amount() {
        return amount;
    }

    @Override
    public Instant createdAt() {
        return createdAt;
    }

}
