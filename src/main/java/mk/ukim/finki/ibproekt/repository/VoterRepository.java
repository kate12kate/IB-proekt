package mk.ukim.finki.ibproekt.repository;

import mk.ukim.finki.ibproekt.model.Voter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoterRepository extends JpaRepository<Voter,Long> {

    Optional<Voter> findByUsernameAndPasswordAndSsn(String username,String password,String ssn);


}
