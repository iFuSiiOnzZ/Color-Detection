public class rgbClass {
    
    private int red     = 0;
    private int blue    = 0;
    private int green   = 0;
    
    public rgbClass (int red, int green, int blue)
    {
        this.red    = red;
        this.blue   = blue;
        this.green  = green;
    }
    
    public rgbClass (rgbClass rgbColors)
    {
        this.red    = rgbColors.getRed();
        this.blue   = rgbColors.getBlue();
        this.green  = rgbColors.getGreen();
    }
    
    public int getRed ()
    {
        return (this.red);
    }
    
    public int getBlue ()
    {
        return (this.blue);
    }
    
    public int getGreen ()
    {
        return (this.green);
    }
}
