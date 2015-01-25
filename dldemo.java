
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mattlao
 */
public class dldemo {
    
    public static void main(String[] args) throws IOException{
    
        DLFile df = new DLFile("https://www.google.com/images/srpr/logo11w.png", "mrpic.png");
    
    
    }
    
}


class DLFile extends mylib{
    String f;
    URL file;
    public DLFile(String filename, String filesaved) throws MalformedURLException, IOException
    {
        file = new URL(filename);
        f= filesaved;      
       
    }
    
    public static void download() throws IOException
    {
        
    }
    
    
    
    
    
    
    
}
    
    
    
    

    



