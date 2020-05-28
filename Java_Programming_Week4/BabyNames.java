import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;
/**
 * Write a description of BabyNames here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BabyNames {
    String path="";
    String fileSub="";
    String fileName="";
    
    private int getRank(String name,int yob,char gender){
        String fileName=path+yob+fileSub;
        FileResource fr=new FileResource(fileName);
        CSVParser par=fr.getCSVParser(false);//no header
        int rank=0;
        for(CSVRecord rec:par){
            if(rec.get(1).equals(gender+""))
            {
                rank++;
                if(rec.get(0).equals(name))
                return rank;
            }
        }
        return 0;
    }
    private String getName(int rank,int yob,char gender){
        String fileName=path+yob+fileSub;
        FileResource fr=new FileResource(fileName);
        CSVParser par=fr.getCSVParser(false);//no header
        String name="";
        for(CSVRecord rec:par){
            if(rec.get(1).equals(gender+""))
            {
                rank--;
                if(rank==0)
                return rec.get(0);
            }
        }
        return "Rank Out Of Bounds";
    }
    private void getTotal(CSVParser par){
        int totalNames=0;
        int total=0;
        int totalBoysName=0;
        int totalBoys=0;
        int totalGirls=0;
        int totalGirlsName=0;
        for(CSVRecord rec:par){
            totalNames++;
            if(rec.get(1).equals("M"))
            {
                totalBoysName++;
                totalBoys+=Integer.parseInt(rec.get(2));
            }  
            else{
                totalGirlsName++;
                totalGirls+=Integer.parseInt(rec.get(2));
            }
        }
        total=totalBoys+totalGirls;
        System.out.println("\nTotal number of children:"+total);
        System.out.println("Total number of names:"+totalNames);
        System.out.println("\nTotal number of boys:"+totalBoys);
        System.out.println("Total number of boys names:"+totalBoysName);
        System.out.println("\nTotal number of girls:"+totalGirls);
        System.out.println("Total number of girls names:"+totalGirlsName);
    }
    private void whatIsNameInYear(String name,int yob,int yos,char gender){
        fileName="us_babynames_test/yob"+yob+"short.csv";
        int nameRank=getRank(name,yob,gender);
        if(nameRank!=0){
            fileName=yos+"short.csv";
            String getName=getName(nameRank,yos,gender);
            System.out.println("\nThe name "+name+" has the rank "+nameRank+" in the year "+yob);
            System.out.println("The equivalent name in the year "+yos+" is "+getName);
        }
        else{
            System.out.println("\nUnable to find name in the year");
        }
    }
    private int yearOfHighestRank(String name,char gender){
        DirectoryResource dr=new DirectoryResource();
        int yearOfHishestRank=0;
        int highestRank=0;
        for(File f:dr.selectedFiles()){
            int yob=Integer.parseInt(f.getName().substring(3,7));
            int getRank=getRank(name,yob,gender);
            if(highestRank==0){
                if(getRank!=0){
                    highestRank=getRank;
                    yearOfHishestRank=yob;
                }
            }
            else if(highestRank>getRank && getRank!=0){
                highestRank=getRank;
                yearOfHishestRank=yob;
            }
        }
        return yearOfHishestRank;
    }
    private double getAverageRank(String name,char gender){
        DirectoryResource dr=new DirectoryResource();
        int totalRank=0;
        int validEntry=0;
        for(File f:dr.selectedFiles()){
            int yob=Integer.parseInt(f.getName().substring(3,7));
            int getRank=getRank(name,yob,gender);
            if(getRank!=0){
                totalRank+=getRank;
                validEntry++;
            }
        }
        if(validEntry==0){
            return 0;
        }
        return ((double)totalRank/validEntry);
    }
    private int getTotalBirthsRankedHigher(String name,int year,char gender){
        int getRank=getRank(name,year,gender)-1;
        int totalHigherBirth=0;
        if(getRank!=0){
            String fileName=path+year+fileSub;
            FileResource fr=new FileResource(fileName);
            CSVParser par=fr.getCSVParser(false);//no header
            for(CSVRecord rec:par){
                if(rec.get(1).equals(gender+"")){
                    totalHigherBirth+=Integer.parseInt(rec.get(2));
                    getRank--;
                }
                if(getRank==0){
                    break;
                }
            }
        }
        return totalHigherBirth;
    }
    
    public void main(){
        String name="Frank";
        System.out.println("Name:"+name);
        int yob=1900;
        System.out.println("Year of birth:"+yob);
        char gender='M';
        System.out.println("GThe gender:"+gender);
        int yos=2014;
        System.out.println("Year of search:"+yos);
        
        path="us_babynames_by_year/yob";
        fileSub=".csv";
        
        //int yohr=yearOfHighestRank(name,gender);
        //System.out.println("\nYear of highest rank:"+yohr);
        
        //whatIsNameInYear(name,yob,yos,gender);
        
        //double avgRank=getAverageRank(name,gender);
        //System.out.println("\nAverage rank for the name"+name+" is "+avgRank);
        
        
        //int getRank=getRank(name,yob,gender);
        //System.out.println("\nRank of "+name+" in year "+yob+" is "+getRank);
        
        //String getName=getName(450,1982,gender);
        //System.out.println("\nRank of "+getName+" in year "+1982+" is "+450);
        
        
        //FileResource fr=new FileResource(path+yob+fileSub);
        //CSVParser par=fr.getCSVParser(false);
        //getTotal(par);
        
       
        //int total=getTotalBirthsRankedHigher(name,yob,gender);
        //System.out.println("\nTotal children with names ranked higher:"+total);
    }
}
