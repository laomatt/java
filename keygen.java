
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mattlao
 */
public class keygen {
    
    public static void main(String[] args) {
        
        System.out.println("public class decodeKey{");
       String[] alpha={"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","1","2","3","4","5","6","7","8","9","0","!","@","#","$","%","^","&","*","(",")","?","!",".",",",":",";","'","\"","/","|"};
       
        int k=0;
       // HashSet<Integer> drawn = new HashSet();
        /*
         while (k<=10)
         {
            ArrayList<Integer> dr = new ArrayList<>();
            Random r = new Random();
              System.out.print("");
         while(dr.size()<=alpha.length)
         {
             int num=r.nextInt(10000);
             
             if (!dr.contains(num))
             {
               
                    dr.add(num);
                    System.out.print(num+",");
                 
                 
             }
         
         }
         System.out.println();
         k++;
        }*/
         
       ////  System.out.print(drawn);
        
           int max=40;
           
       for(k=0;k<max;k++)
       {
         System.out.print("static Integer[] codeKey"+k+"={");
         Random r = new Random();
         ArrayList<Integer> dr = new ArrayList<>();
         int i=0;
         while(i<alpha.length)
            {
                int num=r.nextInt(10000);
                 if (!dr.contains(num))
                {
                    dr.add(num);

                       if (i==alpha.length-1)
                          System.out.print(num+"");

                       else
                          System.out.print(num+", ");

                       i++;
                }

            }
         
        System.out.println("};");
       }
       
        
        System.out.print("static Integer[][] codekeys = {");
        
        for(int i=0;i<max;i++)
        {
            if (i==max-1)
                System.out.print("codeKey"+i+"};");
            else
                System.out.print("codeKey"+i+", ");
        
        }
        
    
        System.out.println("}");
    }
    
    
}
