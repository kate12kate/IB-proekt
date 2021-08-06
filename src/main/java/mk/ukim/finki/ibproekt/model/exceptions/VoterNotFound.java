package mk.ukim.finki.ibproekt.model.exceptions;

public class VoterNotFound extends RuntimeException {
    public VoterNotFound(String message) {
        super(message);
    }
}
