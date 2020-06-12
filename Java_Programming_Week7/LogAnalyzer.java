import java.util.ArrayList;
import java.util.HashMap;
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
    public int countUniqueIPs(){
        ArrayList<String> ips=new ArrayList<>();
        for(LogEntry entry:records){
            String currIP=entry.getIpAddress();
            if(ips.contains(currIP)){
                continue;
            }
            else{
                ips.add(currIP);
            }
        }
        return ips.size();
    }
    public void printAll(){
        for(LogEntry entry:records){
            System.out.println(entry);
        }
    }
    public void printAllHigherThanNum(int num){
        for(LogEntry entry:records){
            int currStatus=entry.getStatusCode();
            if(currStatus>num){
                System.out.println(entry);
            }
        }
    }
    public ArrayList<String> uniqueIPVisitsOnDay(String someday){
        ArrayList<String> uniqueIPs=new ArrayList<>();
        for(LogEntry entry:records){
            String currDate=entry.getAccessTime().toString();
            String currIP=entry.getIpAddress();
            if(currDate.contains(someday) && !uniqueIPs.contains(currIP)){
                uniqueIPs.add(currIP);
            }
        }
        return uniqueIPs;
    }
    public int countUniqueIPsInRange(int low,int high){
        ArrayList<String> uniqueIPs=new ArrayList<>();
        for(LogEntry entry:records){
            int currStatus=entry.getStatusCode();
            String currIP=entry.getIpAddress();
            if(low<=currStatus && currStatus<=high && !uniqueIPs.contains(currIP)){
                uniqueIPs.add(currIP);
            }
        }
        return uniqueIPs.size();
    }
    public HashMap<String,Integer> countVisitsPerIP(){
        HashMap<String,Integer> IPcount=new HashMap<>();
        for(LogEntry entry:records){
            String currIP=entry.getIpAddress();
            if(IPcount.containsKey(currIP)){
                int currCount=IPcount.get(currIP);
                IPcount.replace(currIP,currCount+1);
            }
            else{
                IPcount.put(currIP,1);
            }
        }
        return IPcount;
    }
    public int mostNumberVisitsByIP(HashMap<String,Integer> IPcount){
        int max=0;
        for(String ip:IPcount.keySet()){
            int currCount=IPcount.get(ip);
            if(currCount>max){
                max=currCount;
            }
        }
        return max;
    }
    public ArrayList<String> iPsMostVisits(HashMap<String,Integer> IPcount){
        int maxVisit=0;
        ArrayList<String> IPs=new ArrayList<>();
        for(String ip:IPcount.keySet()){
            int currVisit=IPcount.get(ip);
            if(currVisit>maxVisit){
                IPs.clear();
                IPs.add(ip);
                maxVisit=currVisit;
            }
            else if(currVisit==maxVisit){
                IPs.add(ip);
            }
        }
        return IPs;
    }
}
