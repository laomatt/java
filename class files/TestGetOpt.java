/*
 
 Author:  Abbas Moghtanei 
 Date  :  09/19/07
 Program Name: TestGetOpt.java
 
*/
 
import java.util.*;
 
class TestGetOpt
{
   public static void main(String args[])
   {
         GetOpt g = new GetOpt(args, "a:bcd:");
         int c;

         System.out.println("command-line option(s):");
         g.opterr(false);  // suppress display error messages 

         while( (c = g.getopt()) != -1)
         {
             switch(c)
             {
             case 'a': String age = g.getvalue(c); 
                       System.out.println("I am "+ age + " years old"); break;
             case 'b': System.out.println("b="+(char)c); break;
             case 'c': System.out.println("c="+(char)c); break;
             case 'd': System.out.println("d value ="+g.getvalue(c)); break;
             case '?': System.out.println("Wrong option:"+ g.optopt());
                       System.exit(1);
             }
         }

         String t[] = g.getarg();           // get filename(s)
         System.out.println("Filename(s):");
         for(String s: t) if(!s.equals("")) System.out.println(s);
   }
}
