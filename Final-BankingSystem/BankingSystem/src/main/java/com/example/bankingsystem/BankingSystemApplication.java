package com.example.bankingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankingSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankingSystemApplication.class, args);
    }


    //    // Transaction converter düzenle
    //    // Facade tasarımı eklenecek
    //    // Token control and authority options
    //    // **** RestController Validation

    // ------------------------------------
    //    // Özgün tasarım
          // 1. Kredi kartı şifresi konulup 3 kez yanlış girince bloke olabilir
          // 2. Ilk 1000 sıralamada register olan müşretimizin hesabına 50 tl hediye
          // Logger  Her kayıt silme ve arama işleminden sonra bir logger çalışabilir
    //    // Unit Testler Yazılacak
    //    //



}
