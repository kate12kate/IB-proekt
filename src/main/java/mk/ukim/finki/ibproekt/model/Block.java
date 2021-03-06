package mk.ukim.finki.ibproekt.model;

import lombok.Data;
import mk.ukim.finki.ibproekt.model.calculations.CalculateSha;
import org.springframework.util.DigestUtils;

import java.util.Date;
@Data
public class Block {

    private String hash;
    private String previousHash;
    private Long timeStamp;
    private Integer nonce;
    private BlockData blockData;

    public Block(String previousHash, BlockData blockData) {
        this.previousHash = previousHash;
        this.blockData = blockData;
        this.hash=calculateHash();
        this.timeStamp=new Date().getTime();
        this.nonce = 0;
    }

    public String calculateHash(){
        return CalculateSha.applySha256(this.previousHash+this.timeStamp+this.nonce+blockData.toString());

    }
    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0"
        while(!this.hash.substring( 0, difficulty).equals(target)) {
            nonce ++;
            hash = this.calculateHash();
        }
       // System.out.println("Block Mined!!! : " + hash);
    }
}
