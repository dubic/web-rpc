/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.google.gson.Gson;

/**
 *
 * @author DUBIC
 */
public class Test {

    public void testService(DTO dto){
        System.out.println(new Gson().toJson(dto));
    }
    
    public void getValue(){
        
    }
    public void testMethod(String p1,int n){
        System.out.println("name - "+p1+" age - "+n);
    }
    public static void main(String[] dlkd){
       
    }
}
