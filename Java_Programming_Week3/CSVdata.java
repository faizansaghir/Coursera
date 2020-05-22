
/**
 * Write a description of CSVMax here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.File;
import org.apache.commons.csv.*;
public class CSVdata {
    public CSVRecord hottestHourInFile(CSVParser par){
        CSVRecord largestSoFar=null;
        for(CSVRecord curr:par){
            largestSoFar=largerOfTheTwo(curr,largestSoFar);
        }
        return largestSoFar;
    }
    public CSVRecord coldestHourInFile(CSVParser par){
        CSVRecord smallestSoFar=null;
        for(CSVRecord curr:par){
            smallestSoFar=smallerOfTheTwo(curr,smallestSoFar);
        }
        return smallestSoFar;
    }
    public CSVRecord fileWithHottestTemperature (){
        CSVRecord largestSoFar=null;
        DirectoryResource dr=new DirectoryResource();
        for(File f:dr.selectedFiles()){
            FileResource fr=new FileResource(f);
            CSVRecord curr = hottestHourInFile(fr.getCSVParser());
            largestSoFar=largerOfTheTwo(curr,largestSoFar);
        }
        return largestSoFar;
    }
    public CSVRecord fileWithColdestTemperature (){
        CSVRecord smallestSoFar=null;
        DirectoryResource dr=new DirectoryResource();
        for(File f:dr.selectedFiles()){
            FileResource fr=new FileResource(f);
            CSVRecord curr = coldestHourInFile(fr.getCSVParser());
            smallestSoFar=smallerOfTheTwo(curr,smallestSoFar);
        }
        return smallestSoFar;
    }
    public CSVRecord largerOfTheTwo(CSVRecord curr, CSVRecord largestSoFar){
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
        return largestSoFar;
    }
    public CSVRecord smallerOfTheTwo(CSVRecord curr, CSVRecord smallestSoFar){
        if(smallestSoFar==null && Double.parseDouble(curr.get("TemperatureF"))!=-9999){
                smallestSoFar=curr;
        }
        else if(smallestSoFar!=null)
        {
            double currTemp=Double.parseDouble(curr.get("TemperatureF"));
            double smallestSoFarTemp=Double.parseDouble(smallestSoFar.get("TemperatureF"));
            if(currTemp<smallestSoFarTemp && Double.parseDouble(curr.get("TemperatureF"))!=-9999){
                smallestSoFar=curr;
            }
        }
        return smallestSoFar;
    }
    public void testColdestHourInFile(){
        FileResource fr=new FileResource();
        CSVRecord min=coldestHourInFile(fr.getCSVParser());
        System.out.println("Minimum temperature was "+min.get("TemperatureF")+" at "+min.get("TimeEST"));
    }
    public void testHottestHourInFile(){
        FileResource fr=new FileResource();
        CSVRecord max=hottestHourInFile(fr.getCSVParser());
        System.out.println("Maximum temperature was "+max.get("TemperatureF")+" at "+max.get("TimeEST"));
    }
    public void testFileWithHottestTemperature (){
        CSVRecord max=fileWithHottestTemperature();
        System.out.println("Maximum temperature was "+max.get("TemperatureF")+" at "+max.get("DateUTC"));
    }
    public void testFileWithColdestTemperature (){
        CSVRecord min=fileWithColdestTemperature();
        System.out.println("Minimum temperature was "+min.get("TemperatureF")+" at "+min.get("DateUTC"));
    }
}
