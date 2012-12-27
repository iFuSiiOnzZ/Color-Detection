import java.awt.image.BufferedImage;

public final class rgbMeans extends kmeansCLass {
    
    private rgbClass[] rgbClusters = null;
    
    public rgbMeans (int iTer, int nClusters, BufferedImage bImg)
    {
        super(iTer, nClusters, bImg);
        this.rgbClusters = new rgbClass[this.nClusters];
    }
    
    public rgbClass[] getClusters ()
    {
        return (this.rgbClusters);
    }
    
    public void initCLusters() 
    {
        int imgWidth    = this.bImg.getWidth();
        int imgHeight   = this.bImg.getHeight();
        
        for(int i = 0; i < this.nClusters; i++)
        {
            int widthRand  = (int) (Math.random() * (imgWidth - 1));
            int heightRand = (int) (Math.random() * (imgHeight - 1));
            this.rgbClusters[i] = uClass.getColor(this.bImg, widthRand, heightRand);
        }
    }
    
    public void startKmeans ()
    {
        boolean stable = true;
        int[] red   = new int[this.nClusters];
        int[] blue  = new int[this.nClusters];
        int[] green = new int[this.nClusters];
        int[] cntr  = new int[this.nClusters];
        
        int[] ared   = new int[this.nClusters];
        int[] ablue  = new int[this.nClusters];
        int[] agreen = new int[this.nClusters];
        
        for(int i = 0; i < this.nClusters; i++)
        {
            red[i]      = 0;
            blue[i]     = 0;
            green[i]    = 0;
        }

        do
        {
            for(int i = 0; i < this.nClusters; i++)
            {
                red[i]      = 0;
                blue[i]     = 0;
                green[i]    = 0;
                cntr[i]     = 0;
            }
            
            for(int i = 0; i < this.bImg.getWidth(); i++)
            {
                for(int j = 0; j < this.bImg.getHeight(); j++)
                {
                    int whatCluster = 0;
                    rgbClass rgbColor = this.uClass.getColor(this.bImg, i, j);
                    int cDistance = this.uClass.getDistance(rgbColor, this.rgbClusters[0]);
                    
                    for(int k = 0; k < this.nClusters; k++)
                    {
                        int tDistance = this.uClass.getDistance(rgbColor, this.rgbClusters[k]);
                        if(tDistance < cDistance)
                        {
                            cDistance = tDistance;
                            whatCluster = k;
                        }
                    }
                    
                    cntr[whatCluster]++;
                    red[whatCluster] += rgbColor.getRed();
                    blue[whatCluster] += rgbColor.getBlue();
                    green[whatCluster] += rgbColor.getGreen();
                }
            }
            
            for(int i = 0; i < this.nClusters; i++)
            {
                if(cntr[i] != 0)
                {
                    red[i] = (int) (red[i] / cntr[i]);
                    blue[i] = (int) (blue[i] / cntr[i]);
                    green[i] = (int) (green[i] / cntr[i]);

                    this.rgbClusters[i] = new rgbClass(red[i], green[i], blue[i]);
                }   
            }
            
             
            stable = true;
            for(int i = 0; i < this.nClusters; i++)
            {
                if(red[i] != ared[i]){ stable = false; ared[i] = red[i]; }
                if(blue[i] != ablue[i]){ stable = false; ablue[i] = blue[i]; }
                if(green[i] != agreen[i]){ stable = false; agreen[i] = green[i]; }
            }
            
            
        }while(!stable && this.iTer-- > 0);
    }
}
