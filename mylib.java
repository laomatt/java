
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//package cs211s;

/**
 *
 * @author mattlao
 */
public class mylib extends commandline
{

public static void die(String ...msg)
{
    if(msg.length==0)System.exit(1);
    System.err.println(msg[0]);
    System.exit(1);

}


public static void println(Object ...o)
{
    for(int i=0;i<o.length;i++)
    {
        System.out.print(o[i]+" ");
    }
    
    System.out.println();

}


public static void print(Object ...o)
{
    for(int i=0;i<o.length;i++)
    {
        System.out.print(o[i]+" ");
    }
    
    //System.out.println();

}
public static void sleep(long msec)
    {
       try
       {
       Thread.sleep(msec);
       }catch(InterruptedException e)
       {}
    }

public static int rand(int a, int b)
{
    return ((int)((b-a+1)+Math.random()+a));

}

}

/*
Author: Matt Lao
Class: 211S
Date: 9-20-14
Title: System command exectuer (Extracredit assignment)
Discription:  This program has a method (system)to run system commands and 
                a main method to test it.  
                
                    The system method takes the output of the command
                (passed as an agrument) and returns its output in an array.

                them the main method prints out each array to verify the output.

 */



class commandline 
{

    /**********This is my system method for the command line command**********
     * here we read the command passed as an argument to the system method and 
     * decided how it should be treated, and which additional method it should 
     * be passed to, based to what kind of characters it has,  this is also 
     * where we check for multiple commands as separated by the ';' character. 
     * 
     * @param command
     * @return truth  ****/
    public static String[] system(String command)
    {
    String[] truth={};
    ArrayList<String> flight = new ArrayList<>();
        if(command.contains(";"))
        {
            /*if the command contains a semi-colon (to seperate commads),
            we seperate the commands with a StringTokenizer, with a semicolon as 
            a delimiter, then put it in an arraylist and then run each element 
            in the arraylist as a command*/
            StringTokenizer r = new StringTokenizer(command,";");
            do
            {
                flight.add(r.nextToken());
                
            }while(r.hasMoreTokens());
    
            /*now we make a new arraylist consisting of String arrays
            for the output of all the commands*/
            
            ArrayList<String[]> combined = new ArrayList<>();
            
        for (String command2 : flight) {
            /*we cycle though all commands and send then, based on what kinds of
            special characters they have to the appropriate method*/
            if(command2.contains("*")||command2.contains("|")||command2.contains(">"))
            {
                 combined.add(system1(command2));
            }
            else
            {
                combined.add(system2(command2));
            }
        }    
       /* now we combine all the string values into the one large
        String arrayList */
        
            ArrayList<String> mother = new ArrayList<>();

            for(String[] com : combined)
            {
                mother.addAll(Arrays.asList(com));                     
            }

            /*now we convert mother to the truth array*/
            truth = new String[mother.size()];
            int t=0;
            for(String opie : mother)
            {
                truth[t]=opie;
                t++;
            }
        
        
        }        
        /*if no semi-colon is detected, then we just run the command normally*/
        else
        {
                /*this is where we send commands, based on what kinds of 
                special characters they have to the appropriate method*/
                if(command.contains("*")||command.contains("|")||command.contains(">"))
                {
                    truth = system1(command);
                }
                else
                {
                    truth = system2(command);
                } 
        }
    //we finally return the truth array
    return truth;
    }
    
 /****the methods below, system1 and system2 
                    deal with actually executing commands*****/   
  /***********************************************************************/
/****This is my method for dealing with commands containing special characters
     * @param command*    
     * @return omega  **/    
    public static String[] system1(String command)
    {
        String[] omega={};
        
        /*because the command contains a special character 
                it must be treated as a whole command here*/
       
            ArrayList<String> beta = new ArrayList<>();
                 try{

                 ProcessBuilder p = new ProcessBuilder("/bin/sh","-c",command); 
                 p.directory(new File("."));

                 Process pr = p.start();
                 InputStream is = pr.getInputStream();
                 InputStreamReader sr = new InputStreamReader(is);
                 BufferedReader br = new BufferedReader(sr);
                  /*storing the output in an arraylist*/
                 String line;
                 while((line=br.readLine()) != null)
                 {
                     beta.add(line);
                 }
                 /*converting the arraylist to a returnable array*/
                 omega = new String[beta.size()];

                 for(int i=0;i<beta.size();i++)
                 {
                     omega[i]=beta.get(i);
                 }


                    }catch(IOException e){System.err.println("error:"+e);}   
   
        
       
       return omega;
    }
    
/***********************************************************************/
/**
     * @param command**This is my method for dealing with commands 
     * with no special characters*  ******************  
     * @return omega  ***/    
    
    public static String[] system2(String command)
    {
            String[] omega = {};
            ArrayList<String> beta = new ArrayList<>();
            ArrayList<String> Gamma = new ArrayList<>();
            /*in this case it is nessesary to Tokenize the command*/
            StringTokenizer alpha = new StringTokenizer(command," ");
            /*and store the tokens in an arraylist that can be read in the 
            ProcessBuilder constructor*/
            do
            {
            Gamma.add(alpha.nextToken());
            }while(alpha.hasMoreTokens());
        
         try{
                            
                 ProcessBuilder p = new ProcessBuilder(Gamma); 
                 p.directory(new File("."));

                 Process pr = p.start();
                 InputStream is = pr.getInputStream();
                 InputStreamReader sr = new InputStreamReader(is);
                 BufferedReader br = new BufferedReader(sr);
                 /*storing the output in an arraylist*/
                 String line;
                 while((line=br.readLine()) != null)
                 {
                     beta.add(line);
                 }
                 
                 omega = new String[beta.size()];
                 /*converting the arraylist to a returnable array*/
                 for(int i=0;i<beta.size();i++)
                 {
                     omega[i]=beta.get(i);
                 }
                
             
                }catch(IOException e){System.err.println("error:"+e);} 
    
    return omega;
    }
}



