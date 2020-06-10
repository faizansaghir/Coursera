import edu.duke.FileResource;
import java.util.HashMap;
/**
 * Write a description of wordFrequencyArrayList here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordFrequencyHashMap {
    private HashMap<String,Integer> wordFreq;
    public WordFrequencyHashMap(){
        wordFreq=new HashMap<>();
    }
    private int wordCount(){
        wordFreq.clear();
        FileResource fr=new FileResource();
        for(String word:fr.words()){
            word=word.toLowerCase();
            if(wordFreq.containsKey(word)){
                wordFreq.replace(word,(wordFreq.get(word)+1));
            }
            else{
                wordFreq.put(word,1);
            }
        }
        return wordFreq.size();
    }
    private void printWordFrequency(){
        for(String currKey:wordFreq.keySet()){
            if(wordFreq.get(currKey)>200)
                System.out.println(currKey+":"+wordFreq.get(currKey));
        }
    }
    private String findKeyOfMax(){
        int max=0;
        String key="";
        for(String currKey:wordFreq.keySet()){
            int currFreq=wordFreq.get(currKey);
            if(currFreq>max){
                max=currFreq;
                key=currKey;
            }
        }
        return key;
    }
    
    public void test(){
        int noOfWords=wordCount();
        printWordFrequency();
        System.out.println("Number of unique words:"+noOfWords);
        String keyOfMax=findKeyOfMax();
        if(keyOfMax.equals("")){
            System.out.println("No words in file");
        }
        else{
            System.out.println("The word with maximum frequency is \'"+keyOfMax+"\' and it occurs "+wordFreq.get(keyOfMax)+" times.");
        }
    }
}