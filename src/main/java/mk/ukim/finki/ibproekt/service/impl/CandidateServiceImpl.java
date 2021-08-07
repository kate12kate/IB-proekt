package mk.ukim.finki.ibproekt.service.impl;

import mk.ukim.finki.ibproekt.model.Candidate;
import mk.ukim.finki.ibproekt.repository.CandidateRepository;
import mk.ukim.finki.ibproekt.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateServiceImpl implements CandidateService {
    @Autowired
    private CandidateRepository candidateRepository;

    @Override
    public List<Candidate> findAll() {
        return candidateRepository.findAll();
    }

    @Override
    public Optional<Candidate> findById(Long id) {
        return this.candidateRepository.findById(id);
    }
}
