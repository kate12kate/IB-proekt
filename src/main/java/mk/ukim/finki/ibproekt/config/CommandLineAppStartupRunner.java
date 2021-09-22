package mk.ukim.finki.ibproekt.config;

import mk.ukim.finki.ibproekt.model.Role;
import mk.ukim.finki.ibproekt.model.Voter;
import mk.ukim.finki.ibproekt.repository.VoterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    @Autowired
    VoterRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public void run(String...args) throws Exception {
       if(userRepository.findAll().size() != 0){
           return;
       }
       Voter v =  new Voter("vojdan", Role.ROLE_USER,passwordEncoder.encode("filipion123"),"Vojdan","Ristovski","2704000450035","address1",21,false,"ristovskivojdan@gmail.com");
       Voter v1 =  new Voter("kate", Role.ROLE_USER,passwordEncoder.encode("kate123"),"Katerina","Gligorovska","0309999455051","address2",21,false,"katerinagligorovska@gmail.com");
       Voter v2=  new Voter("admin", Role.ROLE_ADMIN,passwordEncoder.encode("admin"),"Admin","Admin","0000","address3",21,false,"admin@gmail.com");
       userRepository.save(v);
       userRepository.save(v1);
       userRepository.save(v2);
    }
}