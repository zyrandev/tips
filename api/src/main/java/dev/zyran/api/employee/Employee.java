package dev.zyran.api.employee;

import java.time.Instant;

public interface Employee {

    String id();

    String name();

    String displayName();

    Instant joinDate();

}
