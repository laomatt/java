/*
 *  Author: Abbas Moghtanei
 *  Objective: This interface provides several constants to set and modify
 *  terminal attributes.
*/

public interface TermAttr
{
     public String CLS     = "\033[2J";
     public String GOTO1_1 = "\033[1;1H";
     public String BOLD    = "\033[1m";
     public String BLINK   = "\033[5m";
     public String REVERSE = "\033[30m";
     public String EXTRA   = "\033[47m"; //black & white;
     public String REVERSE1= "\033[7m";  //color;
     public String ITALICS = "\033[2m";
     public String RESET   = "\033[0m";
     public String INVIS   = "\033[8m";
     //#******************** ForGround Colors *********************** 
     public String FBLACK  = "\033[30m";
     public String FRED    = "\033[31m";
     public String FGREEN  = "\033[32m";
     public String FYELLOW = "\033[33m";
     public String FBLUE   = "\033[34m";
     public String FMAGENTA= "\033[35m";
     public String FCYAN   = "\033[36m";
     public String FWHITE  = "\033[37m";
     //#******************** BackGround Colors ***********************
     public String BBLACK  = "\033[40m";
     public String BRED    = "\033[41m";
     public String BGREEN  = "\033[42m";
     public String BYELLOW = "\033[43m";
     public String BBLUE   = "\033[44m";
     public String BMAGENTA= "\033[45m";
     public String BCYAN   = "\033[46m";
     public String BWHITE  = "\033[47m";
}
