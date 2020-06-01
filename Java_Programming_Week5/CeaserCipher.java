import edu.duke.*;
/**
 * Write a description of CeaserCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CeaserCipher {
    private String encrypt(String s,int key){
        StringBuilder str=new StringBuilder("");
        String alphabetUpper="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetLower="abcdefghijklmnopqrstuvwxyz";
        String shiftedUpper=alphabetUpper.substring(key)+alphabetUpper.substring(0,key);
        String shiftedLower=alphabetLower.substring(key)+alphabetLower.substring(0,key);
        for(int i=0;i<s.length();i++){
            char currChar=s.charAt(i);
            int idx;
            if(Character.isLetter(currChar) && Character.isUpperCase(currChar))
                idx=alphabetUpper.indexOf(currChar);
            else
                idx=alphabetLower.indexOf(currChar);
            if(idx!=-1)
                if(Character.isUpperCase(currChar))
                    str.append(shiftedUpper.charAt(idx));
                else
                    str.append(shiftedLower.charAt(idx));
                
            else
                str.append(currChar);
        }
        return str.toString();
    }
    private String decrypt(String s,int trueKey){
        StringBuilder str=new StringBuilder("");
        int key=26-trueKey;
        String alphabetUpper="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetLower="abcdefghijklmnopqrstuvwxyz";
        String shiftedUpper=alphabetUpper.substring(key)+alphabetUpper.substring(0,key);
        String shiftedLower=alphabetLower.substring(key)+alphabetLower.substring(0,key);
        for(int i=0;i<s.length();i++){
            char currChar=s.charAt(i);
            int idx;
            if(Character.isLetter(currChar) && Character.isUpperCase(currChar))
                idx=alphabetUpper.indexOf(currChar);
            else
                idx=alphabetLower.indexOf(currChar);
            if(idx!=-1)
                if(Character.isUpperCase(currChar))
                    str.append(shiftedUpper.charAt(idx));
                else
                    str.append(shiftedLower.charAt(idx));
                
            else
                str.append(currChar);
        }
        return str.toString();
    }
    public void testEncrypt(){
        FileResource fr=new FileResource();
        int key=17;
        String encryptedString=encrypt(fr.asString(),key);
        System.out.println("Encrypted Message:\n"+encryptedString+"\n");
    }
    public void testDecrypt(){
        FileResource fr=new FileResource();
        int key=17;
        String decryptedString=decrypt(fr.asString(),key);
        System.out.println("Decrypted Message:\n"+decryptedString+"\n");
    }
}
