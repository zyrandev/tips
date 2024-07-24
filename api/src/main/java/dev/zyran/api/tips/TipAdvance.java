package dev.zyran.api.tips;

import java.time.Instant;

public interface TipAdvance {

    String id();

    String employeeId();

    float amount();

    String notes();

    Instant createdAt();

}
