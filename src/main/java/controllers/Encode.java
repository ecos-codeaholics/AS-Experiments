/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ManagerCompufi
 */
public class Encode {
  public static String[] encodeGenerator(String password){
       String result [] = new String[2];
        String salt = String.valueOf(UUID.randomUUID());
        System.out.println("salt: "+salt);
        result[0] = salt;
        
        String saltAndPassword = salt+password;
        System.out.println("salt&Pass: "+saltAndPassword);
        /*realiza encode del password con el salt*/
        
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(saltAndPassword.getBytes(),0,saltAndPassword.length());
            password = new BigInteger(1,md.digest()).toString(16);
            System.out.println("MD5: "+password);
            result[1] = password;            
            
          } catch (NoSuchAlgorithmException ex) { 
      Logger.getLogger(Encode.class.getName()).log(Level.SEVERE, null, ex);
    }
                
       return result;
    }
  
  public static String encodeGeneratorWithSalt(String password, String salt){
       String result = new String();
       System.out.println("salt: "+salt);
       
        
        String saltAndPassword = salt+password;
        System.out.println("salt&Pass: "+saltAndPassword);
        /*realiza encode del password con el salt*/
        
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(saltAndPassword.getBytes(),0,saltAndPassword.length());
            password = new BigInteger(1,md.digest()).toString(16);
            System.out.println("MD5: "+password);
            result = password;            
            
          } catch (NoSuchAlgorithmException ex) { 
      Logger.getLogger(Encode.class.getName()).log(Level.SEVERE, null, ex);
    }
                
       return result;
    }
}
