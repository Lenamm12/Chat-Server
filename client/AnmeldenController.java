package client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.sun.javafx.property.adapter.PropertyDescriptor.Listener;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import server.FileManager;

public class AnmeldenController {


    
	@FXML
	private TextField name;
	@FXML
	private TextField passwort;
	
	private Scene scene;
	 public static ChatController con;

    private static AnmeldenController instance;
    
    static String username;
    static String userpasswort;

    public AnmeldenController() {
        instance = this;
    }

    public static AnmeldenController getInstance() {
        return instance;
    }
    @FXML
    public void login() throws IOException {
        username = name.getText();
        userpasswort = passwort.getText();
        
  //   if(FileManager.loginCheck(username, userpasswort) == true) {
        

        FXMLLoader fmxlLoader = new FXMLLoader(getClass().getResource("Chat.fxml"));
        Parent window =  fmxlLoader.load();
     //  con = fmxlLoader.<ChatController>getController();
      //  Listener listener = new Listener( username, con);
      //  Thread x = new Thread(listener);
      //  x.start();
   
       
       Thread chatfenster = new Thread(new Chat());
       chatfenster.start();
        this.scene = new Scene(window);
        
     //  }
     
    }

    public void showScene() throws IOException {
        Platform.runLater(() -> {
            Stage stage = (Stage) name.getScene().getWindow();
            stage.setResizable(true);

            stage.setOnCloseRequest((WindowEvent e) -> {
                Platform.exit();
                System.exit(0);
            });
            stage.setScene(this.scene);
            stage.centerOnScreen();
        });
    }
    
    public static String getUsername () {
    	return username;
    }

	public static String getPasswort () {
		return userpasswort;
	}

}
