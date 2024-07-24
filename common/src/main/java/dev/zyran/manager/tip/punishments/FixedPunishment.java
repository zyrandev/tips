package dev.zyran.manager.tip.punishments;

import dev.zyran.api.tips.TipPunishment;

import java.math.BigDecimal;
import java.time.Instant;

public class FixedPunishment extends AbstractPunishment implements TipPunishment {

    private final BigDecimal amount;

    public FixedPunishment(String id, String employee, String reason, String amount, Instant createdAt) {
        super(id, employee, reason, amount, createdAt);
        this.amount = new BigDecimal(amount);
    }

    @Override
    public BigDecimal calculate(BigDecimal total) {
        System.out.printf("FixedPunishment.calculate(%s)%n", total.toPlainString());
        return total.subtract(amount);
    }

}
