package dev.zyran.manager.tip.punishments;

import dev.zyran.api.tips.TipPunishment;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.regex.Matcher;

import static dev.zyran.manager.tip.punishments.PercentagePunishment.PERCENTAGE_PATTERN;

public interface CalculablePunishment extends TipPunishment {

    CalculablePunishment NULL = NullPunishment.INSTANCE;

    BigDecimal calculate(BigDecimal total);

    static CalculablePunishment of(TipPunishment punishment) {
        return of(punishment.id(), punishment.employee(), punishment.reason(), punishment.amount(), punishment.createdAt());
    }

    static CalculablePunishment of(String id, String employee, String reason, String amount, Instant createdAt) {
        Matcher matcher = PERCENTAGE_PATTERN.matcher(amount);
        if (matcher.matches()) {
            return percentage(id, employee, reason, amount, createdAt);
        }
        return fixed(id, employee, reason, amount, createdAt);
    }

    static CalculablePunishment fixed(String id, String employee, String reason, String amount, Instant createdAt) {
        return new FixedPunishment(id, employee, reason, amount, createdAt);
    }

    static CalculablePunishment percentage(String id, String employee, String reason, String amount, Instant createdAt) {
        return new PercentagePunishment(id, employee, reason, amount, createdAt);
    }

}
