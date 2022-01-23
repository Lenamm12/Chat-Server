package client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import server.FileManager;

public class ChatController implements Initializable{
	
	
	@FXML
	private TextField neue_Nachricht;
	private Client client;
	
	@FXML
	private ListView<String> kontakteBox;
	@FXML
	private ListView<String> chatBox;
	
	protected static ListProperty<String> lpUser = new SimpleListProperty<>();
	protected static ListProperty<String> lpNachrichten = new SimpleListProperty<>();
	
	//Methode die vom Button Senden benutzt wird
	@FXML
	private void nachricht_senden() throws Exception {
		
		//Nachricht über Server an andere Nutzer senden
		
		String nachricht = neue_Nachricht.getText();
		if (!neue_Nachricht.getText().isEmpty()) {
			 Client.serverThread.addNextMessage(nachricht);
			 neue_Nachricht.clear();
	        }
		while (Client.serverThread.hasMessages ) {
			Thread.sleep(100);
		}
	}
	
	
	public void setClient(Client client){
		this.client = client;
		
	}
	
	
	public void nutzerAnzeigen() {
		ArrayList<String> users =  Message.getAktiveNutzer();
		lpUser.set(FXCollections.observableArrayList(users));
		kontakteBox.itemsProperty().bind(lpUser);
		
		//ObservableList<String> users = FXCollections.observableList(Message.getAktiveNutzer());
		//kontakteBox.setItems(users);
	}

	public void nachrichtenAnzeigen() {
		ArrayList<String> nachrichten =  Message.getNachrichten();
		lpNachrichten.set(FXCollections.observableArrayList(nachrichten));
		chatBox.itemsProperty().bind(lpNachrichten);
		
	} 

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nutzerAnzeigen();
		nachrichtenAnzeigen();
	    
		//Nachrichten werden mit Enter abgesendet
        neue_Nachricht.addEventFilter(KeyEvent.KEY_PRESSED, ke -> {
            if (ke.getCode().equals(KeyCode.ENTER)) {
                try {
					nachricht_senden();
				} catch (Exception e) {
					e.printStackTrace();
				}
                ke.consume();
            }
        });
		
	}
	

      //  if (nachricht.getName().equals(User.getName())) {
      


}
