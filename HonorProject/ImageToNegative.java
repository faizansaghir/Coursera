import edu.duke.*;
import java.io.File;
/**
 * Write a description of ImageToNegatives here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
//Negatives are the inversion of RGB colours
//The inverted image has RGB value as RNew=255-ROld,GNew=255-GOld,BNew=255-BOld
public class ImageToNegative{
    private ImageResource convertToNegative(ImageResource inp){
        ImageResource out=new ImageResource(inp.getWidth(),inp.getHeight());
        for(Pixel pIn:inp.pixels()){
            int redVal=pIn.getRed();
            int greenVal=pIn.getGreen();
            int blueVal=pIn.getBlue();
            Pixel pOut=out.getPixel(pIn.getX(),pIn.getY());
            pOut.setRed(255-redVal);
            pOut.setGreen(255-greenVal);
            pOut.setBlue(255-blueVal);
        }
        return out;
    }
    
    public void main(){
        DirectoryResource dr=new DirectoryResource();
        for(File f:dr.selectedFiles()){
            ImageResource ir=new ImageResource(f);
            ImageResource ret=convertToNegative(ir);
            //Saves the file with Gray- as prefix
            String fileName=f.getName();
            ret.setFileName("Inverted-"+fileName);
            ret.save();
            /*
            * Allows user to select path and file name to save the file 
            * ret.saveAs();
            */
        }
        System.out.println("Conversion To Negative Successful");
    }
}


    

