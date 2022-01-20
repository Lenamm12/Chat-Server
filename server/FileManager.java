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

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class FileManager {
	static String nutzerdb = "Nutzer.txt";
	
	static Alert alert;
	
	static ArrayList<String> aktiveNutzer = new ArrayList<String>();
	
	//Nutzer Überprüfung beim Login
	public static boolean loginCheck(String username, String password ) throws IOException {
	try {
		File fp = new File(nutzerdb);
		
	     Scanner in = new Scanner(fp);
	     
	     while (in.hasNextLine())
         {
	    	 System.out.println("Test");
	    	 
           String s = in.nextLine();  
           String[] sArray = s.split(",");
           
           System.out.println(sArray[0]); //Just to verify that file is being read
           System.out.println(sArray[1]);

           
           if (username.equals( sArray[0]) && password.equals(sArray[1]))
           {
             alert = new Alert(AlertType.CONFIRMATION);
             alert.setContentText("Login erfolgreich");
             alert.show();
             
             aktiveNutzer.add(username);
            		 
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
		//Alert
		alert = new Alert(AlertType.ERROR);
		alert.setContentText("Nutzerdatenbank nicht gefunden");
    
    }
	return false;
		
	}
	
	///Nutzer Überprüfung registrieren + Schreiben in die Datei
	public void registrierenCheck(String username, String passwort) throws IOException {
		File fp = new File(nutzerdb);
		
		Scanner in = new Scanner(fp);
		
		boolean exists = false; 
	     
	     while (in.hasNextLine())
         {
           String s = in.nextLine();  
           String[] sArray = s.split(",");
           
           System.out.println(sArray[0]); //Just to verify that file is being read
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
		    bw.write(username+","+passwort);
		    bw.newLine();
		    bw.close();
		    
		    aktiveNutzer.add(username);
		}
		
		
	}
	
	//online-liste herausgeben
	public static ArrayList<String> getAktiveNutzer()
	{
		return aktiveNutzer;
	}

	
	//aus online-liste entfernen
	public void offline(String username) {
		aktiveNutzer.remove(username);
	}
	
	
}
