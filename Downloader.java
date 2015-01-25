/*
Author: Matt Lao
Class: 211S
Date: 9-29-14
Title: Downloader
Discription:  This program uses three threads to download a file form a URL
 */

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*************************************************************************/
/***********This is my class used to download a file**********************/
class DownloadFile extends mylib implements Runnable
{
    final static int size = 1024;
    String filenamed="downloadedpicture";
    URL file;
    Thread ct;
    
    /********constructor**********//********constructor**********/
    public DownloadFile(String filename) throws MalformedURLException, IOException
    {
        file = new URL(filename); 
        //ct = t;
    }
    InputStream in;
    protected ByteArrayOutputStream out;
    int b;
    byte[] buf;
    @Override
    /*************************************************************************/
    /********************this method runs the thread**************************/
    public void run() 
    {
       DLthefile();
    
    }  
    /*************************************************************************/
    /***************Method used to send info on download status***************/
    public synchronized int getbyteprog()
    {
    b=out.size();
    
    return b;
    
    }
   /*************************************************************************/
   /**********************method used to download the file********************/
    public synchronized void DLthefile()
    {
     try 
        {
            /****code that downloads the file****/
            in = new BufferedInputStream(file.openStream());
            out = new ByteArrayOutputStream();
            buf = new byte[1024];
   
		 int n = 0;
    
		 while ((n=in.read(buf))!=-1)
		 {
                     
		   out.write(buf, 0, n);
               /*this prints out the size of the ByteArrayOutputStream 
                   as it downloads,  i was supposed to do this in another method
                   but could not figure out how to communicate between 
                   methods through a shared object.*/
                  print(out.size()+" bytes downloaded\r");
                   
		 }
		 out.close();
		 in.close();
		 byte[] response = out.toByteArray();
 
            try (FileOutputStream fos = new FileOutputStream(filenamed)) 
            {
                fos.write(response);
            }
            
            
            
        } catch (IOException ex) 
        {
            Logger.getLogger(DownloadFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
}

/**************class used to show progress of the download****************/
class ShowProgress extends mylib implements Runnable
{
    ArrayList<String> prog = new ArrayList<>();
    DownloadFile ct;
    Thread cthread;
    URL file;
    byte[] buf;
    
    /********constructor**********//********constructor**********/
    public ShowProgress(DownloadFile tr, Thread th)               
    {                                                             
        ct = tr;                                                    
        cthread = th;
        file=ct.file;
        
    }
    @Override
    /*************************************************************************/
    /**********runs the thread***********************************/
    public void run()
    {
      
        print("progress for "+ct.filenamed);
     
        while(cthread.isAlive())
        {
    /* this is supposed to call the getprog
            method that gets info 
            from the shared object between this thread and the 
            download thread.  
            
            i never got this part to work, or display!!*/
             getprog(); 
     
       }
        
        println("file finshed downloading");
        
    }
    /*************************************************************************/
    /***********method that gets the progress form the other thread***********/
    public synchronized void getprog()
    {
    print(ct.getbyteprog());
    
    }
}

/***************************************************************************/
/*******this class creates an animation while the file is downloading********/
class Spin extends mylib implements Runnable
{
    String[] anim= new String[10];
    
    //String[]=;
    
    
    File f;
    
    Thread cf;
    DownloadFile dlf;
    ShowProgress p;
    /********constructor**********//********constructor**********/
    public Spin(DownloadFile df, Thread f)
    {
        //p=sp;
        dlf =df;
        cf=f;   
        anim[0]=" *        * ";
        anim[1]="  *      *  ";
        anim[2]="   *    *   ";
        anim[3]="    *  *    ";
        anim[4]="     **     ";
        anim[5]="    *  *    ";
        anim[6]="   *    *   ";
        anim[7]="  *      *  ";
        anim[8]=" *        * ";
        anim[9]="*          *"; 
    }
    /*************************************************************************/
    /***********************runs the animation********************************/
    @Override
    public void run()
    {    
        int i=0;      
        while(cf.isAlive())
        {
        int j = i%anim.length;
        print("                                                 "+anim[j]+"\r");
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Spin.class.getName()).log(Level.SEVERE, null, ex);
            }
        i++;
        }
        println("URL "+dlf.file.toString()+" saved to file named: "+dlf.filenamed);
    }    
}
/*************************************************************************/
/******************************main class******************************/
public class Downloader extends mylib
{
    
    /*************************************************************************/
    /***************method that calls and executes the thread
     * @param args
     * @throws java.net.MalformedURLException*
     * @throws java.lang.InterruptedException**************/
    public static void main(String[] args) throws MalformedURLException, IOException, InterruptedException
    {
        GetOpt g = new GetOpt(args,"n");       
        String[] fi = g.getarg();       
        for(String sr:fi)
        {
            //creates thread
           DownloadFile dlf = new DownloadFile(sr);
           Thread t1 = new Thread(dlf);
           ShowProgress showp = new ShowProgress(dlf, t1);
            
           Thread t2 = new Thread(showp);
            
           Thread t3 = new Thread(new Spin(dlf, t1));
            
          //startst he threads
           t1.start();
           
           //this thread never worked for me :(
          // t2.start();
           
          // t2.join();
           t3.start();  
       
        }
    }  
}