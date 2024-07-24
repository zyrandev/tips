package dev.zyran.manager.tip.punishments;

import java.math.BigDecimal;
import java.time.Instant;

public class NullPunishment extends AbstractPunishment {

    public static final NullPunishment INSTANCE = new NullPunishment();

    private NullPunishment() {
        super("id", "employee", "reason", "amount", Instant.now());
    }

    @Override
    public BigDecimal calculate(BigDecimal total) {
        return BigDecimal.ZERO;
    }
}
