
import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.Vector;

public class colorNaming {
    private Color rgbColor = null;
    private int nFila = 0;
    private int nCol = 0;
    
    public colorNaming(Color rgbColor)
    {
        this.rgbColor = rgbColor;
        this.nFila = 0;
        this.nCol = 0;
    }
    
    private void calculateColor(String fName) throws FileNotFoundException
    {
        fileReader fReader = new fileReader(fName);
        Vector list = fReader.getColors();
        Vector aux  = ((Vector) list.get(0));
        
        utilsClass uCalss = new utilsClass();
        int r = ((Integer) aux.elementAt(0));
        int g = ((Integer) aux.elementAt(1));
        int b = ((Integer) aux.elementAt(2));
        
       
        
        double cDistance = uCalss.getDistance(rgbColor, new Color(r, g, b));
        this.nFila = 0;
        
        for(int i = 1; i < list.size(); i++)
        {
            aux  = ((Vector) list.get(i));
            for(int j = 1; j < aux.size(); j++)
            {
                r = ((Integer) aux.elementAt(0));
                g = ((Integer) aux.elementAt(1));
                b = ((Integer) aux.elementAt(2));
                
                double tDistance = uCalss.getDistance(rgbColor, new Color(r, g, b));
                if(tDistance < cDistance)
                {
                    cDistance = tDistance;
                    this.nFila = i;
                }
            
            } 
        }
        
        aux = ((Vector) list.get(this.nFila));
        double fColor = ((Double) aux.get(3));
        this.nCol = 3;
        
        for(int i = 4; i < aux.size(); i++)
        {
            if(((Double) aux.get(i)) > fColor)
            {
                fColor = ((Double) aux.get(i));
                this.nCol = i;
            }
        }
    }
    
    public String getName(String fName) throws FileNotFoundException
    {
        this.calculateColor(fName);
        String cName = "No conocido";
        
        if(this.nCol == 3)
        {
            cName = "red";
        }
        
        if(this.nCol == 4)
        {
            cName = "orange";
        }
        
        if(this.nCol == 5)
        {
            cName = "brown";
        }
        
        if(this.nCol == 6)
        {
            cName = "yellow";
        }
        
        if(this.nCol == 7)
        {
            cName = "green";
        }
        
        if(this.nCol == 8)
        {
            cName = "blue";
        }
        
        if(this.nCol == 9)
        {
            cName = "purple";
        }
        
        if(this.nCol == 10)
        {
            cName = "pink";
        }
        
        if(this.nCol == 11)
        {
            cName = "white";
        }
        
        if(this.nCol == 12)
        {
            cName = "grey";
        }
        
        if(this.nCol == 13)
        {
            cName = "black";
        }
        
        return (cName);
    }
    
}
