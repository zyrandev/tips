package dev.zyran.api.tips;

import java.math.BigDecimal;
import java.time.Instant;

public interface Tip {

    String id();

    BigDecimal amount();

    Instant createdAt();

}
