package mk.ukim.finki.ibproekt.service;

import mk.ukim.finki.ibproekt.model.Candidate;

import java.util.List;
import java.util.Optional;

public interface CandidateService {

public List<Candidate> findAll();

Optional<Candidate> findById(Long id);
}
