import edu.duke.FileResource;
import java.util.ArrayList;
/**
 * Write a description of wordFrequencyArrayList here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordFrequencyArrayList {
    private ArrayList<String> words;
    private ArrayList<Integer> freq;
    public WordFrequencyArrayList(){
        words=new ArrayList<>();
        freq=new ArrayList<>();
    }
    private int wordCount(){
        words.clear();
        freq.clear();
        FileResource fr=new FileResource();
        for(String word:fr.words()){
            word=word.toLowerCase();
            if(words.contains(word)){
                int index=words.indexOf(word);
                int currFreq=freq.get(index);
                freq.set(index,currFreq+1);
            }
            else{
                words.add(word);
                freq.add(1);
            }
        }
        return words.size();
    }
    private void printWordFrequency(){
        //can also use for(String str:words) i.e. an iterable loop
        for(int i=0;i<words.size();i++){
            System.out.println(words.get(i)+":"+freq.get(i));
        }
    }
    private int findIndexOfMax(){
        int max=0;
        int index=-1;
        for(int i=0;i<freq.size();i++){
            int currFreq=freq.get(i);
            if(currFreq>max){
                max=currFreq;
                index=i;
            }
        }
        return index;
    }
    
    public void test(){
        int noOfWords=wordCount();
        printWordFrequency();
        System.out.println("Number of unique words:"+noOfWords);
        int indexOfMax=findIndexOfMax();
        if(indexOfMax!=-1)
            System.out.println("The word with maximum frequency is \'"+words.get(indexOfMax)+"\' and it occurs "+freq.get(indexOfMax)+" times.");
        else{
            System.out.println("No words in file");
        }
    }
}