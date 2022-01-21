package client;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Message implements Serializable {

    private String name;
    private String nachricht;
    public static ArrayList<String> aktiveNutzer = new ArrayList<String>();

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
    
    public void nowOnline(String username) {
    	aktiveNutzer.add(username);
    }

	public void nowOffline(String username) {
		aktiveNutzer.remove(username);
	}

}