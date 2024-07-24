package dev.zyran.api.tips;

import java.math.BigDecimal;

public interface EmployeeTipResumeItem {

    String id();

    BigDecimal tip();

    TipPunishment punishment();

}

