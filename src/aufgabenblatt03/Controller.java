/*
 * Praktikum Programmiermethodik 2 - WS 2016
 * Gruppe:  Philip Scheer (Philip.Scheeer@haw-hamburg.de),
 *          Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 28.11.2016 
 * Aufgabe: Praktikum X
 */

package aufgabenblatt03;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Moritz Höwer, Philip Scheer
 * @version 1.0 - 28.11.2016
 */
public class Controller implements Initializable {

    @FXML
    private HBox root;
    
    @FXML
    private Label lblStation;
    
    @FXML
    private Label lblQueue;
    
    @FXML
    private VBox vbxStation;
    
    @FXML
    private VBox vbxQueue;
    
    @FXML
    private VBox vbxPlatformsStation;
    
    @FXML
    private VBox vbxPlatformsQueue;
    
    /* (non-Javadoc)
     * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // Bind sizes of platform containers to fit window size
        vbxPlatformsStation.prefHeightProperty().bind(vbxStation.heightProperty().subtract(lblStation.getHeight()));
        vbxPlatformsQueue.prefHeightProperty().bind(vbxQueue.heightProperty().subtract(lblQueue.getHeight()));
        vbxPlatformsQueue.minWidthProperty().bind(root.widthProperty().subtract(vbxStation.getPrefWidth()));
    }

}
