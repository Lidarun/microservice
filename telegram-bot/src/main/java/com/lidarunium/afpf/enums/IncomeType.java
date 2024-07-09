package com.lidarunium.afpf.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum IncomeType {
    SALARY("Зарплата"),
    OTHER("Другое"),
    DEPOSIT("Проценты с депозита");

    private final String description;
}
