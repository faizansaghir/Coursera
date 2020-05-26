
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
    public CSVRecord lowestHumidityInFile(CSVParser par){
        CSVRecord smallestSoFar=null;
        for(CSVRecord curr:par){
            smallestSoFar=smallerOfTheTwoHumidity(curr,smallestSoFar);
        }
        return smallestSoFar;
    }
    public double averageTemperatureInFile(CSVParser par){
        double avgTemp=0;
        int valid=0;
        for(CSVRecord curr:par){
            double currTemp=Double.parseDouble(curr.get("TemperatureF"));
            if(currTemp!= -9999){
                avgTemp+=currTemp;
                valid++;
            }
        }
        avgTemp=avgTemp/valid;
        return avgTemp;
    }
    public double averageTemperatureWithHighHumidityInFile(CSVParser par,int value){
        double avgTemp=0;
        int valid=0;
        for(CSVRecord curr:par){
            double currTemp=Double.parseDouble(curr.get("TemperatureF"));
            String currHumidityStr=curr.get("Humidity");
            if(currTemp!= -9999 && !currHumidityStr.equals("N/A") && Integer.parseInt(currHumidityStr)>=value){
                avgTemp+=currTemp;
                valid++;
            }
        }
        if(valid>0)
            avgTemp=avgTemp/valid;
        else
            avgTemp=-9999;
        return avgTemp;
    }
    
    
    public CSVRecord highestTemperatureInManyFiles(){
        CSVRecord largestSoFar=null;
        DirectoryResource dr=new DirectoryResource();
        for(File f:dr.selectedFiles()){
            FileResource fr=new FileResource(f);
            CSVRecord curr = hottestHourInFile(fr.getCSVParser());
            largestSoFar=largerOfTheTwo(curr,largestSoFar);
        }
        return largestSoFar;
    }
    public String fileWithColdestTemperature(){
        CSVRecord smallestSoFar=null;
        String file=null;
        DirectoryResource dr=new DirectoryResource();
        for(File f:dr.selectedFiles()){
            FileResource fr=new FileResource(f);
            CSVRecord curr = coldestHourInFile(fr.getCSVParser());
            CSVRecord temp=smallestSoFar;
            smallestSoFar=smallerOfTheTwo(curr,smallestSoFar);
            if(smallestSoFar!=temp)
                file=f.getName();
        }
        return file;
    }
    public CSVRecord lowestHumidityInManyFiles (){
        CSVRecord smallestSoFar=null;
        DirectoryResource dr=new DirectoryResource();
        for(File f:dr.selectedFiles()){
            FileResource fr=new FileResource(f);
            CSVRecord curr = lowestHumidityInFile (fr.getCSVParser());
            smallestSoFar=smallerOfTheTwoHumidity(curr,smallestSoFar);
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
    public CSVRecord smallerOfTheTwoHumidity(CSVRecord curr, CSVRecord smallestSoFar){
        if(smallestSoFar==null && !curr.get("Humidity").equals("N/A")){
                smallestSoFar=curr;
        }
        else if(smallestSoFar!=null && !curr.get("Humidity").equals("N/A") )
        {
            double currTemp=Double.parseDouble(curr.get("Humidity"));
            double smallestSoFarTemp=Double.parseDouble(smallestSoFar.get("Humidity"));
            if(currTemp<smallestSoFarTemp ){
                smallestSoFar=curr;
            }
        }
        return smallestSoFar;
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
    
    
    public void printAllTemp(CSVParser par){
        for(CSVRecord curr:par){
            System.out.println(curr.get("DateUTC")+" "+curr.get("TemperatureF"));
        }
    }
    
    public void testColdestHourInFile(){
        FileResource fr=new FileResource();
        CSVRecord min=coldestHourInFile(fr.getCSVParser());
        System.out.println("Minimum temperature was "+min.get("TemperatureF")+" at "+min.get("DateUTC"));
    }
    public void testLowestHumidityInFile(){
        FileResource fr=new FileResource();
        CSVRecord min=lowestHumidityInFile (fr.getCSVParser());
        System.out.println("Minimum humidity was "+min.get("Humidity")+" at "+min.get("DateUTC"));
    }
    public void testHottestHourInFile(){
        FileResource fr=new FileResource();
        CSVRecord max=hottestHourInFile(fr.getCSVParser());
        System.out.println("Maximum temperature was "+max.get("TemperatureF")+" at "+max.get("TimeEST"));
    }
    public void testAverageTemperatureInFile(){
        FileResource fr=new FileResource();
        double avg=averageTemperatureInFile(fr.getCSVParser());
        System.out.println("Average temperature in file is "+avg);
    }
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr=new FileResource();
        double avg=averageTemperatureWithHighHumidityInFile(fr.getCSVParser(),80);
        if(avg!=-9999)
            System.out.println("Average temperature when high humidity is "+avg);
        else
            System.out.println("No temperatures with that humidity");
    }
    
    public void testHighestTemperatureInManyFiles (){
        CSVRecord max=highestTemperatureInManyFiles();
        System.out.println("Maximum temperature was "+max.get("TemperatureF")+" at "+max.get("DateUTC"));
    }
    public void testLowestHumidityInManyFiles (){
        CSVRecord min=lowestHumidityInManyFiles();
        System.out.println("Minimum humidity was "+min.get("Humidity")+" at "+min.get("DateUTC"));
    }
    public void testFileWithColdestTemperature (){
        String file=fileWithColdestTemperature();
        System.out.println("Minimum temperature was in file:"+file);
        FileResource fr=new FileResource("Data/2013/"+file);
        CSVRecord rec=coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature on that day was "+rec.get("TemperatureF")+"\nAll the Temperatures on the coldest day were:");
        printAllTemp(fr.getCSVParser());
    }
}
