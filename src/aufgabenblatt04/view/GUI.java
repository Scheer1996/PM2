package aufgabenblatt04.view;

import java.io.IOException;

import aufgabenblatt04.BVAnwendung;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import aufgabenblatt04.braitenbergvehikel.BVBewegungAbstossung;
import aufgabenblatt04.braitenbergvehikel.BVBewegungAttraktion;
import aufgabenblatt04.braitenbergvehikel.BVSimulation;
import aufgabenblatt04.braitenbergvehikel.BraitenbergVehikel;
import aufgabenblatt04.braitenbergvehikel.Vektor2;
import aufgabenblatt04.view.BVCanvas;
public class GUI {

	
	@FXML
	private BorderPane borderPane;
	
	@FXML
	private VBox vehikelSettingsVbox;
	
	@FXML
	private CheckBox simulateCheckBox;
	
	private Stage stage;
	
	private BVAnwendung controller;
	
    public GUI(BVAnwendung controller) {
    	this.controller = controller;
        try {
            init();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void showAndWait() {

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
    
    
    private void init() throws IOException {
        // Load FXML
        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("GUI.fxml"));
        fxmlLoader.setController(this);

        Parent root = fxmlLoader.load(); // might throw IOException

        // Canvas setzen
        BVCanvas canvas = new BVCanvas(400, 600, controller.getSimulation());
        canvas.zeichneSimulation();
        
        borderPane.setCenter(canvas);
        stage = new Stage();
        Scene scene = new Scene(root, 600, 430);
        stage.setTitle("Braitenberg-Vehikel!");
        stage.setScene(scene);
        stage.centerOnScreen();
    }
    
    
    @FXML 
    private void startSimulation(ActionEvent e) {
    	System.out.println("Simulationsschritt ausführen");
    }
    
    @FXML
    private void simuliereAction(ActionEvent e) {
    	if(simulateCheckBox.isSelected()) {
        	System.out.println("Checkbox angehakt jetzt dauerhaft Simulieren");
    	} else {
        	System.out.println("Thread Stoppen");
    	}
    }
    
    
}
