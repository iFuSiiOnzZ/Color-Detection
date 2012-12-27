import java.awt.image.BufferedImage;

public class utilsClass {
    
    public int getDistance (rgbClass c1, rgbClass c2)
    {
        int dRed    = c1.getRed() - c2.getRed();
        int dBlue   = c1.getBlue() - c2.getBlue();
        int dGreen  = c1.getGreen() - c2.getGreen();
        
        return ((dRed * dRed) + (dBlue * dBlue) + (dGreen * dGreen));
    }
    
     public double getDistance (cmykClass c1, cmykClass c2)
    {
        double dCyan    = c1.getCyan() - c2.getCyan();
        double dBlack   = c1.getBlack() - c2.getBlack();
        double dYellow  = c1.getYellow() - c2.getYellow();
        double dMagenta = c1.getMagenta() - c2.getMagenta();
        
        return ((dCyan * dCyan) + (dBlack * dBlack) + (dYellow * dYellow) + (dMagenta * dMagenta));
    }
    
    public rgbClass getColor (BufferedImage bImg, int x, int y)
    {
        int rgbColor    = bImg.getRGB(x, y);
        
        int red         = (rgbColor >> 16) & 0x000000FF;
        int blue        = (rgbColor >> 8) & 0x000000FF;
        int green       = (rgbColor) & 0x000000FF;
        
        return (new rgbClass(red, green, blue));
    }
    
    public cmykClass rgbToCmyk (rgbClass rgbColor)
    {
        double red   = 1.0 - rgbColor.getRed() / 255.0;
        double blue  = 1.0 -  rgbColor.getRed() / 255.0;
        double green = 1.0 - rgbColor.getRed() / 255.0;
        
        double black   = Math.min(Math.min(red, green), blue);
        double cyan    = (red - black) / (1 - black);
        double yellow  = (blue - black) / (1 - black);
        double magenta = (green - black) / (1 - black);
        
        cyan    = (cyan * 100.0) + 0.5;
        black   = (black * 100.0) + 0.5;
        yellow  = (yellow * 100.0) + 0.5;
        magenta = (magenta * 100.0) + 0.5;
        
        return (new cmykClass(cyan, magenta, yellow, black));
     
    }
    
    public rgbClass cmykToRgb (cmykClass cmykColor)
    {
        double cyan    = cmykColor.getCyan() / 255.0;
        double black   = cmykColor.getBlack() / 255.0;
        double yellow  = cmykColor.getYellow() / 255.0;
        double magenta = cmykColor.getMagenta() / 255.0;
        
        double red   = cyan * (1.0 - black) + black;
        double blue  = yellow * (1.0 - black) + black;
        double green = magenta * (1.0 - black) + black;
        
        red   = (1.0 - red) * 255.0 + 0.5;
        blue  = (1.0 - blue) * 255.0 + 0.5;
        green = (1.0 - green) * 255.0 + 0.5;
        
        return (new rgbClass((int) red, (int) green, (int) blue));
    }
}