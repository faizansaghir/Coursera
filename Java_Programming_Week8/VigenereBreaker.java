import java.util.*;
import edu.duke.*;
import java.io.File;
public class VigenereBreaker {
    int[] keySet;
    int keyLengthVar;
    int validWordsCount=0;
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
    
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> words=new HashSet<>();
        for(String word: fr.lines()){
            words.add(word.toLowerCase());
        }
        return words;
    }
    
    public int countWords(String message,HashSet<String> dictionary){
        String[] words=message.split("\\W+");
        int wordInDict=0;
        for(String word:words){
            if(dictionary.contains(word.toLowerCase())){
                wordInDict++;
            }
        }
        return wordInDict;
    }
    
    public String breakForLanguage(String encrypted,HashSet<String> dictionary){
        int maxWordMatch=0;
        String bestDecrypt="";
        char mostCommon=mostCommonCharIn(dictionary);
        for(int keyLength=1;keyLength<=100;keyLength++){
            int[] keys=new int[keyLength];
            keys=tryKeyLength(encrypted,keyLength,mostCommon);
            VigenereCipher vc=new VigenereCipher(keys);
            String decrypted=vc.decrypt(encrypted);
            int currWordCount=countWords(decrypted,dictionary);
            if(currWordCount>maxWordMatch){
                maxWordMatch=currWordCount;
                bestDecrypt=decrypted;
                keyLengthVar=keyLength;
                validWordsCount=currWordCount;
            }
        }
        keySet=tryKeyLength(encrypted,keyLengthVar,'e');
        return bestDecrypt;
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary){
        HashMap<Character,Integer> letterCount=new HashMap<>();
        int maxLetterFreq=0;
        char mostCommon=' ';
        for(String word:dictionary){
            for(int i=0;i<word.length();i++){
                char ch=word.charAt(i);
                if(letterCount.containsKey(ch)){
                    int currCount=letterCount.get(ch);
                    letterCount.replace(ch,currCount+1);
                    if(currCount>maxLetterFreq){
                        mostCommon=ch;
                        maxLetterFreq=currCount+1;
                    }
                }
                else{
                    letterCount.put(ch,1);
                }
            }
        }
        return mostCommon;
    }
    
    public void breakForAllLangs(String encrypted,HashMap<String,HashSet<String>> languages){
        int langCount=0;
        HashMap<Integer,Integer> countMap=new HashMap<>();
        HashMap<Integer,String> langMap=new HashMap<>();
        HashMap<Integer,String> messageMap=new HashMap<>();
        for(String language:languages.keySet()){
            HashSet<String> currDict=languages.get(language);
            String decrypted=breakForLanguage(encrypted,currDict);
            countMap.put(langCount,validWordsCount);
            langMap.put(langCount,language);
            messageMap.put(langCount,decrypted);
            langCount++;
        }
        int maxValid=0;
        String mostValid="";
        String validLang="";
        for(int langNum:countMap.keySet()){
            int currValid=countMap.get(langNum);
            if(currValid>maxValid){
                maxValid=currValid;
                mostValid=messageMap.get(langNum);
                validLang=langMap.get(langNum);
            }
        }
        System.out.println("Decrypted Message:\n"+mostValid+"\nLanguage:"+validLang+"\nValid words:"+maxValid);
    }
    
    public void breakVigenere() {
        FileResource fr=new FileResource();
        String encrypted=fr.asString();
        DirectoryResource dictFiles=new DirectoryResource();
        HashMap<String,HashSet<String>> completeDict=new HashMap<>();
        for(File f:dictFiles.selectedFiles()){
            FileResource dictFile=new FileResource(f);
            String language=f.getName();
            HashSet<String> langDict=readDictionary(dictFile);
            completeDict.put(language,langDict);
            System.out.println(language);
        }
        breakForAllLangs(encrypted,completeDict);
    }
}
