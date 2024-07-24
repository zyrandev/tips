package dev.zyran.api.tips;

import java.time.Instant;

public interface TipPunishment {

    String id();

    String employee();

    String reason();

    String amount();

    Instant createdAt();

}
