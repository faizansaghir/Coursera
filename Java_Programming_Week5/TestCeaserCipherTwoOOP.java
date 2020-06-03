import edu.duke.FileResource;
/**
 * Write a description of TestCeaserCipherTwoOOP here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestCeaserCipherTwoOOP {
    public void simpleTest(){
        FileResource fr=new FileResource();
        String message=fr.asString();
        
        CeaserCipherTwoOOP cc=new CeaserCipherTwoOOP(21,8);
        
        String encryptedMessage=cc.encrypt(message);
        System.out.println("Encrypted Message:\n"+encryptedMessage+"\n");
        
        String decryptedMessage=cc.decrypt(encryptedMessage);
        System.out.println("Decrypted Message:\n"+decryptedMessage+"\n");
        
        String decryptedMessageNoKey=cc.breakCeaserCipher(encryptedMessage);
        System.out.println("Decrypted Message:\n"+decryptedMessageNoKey+"\n");
    }
}
