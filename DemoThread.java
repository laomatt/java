
class PrintChar extends mylib implements Runnable
{
    private char ch;
    private int n;
    
    public PrintChar(char c, int t)
    {
        ch=c; n=t;
    }
    
    @Override
    public void run()
    {
        for(int i=0; i<n; i++) print(ch);
    }
    
}

class PrintNum extends mylib implements Runnable
{
    private int lastNum;
    public PrintNum(int n){lastNum=n;}
    
    @Override
    public void run()
    {
        for(int i=0; i<lastNum;i++)print("+i");
    }

}

public class DemoThread {
    
    public static void main(String args[])
    {
        Thread t1 = new Thread(new PrintChar('a',100));
        Thread t2 = new Thread(new PrintChar('b',100));
        Thread t3 = new Thread(new PrintNum(100));
        
        
        
        
        t1.start();
        t2.start();
        t3.start();
    }
    
}
