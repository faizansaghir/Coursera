import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder[] sb=new StringBuilder[totalSlices];
        for(int i=0;i<totalSlices;i++){
            sb[i]=new StringBuilder("");
        }
        for(int i=0;i<message.length();i++){
            int currSb=i%totalSlices;
            sb[currSb].append(message.charAt(i));
        }
        String returnString=sb[whichSlice].toString();
        return returnString;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc=new CaesarCracker(mostCommon);
        for(int i=0;i<klength;i++){
            String sliced=sliceString(encrypted,i,klength);
            key[i]=cc.getKey(sliced);
        }
        return key;
    }

    public void breakVigenere(int keyLength) {
        FileResource fr=new FileResource();
        String encrypted=fr.asString();
        int[] keys=new int[keyLength];
        keys=tryKeyLength(encrypted,keyLength,'e');
        VigenereCipher vc=new VigenereCipher(keys);
        System.out.println("Decrypted Message:\n"+vc.decrypt(encrypted));
    }
    
}
