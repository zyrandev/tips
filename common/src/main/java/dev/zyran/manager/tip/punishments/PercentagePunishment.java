package dev.zyran.manager.tip.punishments;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PercentagePunishment extends AbstractPunishment {

    public static final Pattern PERCENTAGE_PATTERN = Pattern.compile("(\\d+(?:\\.\\d+)?)%");

    private final BigDecimal percentage;

    public PercentagePunishment(String id, String employee, String reason, String amount, Instant createdAt) {
        super(id, employee, reason, amount, createdAt);
        Matcher matcher = PERCENTAGE_PATTERN.matcher(amount);
        if (!matcher.matches() || matcher.groupCount() != 1 || matcher.group(1) == null) {
            throw new IllegalArgumentException("Invalid percentage format: " + amount);
        }
        BigDecimal raw = new BigDecimal(matcher.group(1));

        if (raw.compareTo(BigDecimal.ZERO) < 0 || raw.compareTo(BigDecimal.valueOf(100)) > 0) {
            throw new IllegalArgumentException("Percentage must be between 0 and 100: " + raw);
        }
        this.percentage = BigDecimal.valueOf(raw.doubleValue() / 100);
    }

    @Override
    public BigDecimal calculate(BigDecimal total) {
        return total.subtract(total.multiply(percentage));
    }
}
