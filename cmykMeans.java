import java.awt.image.BufferedImage;

public class cmykMeans extends kmeansCLass {
    private cmykClass[] cmykClusters = null;
    
    public cmykMeans (int iTer, int nClusters, BufferedImage bImg)
    {
        super(iTer, nClusters, bImg);
        this.cmykClusters = new cmykClass[this.nClusters];
    }
    
    public cmykClass[] getClusters ()
    {
        return (this.cmykClusters);
    }
    
    public void initCLusters() 
    {
        int imgWidth    = this.bImg.getWidth();
        int imgHeight   = this.bImg.getHeight();
        
        for(int i = 0; i < this.nClusters; i++)
        {
            int widthRand  = (int) (Math.random() * (imgWidth - 1));
            int heightRand = (int) (Math.random() * (imgHeight - 1));
            this.cmykClusters[i] = this.uClass.rgbToCmyk(uClass.getColor(this.bImg, widthRand, heightRand));
        }
    }
    
    public void startKmeans ()
    {
        boolean stable = true;
        double[] cyan    = new double[this.nClusters];
        double[] black   = new double[this.nClusters];
        double[] yellow  = new double[this.nClusters];
        double[] magenta = new double[this.nClusters];
        double[] cntr    = new double[this.nClusters];
        
        double[] acyan    = new double[this.nClusters];
        double[] ablack   = new double[this.nClusters];
        double[] ayellow  = new double[this.nClusters];
        double[] amagenta = new double[this.nClusters];
        
        for(int i = 0; i < this.nClusters; i++)
        {
            acyan[i]      = 0.0;
            ablack[i]     = 0.0;
            ayellow[i]    = 0.0;
            amagenta[i]   = 0.0;
        }

        do
        {
            for(int i = 0; i < this.nClusters; i++)
            {
                cyan[i]      = 0.0;
                black[i]     = 0.0;
                yellow[i]    = 0.0;
                magenta[i]   = 0.0;
                cntr[i]      = 0.0;
            }
            
            for(int i = 0; i < this.bImg.getWidth(); i++)
            {
                for(int j = 0; j < this.bImg.getHeight(); j++)
                {
                    int whatCluster = 0;
                    cmykClass cmykColor = this.uClass.rgbToCmyk(this.uClass.getColor(this.bImg, i, j));
                    double cDistance = this.uClass.getDistance(cmykColor, this.cmykClusters[0]);
                    
                    for(int k = 0; k < this.nClusters; k++)
                    {
                        double tDistance = this.uClass.getDistance(cmykColor, this.cmykClusters[k]);
                        if(tDistance < cDistance)
                        {
                            cDistance = tDistance;
                            whatCluster = k;
                        }
                    }
                    
                    cntr[whatCluster]++;
                    cyan[whatCluster] += cmykColor.getCyan();
                    black[whatCluster] += cmykColor.getBlack();
                    yellow[whatCluster] += cmykColor.getYellow();
                    magenta[whatCluster] += cmykColor.getMagenta();
                }
            }
            
            for(int i = 0; i < this.nClusters; i++)
            {
                if(cntr[i] != 0)
                {
                    cyan[i]     = cyan[i] / cntr[i];
                    black[i]    = black[i] / cntr[i];
                    yellow[i]   = yellow[i] / cntr[i];
                    magenta[i]  = magenta[i] / cntr[i];   
                    
                    this.cmykClusters[i] = new cmykClass(cyan[i], magenta[i], yellow[i], black[i]);
                }   
            }
            
            stable = true;
            for(int i = 0; i < this.nClusters; i++)
            {
                if(acyan[i] != cyan[i]){ stable       = false; acyan[i]    = cyan[i]; }
                if(ablack[i] != black[i]){ stable     = false; ablack[i]   = black[i]; }
                if(ayellow[i] != yellow[i]){ stable   = false; ayellow[i]  = yellow[i]; }
                if(amagenta[i] != magenta[i]){ stable = false; amagenta[i] = magenta[i]; }       
            }
        }while(!stable && this.iTer-- > 0);
    }
}
