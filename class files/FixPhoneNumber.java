/*
 
 Author: Abbas Moghtanei 
 Date  : 06/25/11
 Program Name: FixPhoneNumber.java
 Objective: This program receives a phone number interactively, then
            validates it. If it is valid, then the phone number will be
            displayed. Otherwise, if the phone number is fixable, then 
            it will be fixed and displayed. This program runs in an
            infinite loop, until the user types stop or end, or quit
            or exit.
*/
 
import java.util.*;
 
public class FixPhoneNumber
{
//****************************getPhoneNumber()****************************
   public static String getPhoneNumber()
   {
       System.out.print("Type a phone number, stop|end|exit|quit "+
                           "stops the program:");
       Scanner sc = new Scanner(System.in);
       String phoneNo = sc.nextLine();
       if(phoneNo.isEmpty())
       {
           System.err.println("Empty string");
           System.exit(1);
       }
       if(phoneNo.equalsIgnoreCase("exit") ||
          phoneNo.equalsIgnoreCase("end")  ||
          phoneNo.equalsIgnoreCase("stop") ||
          phoneNo.equalsIgnoreCase("quit")) System.exit(0);
       
       return(phoneNo);
   }
//*******************************isNumber()*******************************
   public static boolean isNumber(String str)
   {
       for(int i = 0; i < str.length(); i++)
       {
          if(!Character.isDigit(str.charAt(i))) return(false);
       }
       return(true);
   }
//****************************isPhoneNumber()*****************************
   public static boolean isPhoneNumber(String phoneNo)
   {
       if(phoneNo.length() == 13   &&
          phoneNo.charAt(0) == '(' &&
          phoneNo.charAt(4) == ')' && 
          phoneNo.charAt(8) == '-' &&
          isNumber(phoneNo.substring(1,4) +
                   phoneNo.substring(5,8) +
                   phoneNo.substring(9))) return(true);
       else                               return(false);
   }
   
//******************************filterize()*******************************
   public static String filterize(String phoneNo)
   {  
       String digits = "";
       char c;
 
       for(int i = 0; i < phoneNo.length(); i++)
       {
          c = phoneNo.charAt(i);
          if( Character.isDigit(c) ) digits += c;
       }
       return(digits);
   }
//******************************isFixable()*******************************
   public static boolean isFixable(String phoneNo)
   {
       String digits = filterize(phoneNo);
        
       return((digits.length() == 10) ? true : false);
   }
//******************************fixPhoneNo()******************************
   public static String fixPhoneNo(String phoneNo)
   {
       String digits = filterize(phoneNo);
        
       String phoneNum= "(" + digits.substring(0,3) + ")" +
                               digits.substring(3,7) + "-" +
                               digits.substring(7);
       return(phoneNum);
   }     
//*********************************main()*********************************
   public static void main(String args[])
   {
       String phoneNo;
        
       for(;;)
       {
           phoneNo = getPhoneNumber();
           if(isPhoneNumber(phoneNo))
            {
               System.out.println(phoneNo);
            }
            else if(isFixable(phoneNo))
            {
                phoneNo = fixPhoneNo(phoneNo);
                System.out.println(phoneNo);
            }
            else
            {
                System.err.println(phoneNo + " Invalid phone number, " + 
                                             "it should be: (xxx)xxx-xxxx");
            }
        } 
   }
}
