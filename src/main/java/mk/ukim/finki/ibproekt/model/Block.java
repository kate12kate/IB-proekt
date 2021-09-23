package mk.ukim.finki.ibproekt.model;

import lombok.Data;
import mk.ukim.finki.ibproekt.model.calculations.CalculateSha;

import java.security.PrivateKey;
import java.security.Signature;
import java.util.Date;
@Data
public class Block {

    private String hash;
    private String previousHash;
    private Long timeStamp;
    private Integer nonce;
    private BlockData blockData;
    private PrivateKey privateKey;
    private byte[] signature;

    public Block(String previousHash, BlockData blockData, PrivateKey privateKey) {
        this.previousHash = previousHash;
        this.blockData = blockData;
        this.hash=calculateHash();
        this.timeStamp=new Date().getTime();
        this.privateKey = privateKey;
        this.nonce = 0;
        this.signature = null;
    }

    public String calculateHash(){
        return CalculateSha.applySha256(this.previousHash+this.timeStamp+this.nonce+blockData.toString());
    }

    public String getSignatureString(){
        return this.previousHash+this.timeStamp+this.nonce+blockData.toString();
    }

    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0"
        while(!this.hash.substring( 0, difficulty).equals(target)) {
            nonce ++;
            hash = this.calculateHash();
        }
       // System.out.println("Block Mined!!! : " + hash);
        generateSignature(privateKey);
    }

    public void generateSignature(PrivateKey privateKey) {
        String data = getSignatureString();
        this.signature = applyECDSASig(privateKey,data);
    }

    public static byte[] applyECDSASig(PrivateKey privateKey, String input) {
        Signature dsa;
        byte[] output = new byte[0];
        try {
            dsa = Signature.getInstance("ECDSA", "BC");
            dsa.initSign(privateKey);
            byte[] strByte = input.getBytes();
            dsa.update(strByte);
            byte[] realSig = dsa.sign();
            output = realSig;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return output;
    }
}
