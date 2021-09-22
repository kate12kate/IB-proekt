package mk.ukim.finki.ibproekt.service;

import mk.ukim.finki.ibproekt.model.Voter;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface VoterService extends UserDetailsService {

    public Voter login(String username, String password, String ssn);
    public Voter findByUsername(String username);
    Voter save(Voter v);
}
