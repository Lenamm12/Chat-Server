package client;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Message implements Serializable {

    private String name;
    private String nachricht;
    public static ArrayList<String> aktiveNutzer = new ArrayList<String>();
    public static ArrayList<String> nachrichten = new ArrayList<String>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNachricht() {

        return nachricht;
    }

    public void setNachricht(String nachricht) {
        this.nachricht = nachricht;
    }

    public static ArrayList<String> getAktiveNutzer() {
        return aktiveNutzer;
    }
    
    public static boolean nowOnline(String username) {
    	if (aktiveNutzer.contains(username))
    		return false;
    	aktiveNutzer.add(username);
    	return true;
    }

	public void nowOffline(String username) {
		aktiveNutzer.remove(username);
	}

	 public static ArrayList<String> getNachrichten() {
	        return nachrichten;
	    }
	    
	    public static void addNachricht(String nachricht) {
	    	nachrichten.add(nachricht);
	    }

}