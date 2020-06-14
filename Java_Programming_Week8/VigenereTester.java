import edu.duke.FileResource;
/**
 * Write a description of ViigenereTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class VigenereTester {
    public void test(){
        int[] keys={17,14,12,4};
        VigenereCipher vc=new VigenereCipher(keys);
        FileResource fr=new FileResource();
        System.out.println("Encrypted Message:\n"+vc.encrypt(fr.asString()));
    }
}
