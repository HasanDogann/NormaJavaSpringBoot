package com.example.bankingsystem.model.entity.exchange;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Author Hasan DOGAN
 * BankingSystemApplication.java
 * 25.05.2022
 */
public class ExchangeQuery {
    public int amount;
    public String from;
    @JsonProperty("to")
    public String myTo;
}
