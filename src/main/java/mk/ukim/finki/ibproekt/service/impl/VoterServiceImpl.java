package mk.ukim.finki.ibproekt.service.impl;

import mk.ukim.finki.ibproekt.model.Voter;
import mk.ukim.finki.ibproekt.model.exceptions.VoterNotFound;
import mk.ukim.finki.ibproekt.repository.VoterRepository;
import mk.ukim.finki.ibproekt.service.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VoterServiceImpl implements VoterService {
    @Autowired
    private VoterRepository voterRepository;
    @Autowired
    private PasswordEncoder encoder;


    @Override
    public Voter login(String username, String password, String ssn)  {
        return voterRepository.findByUsernameAndPasswordAndSsn(username,password,ssn).orElseThrow(()->new VoterNotFound("Korisnikot ne e najden"));
    }

    @Override
    public Voter findByUsername(String username) {
        return voterRepository.findByUsername(username).orElseThrow(()->new VoterNotFound("Nema vakov voter"));
    }

    @Override
    public Voter save(Voter v) {
        return this.voterRepository.save(v);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return voterRepository.findByUsername(s).orElseThrow(()->new UsernameNotFoundException(s));
    }
}
