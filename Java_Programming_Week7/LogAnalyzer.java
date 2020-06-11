import java.util.ArrayList;
import edu.duke.FileResource;
/**
 * Write a description of LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LogAnalyzer {
    private ArrayList<LogEntry> records;
    public LogAnalyzer(){
        records=new ArrayList<>();
    }
    public void readFile(){
        FileResource fr=new FileResource();
        for(String entry:fr.lines()){
            LogEntry newEntry=WebLogParser.parseEntry(entry);
            records.add(newEntry);
        }
    }
    public void printAll(){
        for(LogEntry entry:records){
            System.out.println(entry);
        }
    }
}
