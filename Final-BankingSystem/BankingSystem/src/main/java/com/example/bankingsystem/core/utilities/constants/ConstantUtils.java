package com.example.bankingsystem.core.utilities.constants;

import java.math.BigDecimal;
import java.util.Random;

public class ConstantUtils {

    private static final BigDecimal MAX_TRANSACTION_LIMIT = BigDecimal.valueOf(5000L);
    private static Long accountNo = new Random().nextLong(1000_000_00, 9999_999_99);
    private static String IBAN = "TR" + new BigDecimal(new Random().nextLong(1000_000_00, 9999_999_99)) + accountNo;
}
