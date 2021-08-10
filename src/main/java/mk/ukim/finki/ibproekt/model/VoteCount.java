package mk.ukim.finki.ibproekt.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class VoteCount {

    private Map<String,Integer> countVotesByName;

    public VoteCount() {
        this.countVotesByName = new HashMap<>();
    }
}
