package client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
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
	private ListView chatBox;
	@FXML
	private TextField neue_Nachricht;
	
	@FXML
	static ListView<String> kontakteBox;
	
	protected static ListProperty<String> lp = new SimpleListProperty<>();
	
	@FXML
	private void nachricht_senden() throws Exception {
		
		//Nachricht im Fenster anzeigen
		String nachricht = neue_Nachricht.getText();
		Client.serverThread.addNextMessage(nachricht);
		//Nachricht über Server an anderen Nutzer senden
		 if (!neue_Nachricht.getText().isEmpty()) {
	            Listener.send(nachricht);
	            neue_Nachricht.clear();
	        }
		
	}
	
	public static void nutzerAnzeigen() {
		System.out.println("Nutzer anzeigen funkt");
		
		ArrayList<String> list =  Message.getAktiveNutzer();
		lp.set(FXCollections.observableArrayList(list));
		
		kontakteBox.itemsProperty().bind(lp);
		System.out.println("unter Box");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	     /* Added to prevent the enter from adding a new line to inputnachrichtBox */
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


	public void logoutScene() {
		Platform.runLater(() -> {
            FXMLLoader fmxlLoader = new FXMLLoader(getClass().getResource("Anmelden.fxml"));
            Parent window = null;
            try {
                window = (Pane) fmxlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = AnmeldenController.getPrimaryStage();
            Scene scene = new Scene(window);
            stage.setMaxWidth(350);
            stage.setMaxHeight(420);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.centerOnScreen();
        });
		
	}

	public void addToChat(Message nachricht) {
	    Task<HBox> andereNachrichten = new Task<HBox>() {
            @Override
            public HBox call() throws Exception {

                Label nachrichtenLabel = new Label();
        
                    nachrichtenLabel.setText(nachricht.getName() + ": " + nachricht.getNachricht());
                
                nachrichtenLabel.setBackground(new Background(new BackgroundFill(Color.WHITE,null, null)));
                HBox x = new HBox();
                x.getChildren().addAll( nachrichtenLabel);
                //logger.debug("ONLINE USERS: " + Integer.toString(Message.getAktiveNutzer().size()));
                return x;
            }
        };

        andereNachrichten.setOnSucceeded(event -> {
            chatBox.getItems().add(andereNachrichten.getValue());
        });

        Task<HBox> eigeneNachrichten = new Task<HBox>() {
            @Override
            public HBox call() throws Exception {
                Label nachrichtenLabel = new Label();
              
                    nachrichtenLabel.setText(nachricht.getNachricht());
                
                nachrichtenLabel.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN,
                        null, null)));
                HBox x = new HBox();
                x.setMaxWidth(chatBox.getWidth() - 20);
                x.setAlignment(Pos.TOP_RIGHT);
                x.getChildren().addAll(nachrichtenLabel);

                return x;
            }
        };
        eigeneNachrichten.setOnSucceeded(event -> chatBox.getItems().add(eigeneNachrichten.getValue()));

        if (nachricht.getName().equals(User.getName())) {
            Thread t2 = new Thread(eigeneNachrichten);
            t2.setDaemon(true);
            t2.start();
        } else {
            Thread t = new Thread(andereNachrichten);
            t.setDaemon(true);
            t.start();
        }
		
	}

}
