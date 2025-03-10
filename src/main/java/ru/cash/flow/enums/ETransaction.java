package ru.cash.flow.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ETransaction {
    INCOME("Доход"),
    EXPENSE("Расход");

    private final String value;

    ETransaction(String value) {
        this.value = value;
    }

    public static boolean isExists(String type) {
        return Arrays.stream(ETransaction.values())
                .anyMatch(transactionType -> transactionType.toString().equals(type));
    }
}