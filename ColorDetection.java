import java.awt.Color;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ColorDetection {

    public static void main(String[] args) {
    	if(args.length < 3)
    	{
    		return;
    	}
    	
    	try{
	    	BufferedImage bImg = ImageIO.read(new File(args[0]));
	    	
	    	kmeansCLass kClass = new rgbMeans(Integer.parseInt(args[1]), Integer.parseInt(args[2]), bImg);
	    	Color[] rgbCLusters;
	    	
	    	kClass.initCLusters();
	    	kClass.startKmeans();
	    	rgbCLusters = ((rgbMeans) kClass).getClusters();
	    	
	    	for(int i = 0; i < rgbCLusters.length; i++)
	    	{
	    		System.out.print("(" + rgbCLusters[i].getRed() +", " + rgbCLusters[i].getGreen() + ", " + rgbCLusters[i].getBlue() + ")\t");
                        colorNaming cName = new colorNaming(rgbCLusters[i]);
	    		System.out.println(cName.getName("RGB.txt"));
	    	} 
    	} catch (IOException e) { 
  		System.out.println(e.getMessage());
	} catch (Exception e) {
		System.out.println(e.getMessage());
	}
    }
}
