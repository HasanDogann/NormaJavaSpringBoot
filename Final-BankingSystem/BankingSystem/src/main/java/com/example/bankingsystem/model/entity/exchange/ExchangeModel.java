package com.example.bankingsystem.model.entity.exchange;

import org.springframework.data.jpa.repository.Query;

/**
 * @author Hasan DOĞAN
 * @Project IntelliJ IDEA
 * @Date 25.05.2022
 */
public class ExchangeModel {
    public String date;
    public String historical;
    public ExchangeInfo info;
    public ExchangeQuery query;
    public double result;
    public boolean success;
}
