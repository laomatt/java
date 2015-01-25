
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mattlao
 */
public class generic extends decodeKey{
    
    public static void main(String[] args)
        {
            GetOpt g = new GetOpt(args, "ed");
        int c=0;
        boolean encr = false;
       
        while((c=g.getopt()) !=-1)
        {
        switch(c)
        {
            case 'e':encr=true;break;
            case 'd':encr=false;break;
        }
        }
        
        String[] fi = g.getarg();
        
          for(String sr: fi)
            {

                /*my help choice*/
                if(encr)
                 {
                    System.out.print(Encypte(sr));

                 }
                else
                {
                    System.out.print(Decypte(sr)+" ");

                }

           }         
       
           System.out.println("");
        }
    
     static String[] alpha={"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","1","2","3","4","5","6","7","8","9","0","!","@","#","$","%","^","&","*","(",")","?","!",".",",",":",";","'","\"","/","|"};
     
  
  	  	static ArrayList<Integer> arraykey;  
    public static String Encypte(String in)
    {
          String out="";
          
               // StringTokenizer st = new StringTokenizer(in, " ");
                String[] temp=new String[in.length()];
                int y=0;
                while (y<in.length())
                {
                    temp[y]=in.charAt(y)+"";
                    y++;
                }
                
                //generate the key array
                Random ran = new Random();
                
                arraykey= new ArrayList();
                
                Scanner sc = new Scanner("key.csv");
                
                //map the arrayket to our charset
		int j = ran.nextInt(10);
                
                Integer[] ck=new Integer[alpha.length];
                
                HashMap<String, Integer> key= new HashMap();
                for(int i=0;i<alpha.length;i++)
                {
                    key.put(alpha[i], codekeys[j][i]);
                }
                
                for(String f:temp)
                {
                
                    out+=""+key.get(f)+".";
                    
                
                }
                return j+","+out+" ";
    
    }
    
    
    
    public static String Decypte(String in)
    {
        String out="";
        int key;
       
            HashMap<Integer, String> hm = new HashMap();
            String[] thw=in.split(",");
            key=Integer.parseInt(thw[0]);
            
            //generate approriate code key
            for(int i=0;i<alpha.length;i++)
                {
                    hm.put(codekeys[key][i],alpha[i]);
                }    
            
            //use code key to make the word
            
            StringTokenizer st = new StringTokenizer(thw[1],".");
            
            while (st.hasMoreTokens())
            {
                out+=hm.get(Integer.parseInt(st.nextToken()))+"";
            }

        return out+"";
    }
    
}
