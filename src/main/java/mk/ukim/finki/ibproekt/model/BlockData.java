package mk.ukim.finki.ibproekt.model;

import lombok.Data;

@Data
public class BlockData {
    private Candidate candidate;
    //private String publicHash;

    public BlockData(Candidate candidate) {
        this.candidate = candidate;
       // this.publicHash = publicHash;
    }

    @Override
    public String toString() {
        return "BlockData{" +
                "candidate=" + candidate +
                '}';
    }
}
