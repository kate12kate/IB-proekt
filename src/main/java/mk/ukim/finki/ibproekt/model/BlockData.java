package mk.ukim.finki.ibproekt.model;

import lombok.Data;

@Data
public class BlockData {
    private Candidate candidate;
    private String publicHash;

    public BlockData(Candidate candidate, String publicHash) {
        this.candidate = candidate;
        this.publicHash = publicHash;
    }
}
