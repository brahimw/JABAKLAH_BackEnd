package com.example.ensapay.service;

import com.example.ensapay.models.Compte;
import com.example.ensapay.models.UserApp;
import com.example.ensapay.repository.CompteRepo;
import com.example.ensapay.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Date;

@Service @RequiredArgsConstructor
@Transactional
@Slf4j

public class UserService {

    @Autowired
    private final UserRepo userRepo;
    @Autowired
    private final CompteRepo compteRepo;
    private final PasswordEncoder passwordEncoder;
    final String number = "0123456789";
    @Autowired
    MailService mailService;

    public String changePassword(String numTel, String newPassword) {
        UserApp user = userRepo.findByNumTel(numTel);
        String encodedPassword = passwordEncoder.encode(newPassword);
        if(user.getFirstAuth() == true){ user.setFirstAuth(false);}
        user.setPassword(encodedPassword);
      try{
          userRepo.save(user);
          System.out.println("mot de passe changé pour Client , le nouveau mot de passe est: " +newPassword);
          mailService.sendEmail(user.getEmail(),"<h1>Bonjour "+user.getNom()+" "+user.getPrenom()+"</h1>" +
                  "<h2>vous avez changé votre mot de passe avec succès</h2>");
      }catch (MessagingException | IOException e){
          System.out.println("échec de la modification du mot de passe du client");
          e.printStackTrace();
      }
return "mot de passe modifié avec succès pour le client";

    }

    public Boolean createCompteToUser(String numTel , String typecompte) throws IOException {
        UserApp user = userRepo.findByNumTel(numTel);
        Compte compte =new Compte();
        String rib = genererRib();
        compte.setRib(rib);

        if(typecompte==null){
            System.out.println("type compte hase arrived empty null");
        }
        compte.setType_compte(typecompte);
        compte.setSolde(0.0);
        compte.setComptename("checking Account");
        user.setCompte(compte);
        compteRepo.save(compte);
        return true;
    }
    public String genererRib() {
        Long dateoftoday =  System.currentTimeMillis();
        String dateoftodayinms = dateoftoday.toString();

        SecureRandom random = new SecureRandom();
        String rib="";


        for(int i=0;i<11;i++) {
            rib+=number.charAt(random.nextInt(number.length()));
        }
        rib+=dateoftodayinms;

        log.info("RIB of client genarated: "+rib);
        return rib;
    }

    public Boolean clienHasAccount(String numTel){
        UserApp client = userRepo.findByNumTel(numTel);

        if (client.getCompte() != null) return true;
        else return false;
    }
    public Boolean clienHaschangedPassword(String numTel){
        UserApp client = userRepo.findByNumTel(numTel);

        if (client.getFirstAuth()) return false;
        else return true;
    }

    public UserApp getClientInfo(String numTel){
        UserApp client = userRepo.findByNumTel(numTel);
            return client;

    }



}