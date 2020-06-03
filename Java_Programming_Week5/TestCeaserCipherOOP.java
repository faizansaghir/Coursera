import edu.duke.FileResource;
/**
 * Write a description of TestCeaserCipherOOP here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestCeaserCipherOOP {
    public void simpleTests(){
        FileResource fr=new FileResource();
        String message=fr.asString();
        
        CeaserCipherOOP cc=new CeaserCipherOOP(15);
        
        String encryptedMessage=cc.encrypt(message);
        System.out.println("Encrypted Message:\n"+encryptedMessage+"\n");
        
        String decryptedMessage=cc.decrypt(encryptedMessage);
        System.out.println("Decrypted Message:\n"+decryptedMessage+"\n");
        
        String decryptedMessageNoKey=cc.breakCeaserCipher(encryptedMessage);
        System.out.println("Decrypted Message:\n"+decryptedMessageNoKey+"\n");
    }
}
