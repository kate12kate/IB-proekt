package mk.ukim.finki.ibproekt.model.exceptions;

public class CandidateDoesNotExists extends RuntimeException{
    public CandidateDoesNotExists(String message) {
        super(message);
    }
}
