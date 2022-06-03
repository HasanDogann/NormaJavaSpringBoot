package com.example.bankingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Locale;

@SpringBootApplication
public class BankingSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankingSystemApplication.class, args);
    }


    //DONE   // Transaction converter düzenle
    //DONE   // Facade tasarımı eklenecek
    //DONE   // Money Transfer ,Card Purchase and Card Debt Payment
    //DONE   // **** RestController Validation
             // Token control and authority options

    //account delete test ile devam edilecek
    //Transactional olanlara aynı anda 2 işlem yapılamaz şeklinde bir arguman konulacak
    //GetAccount() gibi methodlara sadece belli kullanıcıların görmesi filtresi eklenecek


   // --------------------------------------------------------------------------------------------------//
          // Özgün tasarım
          // User şifresi eğer unutmuşsa yeni şifre için bir key kullanarak şifre yenileyebilir
          // 1. Kredi kartı şifresi konulup 3 kez yanlış girince bloke olabilir
          // 2. Ilk 1000 sıralamada register olan müşretimizin hesabına 50 tl hediye
          // Logger  Her kayıt, silme ve arama işleminden sonra bir logger çalışabilir
          // Unit Testler Yazılacak
          //



}
