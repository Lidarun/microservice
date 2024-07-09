package com.lidarunium.afpf.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ButtonType {
    BACK("Back"),
    SAVE("Save"),
    SALARY("Salary"),
    EDIT("Edit"),
    CANCEL("Cancel"),
    INCOME("Income"),
    EXPENSE("Expense");

    private final String value;
}
