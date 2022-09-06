package com.roady.app.entities;

import lombok.ToString;


@ToString
public enum PaymentType {

    CASH,
    REVOLUT,
    BANK_TRANSACTION,
    FREE
}
