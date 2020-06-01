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
    private String encryptTwoKeys(String s,int key1,int key2){
        StringBuilder str=new StringBuilder("");
        String alphabetUpper="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetLower="abcdefghijklmnopqrstuvwxyz";
        String shiftedUpper[]={alphabetUpper.substring(key1)+alphabetUpper.substring(0,key1),alphabetUpper.substring(key2)+alphabetUpper.substring(0,key2)};
        String shiftedLower[]={alphabetLower.substring(key1)+alphabetLower.substring(0,key1),alphabetLower.substring(key2)+alphabetLower.substring(0,key2)};
        for(int i=0;i<s.length();i++){
            char currChar=s.charAt(i);
            int idx;
            if(Character.isLetter(currChar) && Character.isUpperCase(currChar))
                idx=alphabetUpper.indexOf(currChar);
            else
                idx=alphabetLower.indexOf(currChar);
            if(idx!=-1){
                int index=i%2;
                if(Character.isUpperCase(currChar))
                    str.append(shiftedUpper[index].charAt(idx));
                else
                    str.append(shiftedLower[index].charAt(idx));
            }
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
        int key=15;
        String encryptedString=encrypt(fr.asString(),key);
        System.out.println("Encrypted Message:\n"+encryptedString+"\n");
    }
    public void testEncryptTwoKeys(){
        FileResource fr=new FileResource();
        int key1=8;
        int key2=21;
        String encryptedString=encryptTwoKeys(fr.asString(),key1,key2);
        System.out.println("Encrypted Message:\n"+encryptedString+"\n");
    }
    public void testDecrypt(){
        FileResource fr=new FileResource();
        int key=17;
        String decryptedString=decrypt(fr.asString(),key);
        System.out.println("Decrypted Message:\n"+decryptedString+"\n");
    }
}
