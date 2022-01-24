
package server;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;

import client.ChatController;
import client.Client;
import client.Message;
import client.User;
import javafx.application.Platform;

public class ServerThread implements Runnable {
    private Socket socket;
    //private boolean isAlive;
    private final LinkedList<String> messagesToSend;
    public  boolean hasMessages = false;
    private final LinkedList<String> messagesReceived;
    private boolean getMessages = false;
	private Client client;

    public ServerThread(Socket socket, Client client){
        this.socket = socket;
        this.client = client;
        messagesToSend = new LinkedList<String>();
        messagesReceived = new LinkedList<String>();
    }

    public void addNextMessage(String message){
        synchronized (messagesToSend){
            hasMessages = true;
            messagesToSend.push(message);
        }
    }
    
    public int numberOfMessagesReceived() {
    	return messagesReceived.size();
    }
    
    public String getNextMessage(){
        synchronized (messagesReceived){
            if(numberOfMessagesReceived() > 0) {
            	return messagesReceived.pop();
            }
        }
        return null;
    }

    @Override
    public void run(){
        System.out.println("Willkommen :" + User.getName());

        System.out.println("Local Port :" + socket.getLocalPort());
        System.out.println("Server = " + socket.getRemoteSocketAddress() + ":" + socket.getPort());

        try{
            PrintWriter serverOut = new PrintWriter(socket.getOutputStream(), false);
            BufferedReader serverInStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while(!socket.isClosed()){
                if(serverInStream.ready()) { 
                	synchronized(messagesReceived) {
                		String s = serverInStream.readLine();
                		messagesReceived.push(s);
                		Message.addNachricht(s);
                		Platform.runLater(new Runnable() {
							@Override
							public void run() {
								client.controller.nachrichtenAnzeigen();
							}
						});
                		String ben = s.split(":")[0].trim();
                		if (Message.nowOnline(ben)) {
                    		Platform.runLater(new Runnable() {
    							@Override
    							public void run() {
    								client.controller.nutzerAnzeigen();
    							}
    						});
                			
                		}
                	    System.out.println(s);
                	}
                	
              
                 
                }
                if(hasMessages){
                    String nextSend = "";
                    synchronized(messagesToSend){
                        nextSend = messagesToSend.pop();
//                        Message.addNachricht(nextSend);
//                   		Platform.runLater(new Runnable() {
//    							@Override
//    							public void run() {
//    								// TODO Auto-generated method stub
//    								client.controller.nachrichtenAnzeigen();
//    							}
//    						});
                        hasMessages = !messagesToSend.isEmpty();
                    }
                    serverOut.println(User.getName() + " : " + nextSend);
                    serverOut.flush();
                }
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        

    }
}
