import java.awt.Color;
import java.awt.image.BufferedImage;

public final class rgbMeans extends kmeansCLass {
    
    private Color[] rgbClusters = null;
    
    public rgbMeans (int iTer, int nClusters, BufferedImage bImg)
    {
        super(iTer, nClusters, bImg);
        this.rgbClusters = new Color[this.nClusters];
    }
    
    public Color[] getClusters ()
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
        float[] red   = new float[this.nClusters];
        float[] blue  = new float[this.nClusters];
        float[] green = new float[this.nClusters];
        float[] cntr  = new float[this.nClusters];
        
        float[] ared   = new float[this.nClusters];
        float[] ablue  = new float[this.nClusters];
        float[] agreen = new float[this.nClusters];
        
        for(int i = 0; i < this.nClusters; i++)
        {
            ared[i]      = 0;
            ablue[i]     = 0;
            agreen[i]    = 0;
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
                    Color rgbColor = this.uClass.getColor(this.bImg, i, j);
                    
                    double cDistance = this.uClass.getDistance(rgbColor, this.rgbClusters[0]);
                    whatCluster = 0;
                    
                    for(int k = 1; k < this.nClusters; k++)
                    {
                        double tDistance = this.uClass.getDistance(rgbColor, this.rgbClusters[k]);
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
                    red[i] = (red[i] / cntr[i]);
                    blue[i] = (blue[i] / cntr[i]);
                    green[i] = (green[i] / cntr[i]);

                    this.rgbClusters[i] = new Color(Math.round(red[i]), Math.round(green[i]), Math.round(blue[i]));
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
