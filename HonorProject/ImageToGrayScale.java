import edu.duke.*;
import java.io.File;
/**
 * Write a description of ImageToGrayScale here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

/*
Gray is the combination of RGB when value for RGB are equal i.e. R=G=B
Equivalent gray value of any pixel can be approximated by the average of RGB value of the pixel   
*/

public class ImageToGrayScale {
    private ImageResource convertToGray(ImageResource inp){
        ImageResource out=new ImageResource(inp.getWidth(),inp.getHeight());
        for(Pixel pIn:inp.pixels()){
            int redVal=pIn.getRed();
            int greenVal=pIn.getGreen();
            int blueVal=pIn.getBlue();
            int avg=(redVal+greenVal+blueVal)/3;
            Pixel pOut=out.getPixel(pIn.getX(),pIn.getY());
            pOut.setRed(avg);
            pOut.setGreen(avg);
            pOut.setBlue(avg);
        }
        return out;
    }
    
    public void main(){
        DirectoryResource dr=new DirectoryResource();
        for(File f:dr.selectedFiles()){
            ImageResource ir=new ImageResource(f);
            ImageResource ret=convertToGray(ir);
            //Saves the file with Gray- as prefix
            String fileName=f.getName();
            ret.setFileName("Gray-"+fileName);
            ret.save();
            /*
            * Allows user to select path and file name to save the file 
            * ret.saveAs();
            */
        }
        System.out.println("Conversion To Gray Scale Successful");
    }
}
