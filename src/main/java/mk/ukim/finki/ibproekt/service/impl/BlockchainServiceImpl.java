package mk.ukim.finki.ibproekt.service.impl;

import mk.ukim.finki.ibproekt.model.Block;
import mk.ukim.finki.ibproekt.model.BlockData;
import mk.ukim.finki.ibproekt.model.Candidate;
import mk.ukim.finki.ibproekt.model.Peer;
import mk.ukim.finki.ibproekt.service.BlockchainService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlockchainServiceImpl implements BlockchainService {

    private List<Block> blockchain = new ArrayList<>();
    private List<Peer> connectedPeers = new ArrayList<>();
    public static int difficulty = 3;

    @Override
    public void createChainData() {
        BlockData data = new BlockData(null);
        Block block = new Block("0",data);
        this.blockchain.add(block);
    }

    @Override
    public List<Block> getBlockChain() {
        return this.blockchain;
    }

    @Override
    public Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;
        String hashTarget =  new String(new char[difficulty]).replace('\0', '0');

        for(int i=1;i<this.blockchain.size();i++)
        {
            currentBlock = this.blockchain.get(i);
            previousBlock = this.blockchain.get(i-1);
            if(!previousBlock.getHash().equals(currentBlock.getPreviousHash()))
            {
                System.out.println("Previous hash not equal");
                return false;
            }
            if(!currentBlock.getHash().substring( 0, difficulty).equals(hashTarget)) {
                System.out.println("This block hasn't been mined");
                return false;
            }
        }
        return true;
    }

    @Override
    public void addBlock(Block block) {

        block.mineBlock(difficulty);
        blockchain.add(block);

    }

    @Override
    public Block createBlock(Candidate candidate) {
        if(blockchain.size()==0)
        {
            BlockData data = new BlockData(candidate);
            Block b = new Block("0",data);
            return b;
        }

        BlockData p = new BlockData(candidate);
        return new Block(this.blockchain.get(this.blockchain.size()-1).getHash(),p);
    }

    @Override
    public boolean addPeer(String peer_id) {

        boolean verif = false;

        for (int i = 0; i < this.connectedPeers.size(); i++) {
            if (this.connectedPeers.get(i).getPeer_id().equals(peer_id)) {
                verif = true;
                break;
            }
        }

        if (!verif) {
            Peer p = new Peer(peer_id);
            this.connectedPeers.add(p);
            System.out.println("Adding peer...");
            return true;
        } else {
            System.out.println("Peer exist");
            return false;
        }
    }

    @Override
    public boolean removePeer(String peer_id) {
        boolean verif = false;

        for(int i=0;i<this.connectedPeers.size();i++)
        {
            if(this.connectedPeers.get(i).getPeer_id().equals(peer_id))
            {
                this.connectedPeers.remove(i);
                verif = true;
                break;
            }
        }
        return verif;
    }

    @Override
    public List<Peer> getConnectedPeers() {
        return this.connectedPeers;
    }
}
