import edu.duke.*;
/**
 * Write a description of CeaserCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CeaserCipherOOP{
    private String alphabetUpper;
    private String alphabetLower;
    private String shiftedUpper;
    private String shiftedLower;
    private int mainKey;
    public CeaserCipherOOP(int key){
        mainKey=key;
        alphabetUpper="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        alphabetLower="abcdefghijklmnopqrstuvwxyz";
        shiftedUpper=alphabetUpper.substring(key)+alphabetUpper.substring(0,key);
        shiftedLower=alphabetLower.substring(key)+alphabetLower.substring(0,key);
    }
    public String encrypt(String s){
        StringBuilder str=new StringBuilder("");
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
    public String decrypt(String s){
        int decryptKey=26-mainKey;
        CeaserCipherOOP cc=new CeaserCipherOOP(decryptKey);
        String decryptedMessage=cc.encrypt(s);
        return decryptedMessage;
    }
    
    private int maxIndex(int[] alphaCount){
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
        int[] alphaCount=new int[26];
        for(int i=0;i<s.length();i++){
            int index=alphabetLower.indexOf(Character.toLowerCase(s.charAt(i)));
            if(index!=-1)
                alphaCount[index]++;
        }
        key=maxIndex(alphaCount)-4;
        if(key<0)
            key=26+key;
        return key;
    }
    public String breakCeaserCipher(String s){
        int key=findSingleKey(s);
        CeaserCipherOOP cc=new CeaserCipherOOP(key);
        String decryptedMessage=cc.decrypt(s);
        return decryptedMessage;
    }
}
