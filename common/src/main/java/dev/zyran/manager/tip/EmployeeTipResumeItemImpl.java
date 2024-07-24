package dev.zyran.manager.tip;

import dev.zyran.api.tips.EmployeeTipResumeItem;
import dev.zyran.api.tips.TipPunishment;

import java.math.BigDecimal;

public class EmployeeTipResumeItemImpl implements EmployeeTipResumeItem {

    private final String id;
    private final BigDecimal tip;
    private final TipPunishment punishment;

    public EmployeeTipResumeItemImpl(String id, BigDecimal tip, TipPunishment punishment) {
        this.id = id;
        this.tip = tip;
        this.punishment = punishment;
    }

    public EmployeeTipResumeItemImpl(String id, BigDecimal tip) {
        this(id, tip, null);
    }

    @Override
    public String toString() {
        return "EmployeeTipResume(" +
                "id='" + id + '\'' +
                ", tips=" + tip.toPlainString() +
                ')';
    }

    @Override
    public String id() {
        return id;
    }

    @Override
    public BigDecimal tip() {
        return tip;
    }

    @Override
    public TipPunishment punishment() {
        return punishment;
    }
}
