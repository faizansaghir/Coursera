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
        int[] keys=vb.tryKeyLength(fr.asString(),38,'e');
        for(int key:keys){
            System.out.print(key+",");
        }
        VigenereCipher vc=new VigenereCipher(keys);
        String decrypted=vc.decrypt(fr.asString());
        FileResource dictFile=new FileResource();
        int currWordCount=vb.countWords(decrypted,vb.readDictionary(dictFile));
        System.out.println("Word count:"+currWordCount);
    }
    public void testVigenereBreaker(){
        VigenereBreaker vb=new VigenereBreaker();
        vb.breakVigenere();
        System.out.println("Key length:"+vb.keyLengthVar);
        System.out.println("Keys:");
        for(int key:vb.keySet){
            System.out.print(key+",");
        }
        System.out.println("Valid words:"+vb.validWordsCount);
    }
}
