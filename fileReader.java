import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class fileReader {
    Scanner scanner = null;
    
    public fileReader (String fName) throws FileNotFoundException
    {
        this.scanner = new Scanner(new File(fName));
    }
    
    public Vector getColors ()
    {       
        Vector list = new Vector();

        while(this.scanner.hasNext())
        {
            Vector v = new Vector();
            
            v.add(Integer.valueOf(this.scanner.next()));
            v.add(Integer.valueOf(this.scanner.next()));
            v.add(Integer.valueOf(this.scanner.next()));

            v.add(Double.valueOf(this.scanner.next()));
            v.add(Double.valueOf(this.scanner.next()));
            v.add(Double.valueOf(this.scanner.next()));
            v.add(Double.valueOf(this.scanner.next()));
            v.add(Double.valueOf(this.scanner.next()));
            v.add(Double.valueOf(this.scanner.next()));
            v.add(Double.valueOf(this.scanner.next()));
            v.add(Double.valueOf(this.scanner.next()));
            v.add(Double.valueOf(this.scanner.next()));
            v.add(Double.valueOf(this.scanner.next()));
            v.add(Double.valueOf(this.scanner.next()));
            
            list.add(v);
        }
        
        this.scanner.close();
        return (list);
    }
}
