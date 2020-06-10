import edu.duke.FileResource;
import java.util.HashMap;
/**
 * Write a description of CodonCountHashMap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CodonCountHashMap {
    private HashMap<String,Integer> codonFreq;
    public CodonCountHashMap(){
        codonFreq=new HashMap<>();
    }
    private void buildCodonMap(String dna,int start){
        codonFreq.clear();
        int subFactor=(dna.length()-start)%3;
        System.out.println(dna.length());
        for(int i=start;i<(dna.length()-subFactor);i+=3){
            String currCodon=dna.substring(i,i+3);
            if(codonFreq.containsKey(currCodon)){
                codonFreq.replace(currCodon,(codonFreq.get(currCodon)+1));
            }
            else{
                codonFreq.put(currCodon,1);
            }
        }
    }
    private String getMostCodon(){
        int max=0;
        String codon="";
        for(String currKey:codonFreq.keySet()){
            int currFreq=codonFreq.get(currKey);
            if(currFreq>max)
            {
                max=currFreq;
                codon=currKey;
            }
        }
        return codon;
    }
    private void printCodonCount(int start,int end){
        for(String currKey:codonFreq.keySet()){
            int currFreq=codonFreq.get(currKey);
            if(start<=currFreq && currFreq<=end)
            {
                System.out.println(currKey+":"+currFreq);
            }
        }
    }
    public void testMethod(){
        FileResource fr=new FileResource();
        String dna=fr.asString().trim().toUpperCase();
        String mostCodon;
        
        buildCodonMap(dna,0);
        System.out.println("Number of unique codons in 1st reading frame:"+codonFreq.size());
        mostCodon=getMostCodon();
        System.out.println("Most common codon:"+getMostCodon()+":"+codonFreq.get(mostCodon));
        printCodonCount(1,5);
        
        buildCodonMap(dna,1);
        System.out.println("Number of unique codons in 2nd reading frame:"+codonFreq.size());
        mostCodon=getMostCodon();
        System.out.println("Most common codon:"+getMostCodon()+":"+codonFreq.get(mostCodon));
        printCodonCount(1,5);
        
        buildCodonMap(dna,2);
        System.out.println("Number of unique codons in 3rd reading frame:"+codonFreq.size());
        mostCodon=getMostCodon();
        System.out.println("Most common codon:"+getMostCodon()+":"+codonFreq.get(mostCodon));
        printCodonCount(1,5);
    }
}
