package com.example.bankingsystem.core.constants;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Random;

@Component
public final class ConstantUtils {
    private ConstantUtils() {
    }

    private static Random random = new Random();

    public static final BigDecimal DAILY_MAX_LIMIT_OF_ATM=BigDecimal.valueOf(5000L);

    public static String getRandomIban(Integer branchCode) {
        return "TR" + new BigDecimal(random.nextLong(10_000_000, 99_999_999)) + branchCode.toString();
    }

    public static String getCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }

    public static Long getRandomAccountNumber() {
        return random.nextLong(10_000_000, 99_999_999);
    }

    public static Integer getRandomExtraAccountNo() {
        return random.nextInt(1000, 9999);
    }

    public static String getRandomCardNo(){
        return
                new BigDecimal(new BigDecimal(random.nextLong(1000,9999))+""+
                        new BigDecimal(random.nextLong(1000,9999))+""+
                        new BigDecimal(random.nextLong(1000,9999))+""+
                        new BigDecimal(random.nextLong(1000,9999)).toBigInteger()).setScale(0, RoundingMode.DOWN).toString();
    }

}
