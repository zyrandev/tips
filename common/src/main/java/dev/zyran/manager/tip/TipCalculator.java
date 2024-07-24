package dev.zyran.manager.tip;

import dev.zyran.api.tips.EmployeeTipResumeItem;
import dev.zyran.api.tips.Tip;
import dev.zyran.api.tips.TipPunishment;
import dev.zyran.manager.tip.punishments.CalculablePunishment;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


public class TipCalculator {

    private final Map<String, CalculablePunishment> punishments;
    private final Tip tip;
    private final Collection<String> employees;

    public TipCalculator(Collection<TipPunishment> punishments, Tip tip, Collection<String> employees) {
        this.punishments = punishments.stream()
                .collect(Collectors.toMap(TipPunishment::employee, CalculablePunishment::of));
        this.tip = tip;
        this.employees = employees;
    }

    public Map<String, EmployeeTipResumeItem> calculate() {
        BigDecimal totalTips = tip.amount();
        BigDecimal tipPerEmployee = totalTips.divide(BigDecimal.valueOf(employees.size()), RoundingMode.HALF_DOWN);

        Set<String> punishmentEmployees = employees.stream()
                .filter(punishments::containsKey)
                .collect(Collectors.toSet());
        Set<String> noPunishmentEmployees = employees.stream()
                .filter(employee -> !punishmentEmployees.contains(employee))
                .collect(Collectors.toSet());

        Map<String, EmployeeTipResumeItem> resumes = new HashMap<>();

        for (String punishmentEmployee : punishmentEmployees) {
            CalculablePunishment calculablePunishment = punishments.get(punishmentEmployee);
            BigDecimal finalTip = calculablePunishment.calculate(tipPerEmployee)
                    .setScale(0, RoundingMode.DOWN);
            resumes.put(punishmentEmployee, new EmployeeTipResumeItemImpl(punishmentEmployee, finalTip, calculablePunishment));
            totalTips = totalTips.subtract(finalTip);
        }

        BigDecimal remainingTips = totalTips.divide(BigDecimal.valueOf(noPunishmentEmployees.size()), RoundingMode.HALF_DOWN)
                .setScale(0, RoundingMode.DOWN);

        resumes.putAll(noPunishmentEmployees.stream()
                .collect(Collectors.toMap(employee -> employee, employee -> new EmployeeTipResumeItemImpl(employee, remainingTips))));
        return new HashMap<>(resumes);
    }

}
