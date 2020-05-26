import edu.duke.*;
import org.apache.commons.csv.*;
/**
 * Write a description of BabyNames here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BabyNames {
    private int getRank(String name,int yob,char gender){
        String fileName="us_babynames_by_year/yob"+yob+".csv";
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
        String fileName="us_babynames_by_year/yob"+yob+".csv";
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
    
    public void main(){
        String name="Mason";
        System.out.println("Enter your name:"+name);
        int yob=2012;
        System.out.println("Enter your year of birth:"+yob);
        char gender='M';
        System.out.println("Enter your gender:"+gender);
        
        int nameRank=getRank(name,yob,gender);
        if(nameRank!=0)
            System.out.println("\nRank of "+name+" in the year "+yob+" was "+nameRank);
        else
            System.out.println("\nUnable to find name in the year");
        
        String fileName="us_babynames_by_year/yob"+yob+".csv";
        FileResource fr=new FileResource(fileName);
        getTotal(fr.getCSVParser(false));
        
        String nameRet=getName(nameRank,yob,gender);
        System.out.println("\nName found at rank "+nameRank+" is "+nameRet);
    }
}
