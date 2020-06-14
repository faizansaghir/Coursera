import edu.duke.FileResource;
/**
 * Write a description of VigenereBreakTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class VigenereBreakTester {
    public void testSliceString(){
        VigenereBreaker vb=new VigenereBreaker();
        String operational="abcdefghijklm";
        int reqSlice=2,totalSlice=3;
        System.out.println("Original String:"+operational); 
        System.out.println("Total Slice:"+totalSlice);
        System.out.println("Required Slice:"+reqSlice); 
        System.out.println("Sliced String:"+vb.sliceString(operational, reqSlice, totalSlice)); 
    }
    public void testTryKeyLength(){
        FileResource fr=new FileResource();
        VigenereBreaker vb=new VigenereBreaker();
        int[] keys=vb.tryKeyLength(fr.asString(),4,'e');
        for(int key:keys){
            System.out.print(key+",");
        }
    }
}
