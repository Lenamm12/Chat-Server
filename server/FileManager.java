package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import client.Message;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class FileManager {
	static String nutzerdb = "server/Nutzer.txt";
	
	static Alert alert;
	
	static Scanner in;
	
	
	//Nutzer Überprüfung beim Login
	public static boolean loginCheck(String username, String password ) throws IOException {
	try {
		File fp = new File(nutzerdb);
		
	     in = new Scanner(fp);
	     
	     while (in.hasNextLine())
         {
	    	 System.out.println("Test");
	    	 
           String s = in.nextLine();  
           String[] sArray = s.split(",");
           
           System.out.println(sArray[0]); 
           System.out.println(sArray[1]);

           
           if (username.equals( sArray[0]) && password.equals(sArray[1]))
           {
             alert = new Alert(AlertType.CONFIRMATION);
             alert.setContentText("Login erfolgreich");
             alert.show();
             
             Message.nowOnline(username);
            		 
             	return true;
           }
           
           else if (username == sArray[0] && password != sArray[1])
           {
        	  alert = new Alert(AlertType.WARNING);
        	  alert.setContentText("Falsches Passwort");
        	  
        	  return false;
           }
           else
           {
            continue;
           }
         }
	     in.close();
	     
	     alert = new Alert(AlertType.WARNING);
	     alert.setContentText("Nutzer nicht vorhanden, bitte registrieren");
	     
           			return false;
         
         
       
	}
	catch (FileNotFoundException e) {
		alert = new Alert(AlertType.ERROR);
		alert.setContentText("Nutzerdatenbank nicht gefunden");
    
    }
	return false;
		
	}
	
	///Nutzer Überprüfung registrieren + Schreiben in die Datei
	public static void registrierenCheck(String username, String passwort) throws IOException {
		File fp = new File(nutzerdb);
		
		Scanner in = new Scanner(fp);
		
		boolean exists = false; 
	     
	     while (in.hasNextLine())
         {
           String s = in.nextLine();  
           String[] sArray = s.split(",");
           
           System.out.println(sArray[0]); 
           System.out.println(sArray[1]);

           
           if (username == sArray[0])
           {
             alert = new Alert(AlertType.WARNING);
             alert.setContentText("Nutzer existiert bereits");
             alert.show();
             
             exists = true;
            		 
           }
         }
		
		if(exists == false) {
			FileWriter fw = new FileWriter(fp, true);
		    BufferedWriter bw = new BufferedWriter(fw);
		    bw.newLine();
		    bw.write(username+","+passwort);
		    bw.close();
		    
		    Message.nowOnline(username);
		    
		    alert = new Alert(AlertType.CONFIRMATION);
            alert.setContentText("Nutzer wurde registriert. Beim nächsten Start mit Login möglich");
            alert.show();
		}
		
		
	}
	

	

	
	
}
