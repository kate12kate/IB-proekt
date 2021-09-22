package mk.ukim.finki.ibproekt.service.impl;

import mk.ukim.finki.ibproekt.model.*;
import mk.ukim.finki.ibproekt.service.BlockchainService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

@Service
public class BlockchainServiceImpl implements BlockchainService {

    private List<Block> blockchain = new ArrayList<>();

    public static int difficulty = 3;

    //pravenje na nulti block
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


    //iterira niz celiot chain i gleda dali prev hash na momentalniot blockot e ist so prethodniot
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
    //public Map<String, Integer> sealVotes()
    public VoteCount sealVotes(){
        VoteCount voteCount = new VoteCount();
        this.blockchain
                .forEach(block -> {
                    String candidateName = block.getBlockData().getCandidate().getFullName();
                    voteCount.getCountVotesByName()
                            .putIfAbsent(candidateName,0);
                    voteCount.getCountVotesByName()
                            .computeIfPresent(candidateName,(k,v)->{
                                ++v;
                                return v;
                            });
                });

        int count = voteCount.getCountVotesByName()
                .values()
                .stream().mapToInt(i -> i).sum();

        voteCount.setNumberOfVotes(count);
        return voteCount;
    }

}
