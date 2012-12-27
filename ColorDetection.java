import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ColorDetection {

	public static void main(String[] args) {
		if (args.length < 3) {
			return;
		}

		try {
			BufferedImage bImg = ImageIO.read(new File(args[0]));
			BufferedImage convertedImage = new BufferedImage(bImg.getWidth(), bImg.getHeight(), BufferedImage.TYPE_INT_RGB);
			convertedImage.createGraphics().drawRenderedImage(bImg, null);
			

			kmeansCLass kClass = new rgbMeans(Integer.parseInt(args[1]),
					Integer.parseInt(args[2]), convertedImage);
			rgbClass[] rgbCLusters;

			kClass.initCLusters();
			kClass.startKmeans();
			rgbCLusters = ((rgbMeans) kClass).getClusters();

			for (int i = 0; i < rgbCLusters.length; i++) {
				System.out.println("(" + rgbCLusters[i].getRed() + ", "
						+ rgbCLusters[i].getGreen() + ", "
						+ rgbCLusters[i].getBlue() + ")");
			}

			/*
			 * kmeansCLass kClass = new cmykMeans(Integer.parseInt(args[1]),
			 * Integer.parseInt(args[2]), bImg); cmykClass[] cmykCLusters;
			 * 
			 * kClass.initCLusters(); kClass.startKmeans(); cmykCLusters =
			 * ((cmykMeans) kClass).getClusters();
			 * 
			 * for(int i = 0; i < cmykCLusters.length; i++) {
			 * System.out.println("(" + cmykCLusters[i].getCyan() +", " +
			 * cmykCLusters[i].getMagenta() + ", " + cmykCLusters[i].getYellow()
			 * + ", " + cmykCLusters[i].getBlack() +")"); }
			 */
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
