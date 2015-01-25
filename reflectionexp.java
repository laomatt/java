
import java.lang.reflect.*;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.*;
//import static mylib.println;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mattlao
 */
public class reflectionexp extends mylib{
    
    public static void main(String[] args) throws NullPointerException, ClassNotFoundException{   
        String name = "String";
        ClassLoader classLoader = reflectionexp.class.getClassLoader();
        
        Class aClass = classLoader.loadClass("java.lang."+name);
        
        
                    Method[] methods = aClass.getMethods();
                    Constructor conts[] = Integer.class.getConstructors();
                    
                    
                    println("aCLass.getName() = "+ aClass.getName());
     
        
        for(Method method : methods){
        println(" method =  "+method.getName());}
//        
//         
//        
//        for(Constructor gt: conts)
//            {
//                println("CON ==="+gt);
//            }
//        
          
//           Class x = "String".getClass();
//            Class c = Class.forName("String", true, x.getClass().getClassLoader());
            //Constructor conts[] = "Integer".class.getConstructors();
            
          
        //  Class a =classLoader.loadClass("java.lang.Integer");
//          Class c = Class.forName("String", true, x.getClass().getClassLoader());
//          Constructor conts[] = Integer.class.getConstructors();
//          
//            for(Constructor gt: conts)
//            {
//                println("CON ===="+gt);
//            }
       
    }
    
    
    
    
}
