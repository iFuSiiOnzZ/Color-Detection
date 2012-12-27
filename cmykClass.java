public class cmykClass {
    private double cyan = 0;
    private double black = 0;
    private double yellow = 0;
    private double magenta = 0;
    
    public cmykClass (double cyan, double magenta, double yellow, double black)
    {
        this.cyan = cyan;
        this.black = black;
        this.yellow = yellow;
        this.magenta = magenta;
    }
    
    public cmykClass (cmykClass cmykColor)
    {
        this.cyan = cmykColor.getCyan();
        this.black = cmykColor.getBlack();
        this.yellow = cmykColor.getYellow();
        this.magenta = cmykColor.getMagenta();
    }
    
    public double getCyan ()
    {
        return (this.cyan);
    }
    
    public double getBlack ()
    {
        return (this.black);
    }
    
    public double getYellow ()
    {
        return (this.yellow);
    }
    
    public double getMagenta ()
    {
        return (this.magenta);
    }
}
