package mk.ukim.finki.ibproekt.repository;

import mk.ukim.finki.ibproekt.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CandidateRepository extends JpaRepository<Candidate,Long> {

    Optional<Candidate> findByNameAndSurname(String name,String surname);


}
