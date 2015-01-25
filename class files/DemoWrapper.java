/*
 
 Author: Abbas Moghtanei 
 Date  : 09/04/14
 Program Name: DemoWrapper.java
 Objective: This program shows how to use mkWrapper. 
 
*/
import java.util.*;
import java.io.*;
 
class DemoWrapper
{
//******************************mkWrapper()*******************************
   public static void mkWrapper()
   {
       String s = Thread.currentThread().getStackTrace()[1].getClassName();
       String wrapperName = s;
       File f = new File(wrapperName);
       if(!f.exists())
       {
          try
          {
          PrintWriter pw = new PrintWriter(f);
          pw.println("#!/bin/bash");
          pw.println("export CLASSPATH=.:$CLASSPATH");
          pw.println("java $0 ${1+\"$@\"}");
          pw.close();
          }catch(FileNotFoundException e){System.err.println(e);}
       }
       try
       { 
          Process p;
          p = new ProcessBuilder("chmod", "+x", f.getAbsolutePath()).start();
       }catch(IOException e){System.err.println(e);}
   }
   public static void main(String args[])
   {
   
      mkWrapper();   // create the wrapper
      System.out.println("Hello Wrapper!!");
   }
}
