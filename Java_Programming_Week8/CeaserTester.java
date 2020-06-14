import edu.duke.FileResource;
/**
 * Write a description of CeaserTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CeaserTester {
    public void testCeaser(){
        CaesarCipher cc=new CaesarCipher(5);
        String alphaLower="abcdefghijklmnopqrstuvwxyz";
        String alphaUpper=alphaLower.toUpperCase();
        for(int i=0;i<alphaLower.length();i++){
            System.out.print(alphaLower.charAt(i)+"->"+cc.encryptLetter(alphaLower.charAt(i))+"\t");
            System.out.print(alphaUpper.charAt(i)+"->"+cc.encryptLetter(alphaUpper.charAt(i))+"\n");
        }
        
        FileResource frEnc=new FileResource();
        System.out.println("Encrypted Message:");
        System.out.println(cc.encrypt(frEnc.asString()));
        
        FileResource frDec=new FileResource();
        System.out.println("Decrypted Message:");
        System.out.println(cc.decrypt(frDec.asString()));
    }
}
