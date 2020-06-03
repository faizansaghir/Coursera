import edu.duke.FileResource;
/**
 * Write a description of WordLength here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordLength {
    private void countWordLengths(FileResource resource,int[] counts){
        int maxLength=counts.length-1;
        for(String word:resource.words()){
            int length=word.length();
            boolean firstLetter=Character.isLetter(word.charAt(0));
            boolean lastLetter=Character.isLetter(word.charAt(length-1));
            if(!firstLetter){
                length-=1;
            }
            if(!lastLetter){
                length-=1;
            }
            if(length<maxLength && !(length<0)){
                counts[length]++;
            }
            else{
                counts[maxLength]++;
            }
        }
    }
    private int indexOfMax(int[] values){
        int max=0;
        int index=0;
        for(int i=1;i<values.length;i++){
            if(values[i]>max){
                max=values[i];
                index=i;
            }
        }
        return index;
    }
    public void testCountWordLengths(){
        FileResource fr=new FileResource();
        int[] lengths=new int[31];
        countWordLengths(fr,lengths);
        for(int i=1;i<lengths.length;i++){
            if(lengths[i]!=0)
                System.out.println("Length "+i+" : "+lengths[i]);
        }
        int maxFrequencyLength=indexOfMax(lengths);
        System.out.println("Most Common Word Length In File:"+maxFrequencyLength);
    }
}
