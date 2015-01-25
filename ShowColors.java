/*
Author: Matt Lao
Class: 211S
Date: 10-29-14
Title: Color Picker (HW#4)
Discription:  this program lets you create your own color by adding via
sliders a specific amount of red, green and blue and outputs a sample 
of the choosen sample in the form of an oval.  It also has a reset button
that resets the color and a save button to save a color to a palette.

 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/****************************************************************************/
/************************Listener class**************************************/
   class SliderListener implements ChangeListener, ActionListener 
   {
       
  //the dynamic labels
        JLabel redlabel=new JLabel("<html><font color='red'>Red Level</font></html>");
        JLabel bluelabel=new JLabel("<html><font color='blue'>Blue Level</font></html>");
        JLabel greenlabel=new JLabel("<html><font color='green'>Green Level</font></html>");
        JLabel samplerlabel=new JLabel("Color Sample here, use sliders to start");
        
        JPanel samplesave=new JPanel();
        JSlider thi=new JSlider();
        JSlider thir=new JSlider();
        JSlider thig=new JSlider();
        JSlider thib=new JSlider();
        JButton resetbutton=new JButton();
        JButton savebutton=new JButton();
 
        Integer colorLevelr;
        Integer colorLevelg;
        Integer colorLevelb;
        JPanel sample = new JPanel();
        
        Integer maxsize=255;
        Integer minsize=0;
        Integer tick=10;
        Integer MajtickSpaceing=50;
        Integer MintickSpaceing=5;
        
        //boolean values for my radio buttons
        boolean dec=true;
        boolean hex=false;
        boolean bin=false;
        boolean oct=false;
        
       // String r;//= colorLevelr.toString();
        //hex
       // String hexr = Integer.toHexString(i);
        
            ///draw the output circle
        MyCanvasOval mco= new MyCanvasOval();
            ///draw the bars
            MyRect redbar = new MyRect();
            MyRect bluebar = new MyRect();
            MyRect greenbar = new MyRect();
           
            
     
/***************** this is for the sliders Listener*****************/  
        @Override
        public void stateChanged(ChangeEvent e)
        {
  
           
            ///use the setwidth methods in each rectangle class to set the new 
            ///widths dynamically
            redbar.setwidth(thir.getValue()*2);
            bluebar.setwidth(thib.getValue()*2);
            greenbar.setwidth(thig.getValue()*2);

            //then we repaint each of the bars
            redbar.repaint();
            bluebar.repaint();
            greenbar.repaint();
                
            //code the change the color of the sample window
            //first the color levels are changed according to
            //the slider values
              colorLevelr=thir.getValue();
              colorLevelg=thig.getValue();
              colorLevelb=thib.getValue();
 
             //uses my MyCanvasOval classes fill method to update the new color
              
              //the new repainted color
              Color newcolor=new Color(colorLevelr, colorLevelg, colorLevelb);
              mco.fill(newcolor);
              //then repaints the oval
              mco.repaint();
              
              samplerlabel.setText("<html><font size='24'> Color sample </font></html>");
              samplerlabel.setForeground(newcolor);
              
              
              resetbutton.setForeground(newcolor);
              
              
              //updating the labels
             // this codle updates the number label reports according to which radio button selected.
             if(dec) 
             {
             redlabel.setText("<html><font color='red'>Red color level: (Decimals):  "+colorLevelr.toString()+"</font></html>");
             greenlabel.setText("<html><font color='green'>Green color level: (Decimals):  "+colorLevelg.toString()+"</font></html>");
             bluelabel.setText("<html><font color='blue'>Blue color level: (Decimals):  "+colorLevelb.toString()+"</font></html>");
             
             }
             else if(hex) 
             {
             redlabel.setText("<html><font color='red'>Red color level: (Hex):  "+Integer.toHexString(colorLevelr)+"</font></html>");
             greenlabel.setText("<html><font color='green'>Green color level: (Hex):  "+Integer.toHexString(colorLevelg)+"</font></html>");
             bluelabel.setText("<html><font color='blue'>Blue color level: (Hex):  "+Integer.toHexString(colorLevelb)+"</font></html>");
             }
             else if(bin) 
             {
             redlabel.setText("<html><font color='red'>Red color level: (Binary):  "+Integer.toBinaryString(colorLevelr)+"</font></html>");
             greenlabel.setText("<html><font color='green'>Green color level: (Binary):  "+Integer.toBinaryString(colorLevelg)+"</font></html>");
             bluelabel.setText("<html><font color='blue'>Blue color level: (Binary):  "+Integer.toBinaryString(colorLevelb)+"</font></html>");
             }
             else if(oct) 
             {
             redlabel.setText("<html><font color='red'>Red color level: (Octal):  "+Integer.toOctalString(colorLevelr)+"</font></html>");
             greenlabel.setText("<html><font color='green'>Green color level: (Octal):  "+Integer.toOctalString(colorLevelg)+"</font></html>");
             bluelabel.setText("<html><font color='blue'>Blue color level: (Octal):  "+Integer.toOctalString(colorLevelb)+"</font></html>");
             } 
        }
       
/********************************************************************/    
/********************* this is for the  buttons *******************/
    @Override
        public void actionPerformed(ActionEvent e) 
        {
         if (e.getSource() instanceof JButton)
            { 
                //code for the reset button
                if (e.getActionCommand().equals("reset"))
                {
                thir.setValue(0);
                colorLevelr=0;

                thig.setValue(0);
                colorLevelg=0;

                thib.setValue(0);
                colorLevelb=0;

                sample.setBackground(new Color(colorLevelr, colorLevelg, colorLevelb));
                }
                else if (e.getActionCommand().equals("save"))
                {
                //code for the save button
                    samplesave.setBackground(new Color(colorLevelr, colorLevelg, colorLevelb));
                    savebutton.setForeground(new Color(colorLevelr, colorLevelg, colorLevelb));
                
                }
            }

         else if (e.getSource() instanceof JRadioButton)
            {
                /*this is the code for the radio buttons  they basically 
                each set one boolean statement true and the rest false*/
                if (e.getActionCommand().equals("D"))
                        {
                            dec=true;
                            hex=false;
                            bin=false;
                            oct=false;
                            
             redlabel.setText("<html><font color='red'>Red color level: (Decimals):  "+colorLevelr.toString()+"</font></html>");
             greenlabel.setText("<html><font color='green'>Green color level: (Decimals):  "+colorLevelg.toString()+"</font></html>");
             bluelabel.setText("<html><font color='blue'>Blue color level: (Decimals):  "+colorLevelb.toString()+"</font></html>");
 
                        }
                else if(e.getActionCommand().equals("H"))
                {
                            dec=false;
                            hex=true;
                            bin=false;
                            oct=false;
             redlabel.setText("<html><font color='red'>Red color level: (Hex):  "+Integer.toHexString(colorLevelr)+"</font></html>");
             greenlabel.setText("<html><font color='green'>Green color level: (Hex):  "+Integer.toHexString(colorLevelg)+"</font></html>");
             bluelabel.setText("<html><font color='blue'>Blue color level: (Hex):  "+Integer.toHexString(colorLevelb)+"</font></html>");          
                
           // String temp=Integer.toHexString(255);
           //  maxsize=Integer.parseInt(temp);
                }
                
                 else if(e.getActionCommand().equals("B"))
                {
                
                            dec=false;
                            hex=false;
                            bin=true;
                            oct=false;
             redlabel.setText("<html><font color='red'>Red color level: (Binary):  "+Integer.toBinaryString(colorLevelr)+"</font></html>");
             greenlabel.setText("<html><font color='green'>Green color level: (Binary):  "+Integer.toBinaryString(colorLevelg)+"</font></html>");
             bluelabel.setText("<html><font color='blue'>Blue color level: (Binary):  "+Integer.toBinaryString(colorLevelb)+"</font></html>");             
                }
                
                 else if(e.getActionCommand().equals("O"))
                {
                            dec=false;
                            hex=false;
                            bin=false;
                            oct=true;
             redlabel.setText("<html><font color='red'>Red color level: (Octal):  "+Integer.toOctalString(colorLevelr)+"</font></html>");
             greenlabel.setText("<html><font color='green'>Green color level: (Octal):  "+Integer.toOctalString(colorLevelg)+"</font></html>");
             bluelabel.setText("<html><font color='blue'>Blue color level: (Octal):  "+Integer.toOctalString(colorLevelb)+"</font></html>");            
                
                }
            }
        }
        
   }

/******************************************************************************/
/*****************this is for drawing rectangles ******************************/
class MyRect extends JComponent
{
Color sam;
int width=0;
    @Override
    /***********method that paints the rectangle*************/
    public void paint(Graphics g)
    {
    g.setColor(sam);
    g.fillRoundRect(0, 40, width, 50, 45, 45);
    }
    /*************method that sets width dynamically*********/
    public void setwidth(int j)
    {    
    width=j;    
    }
    /*****methods that sets color dynamically************/
    public void setcolor(Color c)
    {
    sam=c;
    }


}

/******************************************************************************/
/*****************this draws the oval *****************************************/
class MyCanvasOval extends JComponent
{
  Graphics g;
  Color sam;
    //this paint method to draw the oval
  @Override
  public void paint(Graphics g) 
  {
    g.setColor(sam);
    g.fillOval(50, 50, 200, 300);
  }
  
  //the set method for the color of the oval
  public void fill(Color s)
  {
  sam=s;
  }
}
/*****************the main class *****************/
public class ShowColors extends JFrame{
    
  
/*****************this method creates and shows the gui
     * @param args *******************/
   //@param args
   public static void main(String args[])
   {
      //main window framework
        JFrame jf = new JFrame("Color picker");
        
        JLabel img= new JLabel(new ImageIcon("211sHWpanel.png"));
        jf.setSize(950, 700); 
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.getContentPane().setLayout(new BorderLayout());
        jf.setResizable(false);
        jf.add(img);
        
 /*******************action listener for everything **************************/
   /*this actionlistener object is an object of a class that implements both 
        changelistener and actionlistener,  so I can use the same object for 
        the radio buttons, the sliders and the save and reset buttons*/         
            SliderListener mainslide = new SliderListener();
            
/***********************radio buttons ****************************************/ 
        JPanel radio = new JPanel();
            radio.setPreferredSize(new Dimension(700,30));
            ButtonGroup grp = new ButtonGroup();
            JRadioButton jbdec = new JRadioButton();
            jbdec.setText("Decimal");
            jbdec.setToolTipText("convert color numbers to decimal");
            jbdec.addActionListener(mainslide);
            jbdec.setActionCommand("D");
            
            grp.add(jbdec);
            radio.add(jbdec);
            
            JRadioButton jbbin = new JRadioButton();
            jbbin.setText("Binary");
            jbbin.addActionListener(mainslide);
            jbbin.setToolTipText("convert color numbers to binary");
            jbbin.setActionCommand("B");
            grp.add(jbbin);
            radio.add(jbbin);
            
            JRadioButton jbhex = new JRadioButton();
            jbhex.setText("Hexagonal");
            grp.add(jbhex);
            jbhex.addActionListener(mainslide);
            jbhex.setActionCommand("H");   
            jbhex.setToolTipText("convert color numbers to hexagonal");
            radio.add(jbhex);
            
            JRadioButton jboct = new JRadioButton();
            jboct.setText("Octal");
            jboct.addActionListener(mainslide);
            jboct.setActionCommand("O");
            jboct.setToolTipText("convert color numbers to octal");
            grp.add(jboct);
            radio.add(jboct);           
/*****************************************************************************/
//*********************** color sliders **********************************//
        //red bar slider
        JSlider red = new JSlider(JSlider.VERTICAL, mainslide.minsize, mainslide.maxsize, 0);
            red.setMajorTickSpacing(mainslide.MajtickSpaceing);
            red.setMinorTickSpacing(mainslide.MintickSpaceing);
            red.setPaintTicks(true);
            red.setPaintLabels(true);
            red.setToolTipText("<html><font size='15'>Slide this to adjust the Red level</font></html>");
            
       //blue bar slider     
        JSlider blue = new JSlider(JSlider.VERTICAL, mainslide.minsize, mainslide.maxsize, 0);
            blue.setMajorTickSpacing(mainslide.MajtickSpaceing);
            blue.setMinorTickSpacing(mainslide.MintickSpaceing);
            blue.setPaintTicks(true);
            blue.setPaintLabels(true);  
            blue.setToolTipText("<html><font size='15'>Slide this to adjust the Blue level</font></html>");
            
        //green bar slider    
        JSlider green = new JSlider(JSlider.VERTICAL, mainslide.minsize, mainslide.maxsize, 0);
            green.setMajorTickSpacing(mainslide.MajtickSpaceing);
            green.setMinorTickSpacing(mainslide.MintickSpaceing);
            green.setPaintTicks(true);
            green.setPaintLabels(true);
            green.setToolTipText("<html><font size='15'>Slide this to adjust the Green level</font></html>");
/*****************************************************************************/  
            
         //*assigning mainslide Listener to each slider and button*/
            mainslide.thir=red;                 //assigns to the red slider
            red.addChangeListener(mainslide);
            
            mainslide.thig=green;               //assigns to the blue slider
            green.addChangeListener(mainslide);
            
            mainslide.thib=blue;                //assigns to the green slider
            blue.addChangeListener(mainslide);
      
       //reset button 
            JButton reset = new JButton("<html><font size='25'>Reset</font></html>");
            reset.addActionListener(mainslide);//sets reset buttons to 
                                              //mainslide listener
            mainslide.resetbutton=reset;
            reset.setToolTipText("<html><font size='15'>Reset the sample</font></html>");
            reset.setActionCommand("reset");
            reset.setPreferredSize(new Dimension(100,100));
            
       //the save button
            JButton save = new JButton("save color");
            save.addActionListener(mainslide);
            save.setActionCommand("save");
            save.setToolTipText("Save the current color here");
            mainslide.savebutton=save;
            
   /*************************creating a mother panel**************************/
   /*****************and setting it to the main JFrame *****************/
            
        Container mother = jf.getContentPane();
        
  /*************************************************************************/    
  //*****************graphical bar graphs**********************************//
        
        JPanel bars = new JPanel();

            bars.setSize(700,400);
            bars.setLayout(new GridLayout(3,0));
            Container blank=new JPanel();
            Container blank2=new JPanel();
            Container blank3=new JPanel();
            blank.setPreferredSize(new Dimension(150,10));
            blank2.setPreferredSize(new Dimension(150,10));
            blank3.setPreferredSize(new Dimension(150,10));
            
            int bordersize=3;
            int spacing=10;
            /************the red bar*************/
          
            JPanel rbp = new JPanel();
            rbp.setLayout(new BorderLayout());
            rbp.add(mainslide.redbar, BorderLayout.CENTER);
            mainslide.redbar.setcolor(Color.red);
            Container rbp2= new JPanel();
            rbp2.setLayout(new BorderLayout());
            rbp2.add(blank, BorderLayout.WEST);
            rbp2.add(mainslide.redlabel, BorderLayout.CENTER);
            mainslide.redlabel.setPreferredSize(new Dimension(50,50));

            rbp.add(rbp2, BorderLayout.SOUTH);
            rbp.setBorder(BorderFactory.createLineBorder(Color.RED, bordersize));

            JPanel rbp3 = new JPanel();
            rbp3.setLayout(new BorderLayout());
            rbp3.setBorder(BorderFactory.createEmptyBorder(spacing, spacing, spacing, spacing));
            rbp3.add(rbp, BorderLayout.CENTER);
            bars.add(rbp3);


         /************the blue bar*************/   


            JPanel bb = new JPanel();
            bb.setLayout(new BorderLayout());
            bb.add(mainslide.bluebar, BorderLayout.CENTER);

            Container bb2  = new JPanel();
            bb2.setLayout(new BorderLayout());
            bb2.add(blank2, BorderLayout.WEST);
            bb2.add(mainslide.bluelabel, BorderLayout.CENTER);
            mainslide.bluebar.setcolor(Color.blue);
            mainslide.bluelabel.setPreferredSize(new Dimension(50,50));
            bb.add(bb2, BorderLayout.SOUTH);
            bb.setBorder(BorderFactory.createLineBorder(Color.BLUE, bordersize));

            JPanel bb3 = new JPanel();
            bb3.setLayout(new BorderLayout());
            bb3.setBorder(BorderFactory.createEmptyBorder(spacing, spacing, spacing, spacing));
            bb3.add(bb, BorderLayout.CENTER);
            bars.add(bb3);

         /************the green bar*************/

            JPanel gb = new JPanel();
            gb.setLayout(new BorderLayout());
            gb.add(mainslide.greenbar, BorderLayout.CENTER);
            mainslide.greenbar.setcolor(Color.green);
            mainslide.greenlabel.setPreferredSize(new Dimension(50,50));
            Container gb2 = new JPanel();
            gb2.setLayout(new BorderLayout());
            gb2.add(blank3, BorderLayout.WEST);
            gb2.add(mainslide.greenlabel, BorderLayout.CENTER);
            gb.add(gb2, BorderLayout.SOUTH);
            gb.setBorder(BorderFactory.createLineBorder(Color.GREEN, bordersize));


            JPanel gb3 = new JPanel();
            gb3.setLayout(new BorderLayout());
            gb3.setBorder(BorderFactory.createEmptyBorder(spacing, spacing, spacing, spacing));
            gb3.add(gb, BorderLayout.CENTER);
            bars.add(gb3);

            bars.setBorder(BorderFactory.createEtchedBorder());

/*************************  color sample window  *************************/

            JTextField samp = new JTextField();
            samp.setPreferredSize(new Dimension(50,20));
            JPanel window = new JPanel();
            window.setPreferredSize(new Dimension(300,700));
                
           window.setLayout(new BorderLayout());
           
           JPanel windowsamp = new JPanel();
           windowsamp.setLayout(new BorderLayout());
           windowsamp.add(mainslide.samplerlabel, BorderLayout.CENTER);
           windowsamp.setBorder(BorderFactory.createEtchedBorder());
           mainslide.samplerlabel.setHorizontalAlignment(SwingConstants.CENTER);
           
           window.add(windowsamp, BorderLayout.NORTH);
            mainslide.samplerlabel.setPreferredSize(new Dimension(200,50));
           window.add(mainslide.mco, BorderLayout.CENTER);
           
           JPanel buttons = new JPanel();
           buttons.setLayout(new GridLayout(0,2));
           
           buttons.add(reset);
           buttons.add(mainslide.samplesave);
           mainslide.samplesave.add(save);

           window.add(buttons, BorderLayout.SOUTH);  
           window.setBorder(BorderFactory.createEtchedBorder());
           
     
   /************************************************************************/         
   /*************************** slider panel ********************************/
        JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(3,0));
                panel.add(red);
                panel.add(blue);
                panel.add(green);
        
       JPanel padding = new JPanel();
       padding.setPreferredSize(new Dimension(600,50));
     /************************************************************************/ 
     /*********now we add all our panels to the mother panel********/
  
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
   
        //wrapper for my containers
           JPanel windowwrapper = new JPanel();
           windowwrapper.setLayout(new BorderLayout());
           windowwrapper.add(window, BorderLayout.CENTER);
           windowwrapper.setBorder(BorderFactory.createEmptyBorder(spacing, spacing, spacing, spacing));
      
           JPanel radiowrapper = new JPanel();
           radiowrapper.setLayout(new BorderLayout());
           radiowrapper.add(radio, BorderLayout.CENTER);
           radiowrapper.setBorder(BorderFactory.createEmptyBorder(spacing, spacing, spacing, spacing));
           radio.setBorder(BorderFactory.createEtchedBorder());
           
        mother.add(radiowrapper, BorderLayout.NORTH);
        mother.add(bars, BorderLayout.CENTER);
        mother.add(panel, BorderLayout.WEST);
        mother.add(windowwrapper, BorderLayout.EAST);
        mother.add(padding, BorderLayout.SOUTH);
         jf.setVisible(true);
                
   }
   

    
    
}
