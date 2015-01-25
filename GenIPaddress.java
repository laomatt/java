
import java.awt.Container;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/*
Author: Matt Lao
Class: 211S
Date: 11-21-14
Title: HW#5  Random IP address generator
Discription:  
This program produces a set of 1000 unique IP adresses, and then checks for 
the uniqueness of the members of the set.

 */
public class GenIPaddress extends mylib{
    public static void main(String[] args)
    {
    Random r= new Random();
    //use a HashSet to garuntee uniqueness among our entries
    HashSet<String> hs = new HashSet<>();
    
    
    
    
    
    while (hs.size()<=1000)
    {
          //  hs.add(r.nextInt(255)+"."+r.nextInt(255)+"."+r.nextInt(255)+"."+r.nextInt(255));
    }
    
    
    
    
    
    
    
    /*
    println(hs);
    //this is just code to confirm that everything in our Container is unique.
    
    println("any duplicates will be printed below:");
    Object[] up = hs.toArray();
    
    int errors=0;
    int j=0;
    for (Object o:up)
    {
        int i=0;
        for(Object m:up)
        {           
            if(o.toString().equals(m.toString()) && i!=j)
            {
                errors++;
                println(o.toString());
            }
            i++;
        }
        j++;
    }
        // if nothing prints then we print out a message stating
        //that no duplicates were found
    if(errors==0)
        println("No duplicates found...");
    
    
    */
    
    
    }   
}
