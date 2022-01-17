
package client;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import javafx.application.Application;
import server.ServerThread;

public class Client {

    public static final String host = "10.0.3.36";
    public static final int portNumber = 4711;

    private String userName;
    private String serverHost;
    private int serverPort;


    public static void main(String[] args){
    	Anmelden.main(args); //Fenster öffnen
    	
    	
        String readName = null;
        Scanner scan = new Scanner(System.in);
        /*
        System.out.println("Nutzername eingeben:");
        while(readName == null || readName.trim().equals("")){

            readName = scan.nextLine();
            if(readName.trim().equals("")){
                System.out.println("Bitte erneut versuchen:");
            }
        }
        */
        
        String username = AnmeldenController.getUsername();

        Client client = new Client(username, host, portNumber);
        client.startClient(scan);
    }

    private Client(String userName, String host, int portNumber){
        this.userName = userName;
        this.serverHost = host;
        this.serverPort = portNumber;
    }

    private void startClient(Scanner scan){
        try{
            Socket socket = new Socket(serverHost, serverPort);
            Thread.sleep(1000); 

            ServerThread serverThread = new ServerThread(socket, userName);
            Thread serverAccessThread = new Thread(serverThread);
            serverAccessThread.start();
            while(serverAccessThread.isAlive()){
                if(scan.hasNextLine()){
                    serverThread.addNextMessage(scan.nextLine());
                }

            }
        }catch(IOException ex){
            System.err.println("Verbindungsfehler!");
            ex.printStackTrace();
        }catch(InterruptedException ex){
            System.out.println("Unterbrochen");
        }
    }
}
