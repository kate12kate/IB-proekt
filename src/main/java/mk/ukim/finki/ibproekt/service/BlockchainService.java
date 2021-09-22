package mk.ukim.finki.ibproekt.service;

import mk.ukim.finki.ibproekt.model.Block;
import mk.ukim.finki.ibproekt.model.Candidate;
import mk.ukim.finki.ibproekt.model.VoteCount;


import java.util.List;

public interface BlockchainService {

    void createChainData();

    List<Block> getBlockChain();

    Boolean isChainValid();

    void addBlock(Block block);

    Block createBlock(Candidate candidate);





    VoteCount sealVotes();
}
