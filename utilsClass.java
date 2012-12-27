import java.awt.Color;
import java.awt.image.BufferedImage;

public class utilsClass {
    
    public double getDistance (Color c1, Color c2)
    {
        int dRed    = c1.getRed() - c2.getRed();
        int dBlue   = c1.getBlue() - c2.getBlue();
        int dGreen  = c1.getGreen() - c2.getGreen();
        
        return (Math.sqrt((dRed * dRed) + (dBlue * dBlue) + (dGreen * dGreen)));
    }
    
     public double getDistance (cmykClass c1, cmykClass c2)
    {
        double dCyan    = c1.getCyan() - c2.getCyan();
        double dBlack   = c1.getBlack() - c2.getBlack();
        double dYellow  = c1.getYellow() - c2.getYellow();
        double dMagenta = c1.getMagenta() - c2.getMagenta();
        
        return (Math.sqrt((dCyan * dCyan) + (dBlack * dBlack) + (dYellow * dYellow) + (dMagenta * dMagenta)));
    }
    
    public Color getColor (BufferedImage bImg, int x, int y)
    {
        int rgbColor    = bImg.getRGB(x, y);
        return (new Color(rgbColor));
    }
    
    public cmykClass rgbToCmyk (Color rgbColor)
    {
        double red   = 1.0 - rgbColor.getRed() / 255.0;
        double blue  = 1.0 - rgbColor.getBlue() / 255.0;
        double green = 1.0 - rgbColor.getGreen() / 255.0;
        
        double black   = Math.min(Math.min(red, green), blue);
        double cyan    = (red - black) / (1 - black);
        double yellow  = (blue - black) / (1 - black);
        double magenta = (green - black) / (1 - black);
        
        cyan    = (cyan * 100.0);
        black   = (black * 100.0);
        yellow  = (yellow * 100.0);
        magenta = (magenta * 100.0);
        
        return (new cmykClass(cyan, magenta, yellow, black));
     
    }
    
    public Color cmykToRgb (cmykClass cmykColor)
    {
        double cyan    = cmykColor.getCyan() / 255.0;
        double black   = cmykColor.getBlack() / 255.0;
        double yellow  = cmykColor.getYellow() / 255.0;
        double magenta = cmykColor.getMagenta() / 255.0;
        
        double red   = cyan * (1.0 - black) + black;
        double blue  = yellow * (1.0 - black) + black;
        double green = magenta * (1.0 - black) + black;
        
        red   = (1.0 - red) * 255.0;
        blue  = (1.0 - blue) * 255.0;
        green = (1.0 - green) * 255.0;
        
        return (new Color((int) red, (int) green, (int) blue));
    }
}