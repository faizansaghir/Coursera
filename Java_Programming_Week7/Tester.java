
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la=new LogAnalyzer();
        la.readFile();
        int uniqueIPs=la.countUniqueIPs();
        System.out.println("Number of unique IPs are:"+uniqueIPs);
        ArrayList<String> uniqueIPsOnDay=la.uniqueIPVisitsOnDay("Sep 24");
        System.out.println("Number of unique IPs on the day were:"+uniqueIPsOnDay.size());
        int IPInRange=la.countUniqueIPsInRange(200,299);
        System.out.println("Number of unique IPs in range is:"+IPInRange);
    }
    public void testLogAnalyzerPrintAll(){
        LogAnalyzer la=new LogAnalyzer();
        la.readFile();
        la.printAll();
    }
    public void testLogAnalyzerHigherThanNum(){
        LogAnalyzer la=new LogAnalyzer();
        la.readFile();
        la.printAllHigherThanNum(400);
    }
    public void testLogAnalyzerCountVisitPerIP(){
        LogAnalyzer la=new LogAnalyzer();
        la.readFile();
        HashMap<String,Integer> countVisit=la.countVisitsPerIP();
        for(String ip:countVisit.keySet()){
            System.out.println(ip+":"+countVisit.get(ip));
        }
        int maxVisitByIP=la.mostNumberVisitsByIP(countVisit);
        System.out.println("Most number of visit by a single IP is:"+maxVisitByIP);
        ArrayList<String> mostVisitIPs=la.iPsMostVisits(countVisit);
        System.out.println("IP(s) with most visits:"+mostVisitIPs.toString());
    }
    public void testiPsForDays(){
        LogAnalyzer la=new LogAnalyzer();
        la.readFile();
        HashMap<String,ArrayList<String>> dateIPMap=la.iPsForDays();
        for(String day:dateIPMap.keySet()){
            ArrayList<String> IPs=dateIPMap.get(day);
            System.out.println(day+":"+IPs.toString());
        }
        String dayWithMostVisit=la.dayWithMostIPVisits(dateIPMap);
        System.out.println("Day with most IP visits:"+dayWithMostVisit);
        String searchDay="Sep 29";
        ArrayList<String> iPsWithMostVisit=la.iPsWithMostVisitsOnDay(dateIPMap,searchDay);
        System.out.println("IP(s) with most visit on the day:"+iPsWithMostVisit.toString());
    }
}
