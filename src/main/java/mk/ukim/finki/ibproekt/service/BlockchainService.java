package mk.ukim.finki.ibproekt.service;

import mk.ukim.finki.ibproekt.model.Block;
import mk.ukim.finki.ibproekt.model.Candidate;
import mk.ukim.finki.ibproekt.model.Peer;

import java.util.List;
import java.util.Map;

public interface BlockchainService {

    void createChainData();

    List<Block> getBlockChain();

    Boolean isChainValid();

    void addBlock(Block block);

    Block createBlock(Candidate candidate);

    boolean addPeer(String peer_id);

    boolean removePeer(String peer_id);

    List<Peer> getConnectedPeers();

    String sealVotes();
}
