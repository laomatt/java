
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
public class genpw {


    public static void main(String[] args){
    String alpha="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    Random r = new Random();
    String pw="";
    int i=0;
    while (i<25)
    {
    int j=r.nextInt(alpha.length());
    pw+=alpha.charAt(j);
    i++;

    }

    File f = new File("wifipws");



    System.out.println(pw);


    }

}
