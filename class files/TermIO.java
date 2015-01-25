/*
 * Author: Abbas Moghtanei
 * Objective: This java program defines sevaral static methods using
 *            constants from the TermAttr interface.
*/
   
public class TermIO implements TermAttr
{
/********************************cls()*******************************/
   public static void cls()
   {
        System.out.print(CLS);
        System.out.print(GOTO1_1);
   }
/********************************bold()*******************************/
   public static void bold()
   {
        System.out.print(BOLD);
   }
/********************************blink()*******************************/
   public static void blink()
   {
        System.out.print(BLINK);
   }
/********************************reverse()*******************************/
   public static void reverse()
   {
        System.out.print(REVERSE);
        System.out.print(EXTRA);
   }
/********************************reverse1()*******************************/
   public static void reverse1()
   {
        System.out.print(REVERSE1);
   }
/********************************italics()*******************************/
   public static void italics()
   {
        System.out.print(ITALICS);
   }
/********************************reset()*******************************/
   public static void reset()
   {
        System.out.print(RESET);
   }
/********************************invis()*******************************/
   public static void invis()
   {
        System.out.print(INVIS);
   }
/********************************fblack()*******************************/
   public static void fblack()
   {
        System.out.print(FBLACK);
   }
/********************************fred()*******************************/
   public static void fred()
   {
        System.out.print(FRED);
   }
/********************************fgreen()*******************************/
   public static void fgreen()
   {
        System.out.print(FGREEN);
   }
/********************************fyellow()*******************************/
   public static void fyellow()
   {
        System.out.print(FYELLOW);
   }
/********************************fblue()*******************************/
   public static void fblue()
   {
        System.out.print(FBLUE);
   }
/********************************fmagenta()*******************************/
   public static void fmagenta()
   {
        System.out.print(FMAGENTA);
   }
/********************************fcyan()*******************************/
   public static void fcyan()
   {
        System.out.print(FCYAN);
   }
/********************************fwhite()*******************************/
   public static void fwhite()
   {
        System.out.print(FWHITE);
   }
/********************************bblack()*******************************/
   public static void bblack()
   {
        System.out.print(BBLACK);
   }
/********************************bred()*******************************/
   public static void bred()
   {
        System.out.print(BRED);
   }
/********************************bgreen()*******************************/
   public static void bgreen()
   {
        System.out.print(BGREEN);
   }
/********************************byellow()*******************************/
   public static void byellow()
   {
        System.out.print(BYELLOW);
   }
/********************************bblue()*******************************/
   public static void bblue()
   {
        System.out.print(BBLUE);
   }
/********************************bmagenta()*******************************/
   public static void bmagenta()
   {
        System.out.print(BMAGENTA);
   }
/********************************bcyan()*******************************/
   public static void bcyan()
   {
        System.out.print(BCYAN);
   }
/*******************************bwhite()*******************************/
   public static void bwhite()
   {
        System.out.print(BWHITE);
   }
/********************************locate()*******************************/
   public static void locate(int row, int col)
   {
           System.out.print("\033["+row+";"+col+"H");
   }
/********************************box()**********************************/
   public static void box(int r1, int c1, int r2, int c2, char c)
   {
       int i;
       int z = c2 - c1 + 1;
       String line="";
       for (i = 1; i <= z; i++)
       {
           line += c;
       } 
       locate(r1, c1); System.out.print(line);
       locate(r2, c1); System.out.print(line);
       i = r1 + 1;
       while ( i < r2 )
       {
           locate(i, c1); System.out.print(c);
           locate(i, c2); System.out.print(c);
           i++;
       }
  }
}   
