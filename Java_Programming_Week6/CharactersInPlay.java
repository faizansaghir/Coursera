import java.util.ArrayList;
import edu.duke.FileResource;
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CharactersInPlay {
    ArrayList<String> characters;
    ArrayList<Integer> mentions;
    public CharactersInPlay(){
        characters=new ArrayList<>();
        mentions=new ArrayList<>();
    }
    private void update(String character){
        if(characters.contains(character)){
            int index=characters.indexOf(character);
            int currMentions=mentions.get(index);
            mentions.set(index,currMentions+1);
        }
        else{
            characters.add(character);
            mentions.add(1);
        }
    }
    private void printCharacterDialogueFreq(){
        for(int i=0;i<characters.size();i++){
            System.out.println(characters.get(i)+":"+mentions.get(i));
        }
        System.out.println();
    }
    private void findAllCharacters(){
        characters.clear();
        mentions.clear();
        FileResource fr=new FileResource();
        for(String line:fr.lines()){
            int indexOfPeriod=line.indexOf(".");
            if(indexOfPeriod!=-1){
                String character=line.substring(0,indexOfPeriod);
                update(character);
            }
        }
    }
    private void charactersWithNumParts(int num1,int num2){
        for(int i=0;i<characters.size();i++){
            int mention=mentions.get(i);
            if(mention>=num1 && mention<=num2)
                System.out.println(characters.get(i)+":"+mentions.get(i));
        }
        System.out.println();
    }
    private int maxDialogue(){
        int max=0;
        int index=-1;
        for(int i=0;i<mentions.size();i++){
            int currMention=mentions.get(i);
            if(currMention>max){
                max=currMention;
                index=i;
            }
        }
        return index;
    }
    public void test(){
        findAllCharacters();
        printCharacterDialogueFreq();
        charactersWithNumParts(10,15);
        int mainCharIndex=maxDialogue();
        System.out.println(characters.get(mainCharIndex)+" has maximum dialogue:"+mentions.get(mainCharIndex));
    }
}
