
package server;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Scanner;

import client.Message;

public class ServerThread implements Runnable {
    private Socket socket;
    private String userName;
    //private boolean isAlive;
    private final LinkedList<String> messagesToSend;
    public  boolean hasMessages = false;
    private final LinkedList<String> messagesReceived;
    private boolean getMessages = false;

    public ServerThread(Socket socket, String userName){
        this.socket = socket;
        this.userName = userName;
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
        System.out.println("Willkommen :" + userName);

        System.out.println("Local Port :" + socket.getLocalPort());
        System.out.println("Server = " + socket.getRemoteSocketAddress() + ":" + socket.getPort());

        try{
            PrintWriter serverOut = new PrintWriter(socket.getOutputStream(), false);
            BufferedReader serverInStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while(!socket.isClosed()){
                if(serverInStream.ready()) { 
                	String s = serverInStream.readLine();
                	messagesReceived.push(s);
                	Message.addNachricht(s);
                	
                   System.out.println(s);
                 
                }
                if(hasMessages){
                    String nextSend = "";
                    synchronized(messagesToSend){
                        nextSend = messagesToSend.pop();
                        Message.addNachricht(nextSend);
                        hasMessages = !messagesToSend.isEmpty();
                    }
                    serverOut.println(userName + " : " + nextSend);
                    serverOut.flush();
                }
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        

    }
}
