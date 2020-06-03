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
    
    private String decryptTwoKey(String s,int key1,int key2){
        StringBuilder str1=new StringBuilder("");
        StringBuilder str2=new StringBuilder("");
        StringBuilder finalStr=new StringBuilder("");
        for(int i=0;i<s.length();i++){
            if(i%2==0){
                str1.append(s.charAt(i));
            }
            else{
                str2.append(s.charAt(i));
            }
        }
        String st1=decrypt(str1.toString(),key1);
        String st2=decrypt(str2.toString(),key2);
        for(int i=0;i<st2.length();i++){
            finalStr.append(st1.charAt(i));
            finalStr.append(st2.charAt(i));
        }
        if(st1.length()>st2.length()){
            finalStr.append(st1.charAt(st1.length()-1));
        }
        return finalStr.toString();
    }
    
    private int indexOfMaxFreq(int[] alphaCount){
        int max=0;
        int index=4;
        for(int i=0;i<alphaCount.length;i++){
            if(alphaCount[i]>max){
                max=alphaCount[i];
                index=i;
            }
        }
        return index;
    }
    private int findSingleKey(String s){
        int key=0;
        String alphabets="abcdefghijklmnopqrstuvwxyz";
        int[] alphaCount=new int[26];
        for(int i=0;i<s.length();i++){
            int index=alphabets.indexOf(Character.toLowerCase(s.charAt(i)));
            if(index!=-1)
                alphaCount[index]++;
        }
        key=indexOfMaxFreq(alphaCount)-4;
        if(key<0)
            key=26+key;
        return key;
    }
    private String decryptNoKey(String s){
        int key=findSingleKey(s);
        String decryptedMessage=decrypt(s,key);
        System.out.println("Key found:"+key);
        return decryptedMessage;
    }
    
    private String decryptTwoKeyNoKey(String s){
        StringBuilder str1=new StringBuilder("");
        StringBuilder str2=new StringBuilder("");
        StringBuilder finalStr=new StringBuilder("");
        for(int i=0;i<s.length();i++){
            if(i%2==0){
                str1.append(s.charAt(i));
            }
            else{
                str2.append(s.charAt(i));
            }
        }
        String st1=decryptNoKey(str1.toString());
        String st2=decryptNoKey(str2.toString());
        for(int i=0;i<st2.length();i++){
            finalStr.append(st1.charAt(i));
            finalStr.append(st2.charAt(i));
        }
        if(st1.length()>st2.length()){
            finalStr.append(st1.charAt(st1.length()-1));
        }
        return finalStr.toString();
    }
    
    public void testEncrypt(){
        FileResource fr=new FileResource();
        int key=15;
        String encryptedString=encrypt(fr.asString(),key);
        System.out.println("Encrypted Message:\n"+encryptedString+"\n");
    }
    public void testEncryptTwoKeys(){
        FileResource fr=new FileResource();
        int key1=17;
        int key2=3;
        String encryptedString=encryptTwoKeys(fr.asString(),key1,key2);
        System.out.println("Encrypted Message:\n"+encryptedString+"\n");
    }
    public void testDecrypt(){
        FileResource fr=new FileResource();
        int key=15;
        String decryptedString=decrypt(fr.asString(),key);
        System.out.println("Decrypted Message:\n"+decryptedString+"\n");
    }
    public void testDecryptTwoKey(){
        FileResource fr=new FileResource();
        int key1=17;
        int key2=3;
        String decryptedString=decryptTwoKey(fr.asString(),key1,key2);
        System.out.println("Decrypted Message:\n"+decryptedString+"\n");
    }
    public void testDecryptNoKey(){
        FileResource fr=new FileResource();
        String decryptedString=decryptNoKey(fr.asString());
        System.out.println("Decrypted Message:\n"+decryptedString+"\n");
    }
    public void testDecryptTwoKeyNoKey(){
        FileResource fr=new FileResource();
        String decryptedString=decryptTwoKeyNoKey(fr.asString());
        System.out.println("Decrypted Message:\n"+decryptedString+"\n");
    }
}
