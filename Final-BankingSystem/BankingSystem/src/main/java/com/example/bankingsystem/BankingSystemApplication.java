package com.example.bankingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Locale;

@SpringBootApplication
public class BankingSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankingSystemApplication.class, args);
    }


    //GetAccount() gibi methodlara sadece belli kullanıcıların görmesi filtresi eklenecek
    //Kredi kartı ekstresi düşünülebilir

    // Özgün tasarım
    // 2. Ilk 1000 sıralamada register olan müşretimizin hesabına 50 tl hediye
    // User şifresi eğer unutmuşsa yeni şifre için bir key kullanarak şifre yenileyebilir





    // --------------------------------------------------------------------------------------------------//
          // Özgün tasarım
          // User şifresi eğer unutmuşsa yeni şifre için bir key kullanarak şifre yenileyebilir
          // 1. Kredi kartı şifresi konulup 3 kez yanlış girince bloke olabilir


    //Transactional olanlara aynı anda 2 işlem yapılamaz şeklinde bir arguman konulacak
    // Logger  Her kayıt, silme ve arama işleminden sonra bir logger çalışabilir

    // Unit Testler Yazılacak
    //DONE   // Transaction converter düzenle
    //DONE   // Facade tasarımı eklenecek
    //DONE   // Money Transfer ,Card Purchase and Card Debt Payment
    //DONE   // **** RestController Validation
    // Token control and authority options




}
