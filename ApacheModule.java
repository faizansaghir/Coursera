
/**
 * Write a description of ApacheModule here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.FileReader;
import org.apache.commons.csv.*;
public class ApacheModule {
    public static void main()
    {
        FileResource fr=new FileResource();
            
            CSVParser par=fr.getCSVParser();
            
            for(CSVRecord rec: par){
                System.out.println(rec.get("Name")+"\t"+
                rec.get("Class")+"\t"+rec.get("Section"));
            }
    }
}
