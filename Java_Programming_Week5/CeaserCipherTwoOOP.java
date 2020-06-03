
/**
 * Write a description of CeaserCipherTwoOOP here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CeaserCipherTwoOOP {
    private String alphabetUpper;
    private String alphabetLower;
    private String[] shiftedUpper=new String[2];
    private String[] shiftedLower=new String[2];
    private int mainKey1;
    private int mainKey2;
    public CeaserCipherTwoOOP(int key1,int key2){
        mainKey1=key1;
        mainKey2=key2;
        alphabetUpper="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        alphabetLower="abcdefghijklmnopqrstuvwxyz";
        shiftedUpper[0]=alphabetUpper.substring(key1)+alphabetUpper.substring(0,key1);
        shiftedUpper[1]=alphabetUpper.substring(key2)+alphabetUpper.substring(0,key2);
        shiftedLower[0]=alphabetLower.substring(key1)+alphabetLower.substring(0,key1);
        shiftedLower[1]=alphabetLower.substring(key2)+alphabetLower.substring(0,key2);
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
    public String decrypt(String s){
        int key1=26-mainKey1;
        int key2=26-mainKey2;
        CeaserCipherTwoOOP cc=new CeaserCipherTwoOOP(key1,key2);
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
    private int findKey(String s){
        int key=0;
        String alphabets="abcdefghijklmnopqrstuvwxyz";
        int[] alphaCount=new int[26];
        for(int i=0;i<s.length();i++){
            int index=alphabets.indexOf(Character.toLowerCase(s.charAt(i)));
            if(index!=-1)
                alphaCount[index]++;
        }
        key=maxIndex(alphaCount)-4;
        if(key<0)
            key=26+key;
        return key;
    }
    public String breakCeaserCipher(String s){
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

        int key1=findKey(str1.toString());
        int key2=findKey(str2.toString());

        CeaserCipherTwoOOP cc=new CeaserCipherTwoOOP(key1,key2);
        String decryptedMessage=cc.decrypt(s);
        return decryptedMessage;
    }
}
