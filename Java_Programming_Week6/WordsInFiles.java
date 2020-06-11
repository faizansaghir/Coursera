import java.util.ArrayList;
import java.util.HashMap;
import edu.duke.FileResource;
import edu.duke.DirectoryResource;
import java.io.File;
/**
 * Write a description of WordsInFile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordsInFiles {
    private HashMap<String,ArrayList<String>> wordFileMap;
    public WordsInFiles(){
        wordFileMap=new HashMap<>();
    }
    private void addWordsFromFile(File f){
        FileResource fr=new FileResource(f);
        for(String word:fr.words()){
            if(wordFileMap.containsKey(word)){
                ArrayList file=wordFileMap.get(word);
                if(file.contains(f.getName())){
                   continue;
                }
                else{   
                    file.add(f.getName());
                }
            }
            else{
                ArrayList<String> file=new ArrayList<>();
                file.add(f.getName());
                wordFileMap.put(word,file);
            }
        }
    }
    private void buildWordFileMap(){
        wordFileMap.clear();
        DirectoryResource dr=new DirectoryResource();
        for(File f:dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }
    private int maxNumber(){
        int max=0;
        for(String key:wordFileMap.keySet()){
            ArrayList<String> file=wordFileMap.get(key);
            int noOfFiles=file.size();
            if(max<noOfFiles){
                max=noOfFiles;
            }
        }
        return max;
    }
    private ArrayList<String> wordsInNumFiles(int reqNo){
        ArrayList<String> word=new ArrayList<>();
        for(String key:wordFileMap.keySet()){
            ArrayList<String> file=wordFileMap.get(key);
            int noOfFiles=file.size();
            if(noOfFiles==reqNo){
                word.add(key);
            }
        }
        return word;
    }
    private void printFilesIn(String reqWord){
        if(wordFileMap.containsKey(reqWord)){
            ArrayList<String> files=wordFileMap.get(reqWord);
            System.out.println("The word "+reqWord+" appear in file(s) named:");
            for(String file:files){
                System.out.print(file+"\t");
            }
        }
        else{
            System.out.println("The word "+reqWord+" doesnot appear in any file");
        }
    }
    private void printWordFileMap(){
        System.out.println("\n");
        for(String word:wordFileMap.keySet()){
            System.out.println("The word \'"+word+"\' appears in file(s) named:");
            ArrayList<String> files=wordFileMap.get(word);
            for(String file:files){
                System.out.print(file+"\t");
            }
            System.out.println();
        }
    }
    public void tester(){
        buildWordFileMap();
        int maxInFiles=maxNumber();
        System.out.println("The maximum number of files a single word appears in is:"+maxInFiles);
        ArrayList<String> wordsInNoFile=wordsInNumFiles(maxInFiles);
        for(String s:wordsInNoFile){
            System.out.println("The word \'"+s+"\' appears in file(s) named:");
            ArrayList<String> fileName=wordFileMap.get(s);
            for(String file:fileName){
                System.out.print(file+"\t");
            }
            System.out.println();
        }
    }
}
