
import java.lang.reflect.*;
import java.lang.Class;
/**
Author: Matt Lao
Class: 211S
Date: 10-11-14
Title: Probe.java (HW# 3)
Description: takes options and a class in java.lang library and returns, 
* based on the option switch, displays info on the class.
 */



public class probe extends mylib{
    
        /*this is my method that displays the help text*/
    public static void help()
    {
    
    println("-c :  Info about Constructors");
    println("-m :  Info about Methods");
    println("-v :  Info about Variables");
    println("-a :  Info about everthing");
    println("-C :  Info about all the Constants");
    println("-i :  Info about the interfaces");
    println("-h :  help");
    println("");
    
    
    
    
    /*main method that handles all operations*/
    }
    
    public static void main(String[] args) throws ClassNotFoundException
    {   
       
        GetOpt g = new GetOpt(args, "cmvaCih");
        int c=0;
        boolean help = false;
        boolean interfaces = false;
        boolean constants = false; 
        boolean all = false;
        boolean variables = false;
        boolean methods = false;
        boolean constructors = false;
        
         
        while((c=g.getopt()) !=-1)
        {
        switch(c)
        {
            case 'h':help=true;break;
            case 'i':interfaces=true;break;
            case 'C':constants=true;break;
            case 'a':all = true;break;
            case 'v':variables = true;break;
            case 'm':methods = true;break;
            case 'c':constructors = true;break;
            default: System.err.println("error: invalid option");
            System.exit(1);
            break;
        
        }
        }
        
        
        String[] fi = g.getarg();
        
  for(String sr: fi)
  {
      
      /*my help choice*/
      if(help)
       {
       help();
       System.exit(1);
       }
      else
        {
            /*this allows us to type in a class name in the java.lang
            library,  if we wanted a broader range of Classes to choose from
            we would leave this as "" and the user would have to type out 
            the full class name i.e. "java.lang.String" or "java.util.Random"*/
            String prefix="java.lang.";
            
            ClassLoader classLoader = probe.class.getClassLoader();
        
  /*here we initialie the Class object with a class loader with out java.lang prefix and the 
            string of the current iteration*/
              Class aClass = classLoader.loadClass(prefix+sr);
              /*my all info choice*/
                if(all)
                {
                  
                    variables=true;
                    interfaces=true;
                    methods=true;
                    constructors=true;
                    constants=true;
                    
                }
            /*constructor info option*/
                if(constructors)
                {
                    Constructor conts[] = aClass.getConstructors();
                    println("----------------Constructors for "+sr+"----------------");
                    for(Constructor gt: conts)
                    {
                    print("          ");
                    
                    
                    print("(");
                    for(int i=1;i<gt.getParameterTypes().length;i++)
                    {
                    print(gt.getParameterTypes()[i]+", ");
                    }
                    println(")");
                    }
                    println(""); println("");
                }
                /*method info option*/
                if(methods)
                {
                    println("---------------- Methods for"+ sr+ "----------------");
                    Method meths[] = aClass.getMethods();
                    int j=0;
                    for(Method me: meths)
                    {
                    
                    println("---"+": "+sr+ " Method: "+ me.getName());
                    
                    
                    print(" Parameter types:");

                    for(int i=0; i < me.getParameterTypes().length;i++)
                    {
                        Class params = me.getParameterTypes()[i];
                        print("         "+params.getSimpleName());
                    }
                   println("");
                    println("         Returns: "+me.getReturnType().getSimpleName());
                    
                    j++;
                    
                    }
                println(""); println("");
                }
                /*variable info option*/
                if(variables)
                {
                    println("---------------- Variables for"+ sr+ "----------------");
                    Field vars[] = aClass.getFields();
                    int j=0;
                    for(Field va: vars)
                    {
                    
                    println("  "+va.getType().getSimpleName()+"      "+va.getName());
                    }
                println(""); println("");
                }
                
                /*the final Field info choice*/
                if(constants)
                {
                               println("---------------- Constants for"+ sr+ "----------------");
                    Field cons[] = aClass.getDeclaredFields();
                    for(Field cfo : cons){
                      println("     "+cfo.getName());
                    
                    }
                    
                println(""); println("");
                }
                
                /*choice for the interfaces*/
                
                if(interfaces)
                {
                    
                            println("---------------- Interfaces for "+ sr+ "----------------");
                    Class intf[] = aClass.getInterfaces();
                    
                    for(Class cal: intf)
                    {
                    
                    println("  "+cal.getSimpleName());
                    }
                    
                    
                println(""); println("");
                }   
        } 
  }   
}
}


    