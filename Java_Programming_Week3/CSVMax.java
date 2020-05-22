
/**
 * Write a description of CSVMax here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
public class CSVMax {
    public CSVRecord hottestHourInFile(CSVParser par){
        CSVRecord largestSoFar=null;
        for(CSVRecord curr:par){
            if(largestSoFar==null){
                largestSoFar=curr;
            }
            else{
                double currTemp=Double.parseDouble(curr.get("TemperatureF"));
                double largestSoFarTemp=Double.parseDouble(largestSoFar.get("TemperatureF"));
                if(currTemp>largestSoFarTemp){
                    largestSoFar=curr;
                }
            }
        }
        return largestSoFar;
    }
    public void test(){
        FileResource fr=new FileResource();
        CSVParser par=fr.getCSVParser();
        CSVRecord max=hottestHourInFile(par);
        System.out.println("Maximum temperature was "+max.get("TemperatureF")+" at "+max.get("TimeEST"));
    }
}
