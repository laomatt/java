/*
* Matthew Lao
* cs 111b
* CRN: 52499-001 
* StateStats
* July 17th 2014  
*/

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.StringTokenizer;
import javax.swing.JFrame;
import javax.swing.JList;




public class StateStats {
 
static String pathto = "C:/Users/Matt/workspace/states/src/";
//static String[] lista;
    public static void main(String[] args) throws FileNotFoundException, IOException {
       ReadFile st = new ReadFile(pathto+"stateAbbrev.csv");
       ReadFile abPop = new ReadFile(pathto+"AbbrevPop.csv");      
       ReadFile Perc = new ReadFile(pathto+"PopPercentAlpha.dat");
      PrintWriter writer = new PrintWriter("C:/Users/Matt/workspace/states/src/StateAbbPop.csv","UTF-8");
      
      /*we we make our state object by using our methods in our ReadFile class*/
        State CA = new State(st.lookforThisLineAndReturnFirst("CA", 1),st.lookforThisLineAndReturnSecond("CA", 1),abPop.lookforThisLineAndReturnSecond("CA", 1), Perc.lookforThisinBinaryFile(st.place));
      //to test out our object we invoke the display method it has 
       CA.display();

       
       //this is the header for our new CSV file
      writer.write("State, Abbrev, Population, %of US");
      writer.write("\n");
      //  our array to use in our jList
//      for(int a=0;a<50;a++){lista[a]=st.arry[a];}
       
      
       //here we populate the new CSV file with values
       for(int p=1;p<51;p++){
           String sta= st.lookforThisLineAndReturnFirst(st.arry[p], 1);
           String abr= st.lookforThisLineAndReturnSecond(st.arry[p], 1);
           String pop= abPop.lookforThisLineAndReturnSecond(abPop.arry[p], 1);
           float pe= Perc.lookforThisinBinaryFile(st.place);
           writer.write(sta+", "+abr+", "+pop+", "+pe);
           writer.write("\n");
             }
writer.close();

JFrame window = new JFrame();

JList list = new JList(st.arry);
window.setAlwaysOnTop(true);
window.setVisible(true);
window.setSize(500, 1000);
window.add(list);
window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }
    
}

abstract class listen implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) { 
    //code that reacts to the action... 
}

}

//this class uses recursion to parse through a given file (used for the text file)
class ReadFile{
    String path;
    int place;  //this variable catches the exact line our match takes place at
    String[] arry = new String[51];
            //constructors for this class
            ReadFile(String pa) throws FileNotFoundException, IOException{
                path = pa;
                    FileReader fr = new FileReader(path);
                     BufferedReader textReader = new BufferedReader(fr);
                
            for(int i=0;i<51;i++){arry[i]=textReader.readLine();}
            }
   //this method searches the array initialized in the constructor starting at index t and returns the line
    public String LookforThisStringInFirsCol(String look, int t) throws FileNotFoundException, IOException{

        if(arry[t].contains(look)){
             place=t-1;  //we account for the column header row
             return arry[t].toString();}
        else {return LookforThisStringInFirsCol(look, t+1);}
    }
    //this method searches the array initialized in the constructor starting at index t and returns what ever is in the second column
    public String lookforThisLineAndReturnSecond(String look, int t) throws FileNotFoundException, IOException{
    
        if(arry[t].contains(look)){
            // we use a StringTokenizer object to get the second value
            StringTokenizer to = new StringTokenizer(arry[t].toString(),",",false);
            String one=to.nextToken();
            String two = to.nextToken();
            place=t-1;  //we account for the column header row
            return two;            
            }
        
        else {return lookforThisLineAndReturnSecond(look, t+1);}
    
    }
    
        /*this method searches the array initialized in the constructor starting at index t and returns what ever is in the first column*/
    public String lookforThisLineAndReturnFirst(String look, int t) throws FileNotFoundException, IOException{
    
        if(arry[t].contains(look)){
            // we use a StringTokenizer object to get the first value
            StringTokenizer to = new StringTokenizer(arry[t].toString(),",",false);
            String one=to.nextToken();
            String two = to.nextToken();
            place=t-1;  //we account for the column header row
            return one;            
            }
        
        else {return lookforThisLineAndReturnFirst(look, t+1);}
    
    }
    
    /*this class uses the a RandomAccessFile object to look for the appropriate position in the binary file and returns a float*/
    public float lookforThisinBinaryFile(int r) throws FileNotFoundException, IOException{
    RandomAccessFile Percent = new RandomAccessFile(path,"r");
    float[] arr=new float[r+1];
    for(int t=0;t<=r;t++){
        arr[t]=Percent.readFloat();
            }
    return arr[arr.length-1];
    
    }
    
}
/*this is the class i made to describe a state*/
class State{
    //state fields
	String StName;
	String StAbbrev;
	String population;
	int populationint;
	float percentage;
	
	State(String name, String ab, String pop, float percent){
		StName=name;
		StAbbrev=ab;
		population=pop;
		percentage=percent;
		}
	
	State(String name, String ab, int pop, float percent){
		StName=name;
		StAbbrev=ab;
		populationint=pop;
		percentage=percent;
		}
	//these methods display the information in various ways
	void display(){
		System.out.println("State name:  "+StName);
		System.out.println("State Abbreviation:  "+StAbbrev);	
		System.out.println("State population:  "+population);
		System.out.println("State US percentage:  "+percentage);
}
	
	String displayStatename(){return StName;}
	String displayStateAb(){return  StAbbrev;}
	String displayStatePop(){return population;}
	float displayStatePerc(){return percentage;}

	}