import edu.duke.*;
import java.util.*;
/**
 * Write a description of GladLib here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GladLibHashMap {
    private HashMap<String,ArrayList<String>> myMap;
    
    private ArrayList<String> usedWords;
    private ArrayList<String> catUsed;
    private Random myRandom;
    
    private static String dataSourceURL = "C:\\Users\\FAIZAN\\CourseEra\\Java_Programming_Week6\\";
    private static String dataSourceDirectory = "data";
    
    public GladLibHashMap(){
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    
    public GladLibHashMap(String source){
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        myMap=new HashMap<>();
        String[] categories={"noun","country","color","name","adjective","animal","timeframe","verb","fruit"};
        for(String category:categories){
            ArrayList<String> catList=readIt(source+"/"+category+".txt");
            myMap.put(category,catList);
        }
        usedWords=new ArrayList<>();
        catUsed=new ArrayList<>();
    }
    
    private String randomFrom(ArrayList<String> source){
        String word;
        while(true)
        {
            int index = myRandom.nextInt(source.size());
            word=source.get(index);
            if(!usedWords.contains(word))
            {
                break;
            }
        }
        usedWords.add(word);
        return word;
    }
    
    private String getSubstitute(String label) {
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        if(myMap.containsKey(label)){
            if(catUsed.contains(label)){
                return randomFrom(myMap.get(label));
            }
            else{
                catUsed.add(label);
                return randomFrom(myMap.get(label));
            }
        }
        return "**UNKNOWN**";
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    private int totalWordsInMap(){
        int totalWords=0;
        for(String category:myMap.keySet()){
            ArrayList<String> words=myMap.get(category);
            totalWords+=words.size();
        }
        return totalWords;
    }
    
    private int totalWordsConsidered(){
        int totalWords=0;
        for(String category:myMap.keySet()){
            if(catUsed.contains(category)){
                ArrayList<String> words=myMap.get(category);
                totalWords+=words.size();
            }
        }
        return totalWords;
    }
    
    public void makeStory(){
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate.txt");
        printOut(story, 60);
        System.out.println("\n\nWords Used:");
        for(String s:usedWords){
            System.out.print(s+",");
        }
        int wordsInMap=totalWordsInMap();
        System.out.println("\n\nTotal number of words in every category combined:"+wordsInMap);
        int wordsInCatUsed=totalWordsConsidered();
        System.out.println("Total number of words in categories used:"+wordsInCatUsed);
        System.out.println("Categories used:"+catUsed.toString());
    }
}
