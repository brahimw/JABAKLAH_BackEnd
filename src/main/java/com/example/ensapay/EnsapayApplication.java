package com.example.ensapay;

import com.example.ensapay.models.Admin;
import com.example.ensapay.service.AdminService;
import com.example.ensapay.service.AgentService;
import com.example.ensapay.service.FactureService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@SpringBootApplication
public class EnsapayApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnsapayApplication.class, args);
    }

    @Bean
    CommandLineRunner run(FactureService factureService, AgentService agentService, AdminService adminService) {
        return args -> {

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

            Date date = new Date();

            adminService.saveAdmin(new Admin("admin", "admin", "admin@gmail.com"));

            adminService.createAgent("agent", "agent", "CIN", "A123", new Date(), "marrakech", "agent@mail.com", "0612454558", "az1212", "az1212");

            agentService.createUser("client","brahim","ahmed","client@mail.com","0622325435");

            factureService.createFacture("unpaied",20.0,"paiment facture electricite",date,"LYDEC","0622325435");
            factureService.createFacture("unpaied",30.0,"paiment facture eau",date,"LYDEC","06223254355");
            factureService.createFacture("unpaied",120.0,"paiment facture eau",date,"LYDEC","0622325435");
            factureService.createFacture("unpaied",03.5,"paiment facture eau",date,"AMANDISTANGER","0622325435");
            factureService.createFacture("unpaied",40.0,"paiment facture electricite",date,"AMANDISTANGER","0622325435");
            factureService.createFacture("unpaied",57.0,"paiment facture abonement IAM",date,"IAMFACTURES","00622325435");
            factureService.createFacture("unpaied",43.0,"paiment facture abonement IAM",date,"IAMFACTURES","0622325435");
            factureService.createFacture("unpaied",99.0,"paiment facture wifi",date,"IAMFACTURES","0622325435");
            factureService.createFacture("unpaied",99.0,"paiment facture wifi",date,"IAMFACTURES","0622325435");
            factureService.createFacture("unpaied",99.0,"paiment facture electricite",date,"REDAL","0622325435");
            factureService.createFacture("unpaied",20.0,"paiment facture abonement telephone fix",date,"IAMRECHARGE","0622325435");
            factureService.createFacture("unpaied",45.0,"paiment facture abonement telephone fix",date,"IAMRECHARGE","0622325435");
            factureService.createFacture("unpaied",25.0,"paiment facture abonement telephone fix",date,"IAMRECHARGE","0622325435");
            factureService.createFacture("unpaied",27.0,"paiment facture d'eau penalite ",date,"LYDEC","0613085665");
            factureService.createFacture("unpaied",200.0,"paiment facture d'eau penalite",date,"REDAL","0613085665");S

        };

    }

}

