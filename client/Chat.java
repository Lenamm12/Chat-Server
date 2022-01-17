package client;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Chat extends Application implements Runnable{
	
	private Scene scene;
	
	

	 @Override
	    public void start(Stage stage) throws Exception {

	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(getClass().getResource("Chat.fxml"));
	        Parent root = loader.load();
	      /*  con = fxmlLoader.<ChatController>.getController();
	        Listener listener = new Listener(name, con);
	        Thread x = new Thread(listener);
	        x.start(); */
	        
	        this.scene = new Scene(root);
	        
	        //stage.setScene(scene);
	        //stage.show();
	    }

	public static void main() {
		launch();
		
	}
	


	@Override
	public void run() {
		 try {
			AnmeldenController.getInstance().showScene();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
