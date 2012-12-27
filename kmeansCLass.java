import java.awt.image.BufferedImage;

public abstract class kmeansCLass {
    
    protected int iTer = 0;
    protected int nClusters = 0;
    protected utilsClass uClass = null;
    protected BufferedImage bImg = null;
    
    public kmeansCLass (int iTer, int nClusters, BufferedImage bImg)
    {
        this.iTer        = iTer;
        this.bImg        = bImg;
        
        this.uClass      = new utilsClass();
        this.nClusters   = nClusters;
    }
    
    public abstract void initCLusters ();
    public abstract void startKmeans ();
}
