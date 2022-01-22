package client;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Chat extends Application {
	
	private Scene scene;
	
	  public static final String host = "10.0.3.36";
	    public static final int portNumber = 4711;
	  


	 @Override
	    public void start(Stage stage) throws Exception {

		 try {
			 String username = AnmeldenController.getUsername();
		     System.out.println(username);
		     
		     FXMLLoader fmxlLoader = new FXMLLoader(getClass().getResource("Chat.fxml"));
			 ChatController con =  fmxlLoader.<ChatController>getController();
        	 Client client = new Client(username, host, portNumber,con);
        	 client.startClient();
        	 
       
			AnmeldenController.getInstance().showScene();
			ChatController.nutzerAnzeigen();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
	    }

	public static void main() {
		launch();
		
	}
	
}
