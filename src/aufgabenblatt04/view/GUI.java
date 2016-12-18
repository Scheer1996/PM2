/*
 * Praktikum Programmiermethodik 2 - WS 2016
 * Gruppe:  Philip Scheer (Philip.Scheeer@haw-hamburg.de),
 *          Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 18.12.2016 
 * Aufgabe: Praktikum 4
 */

package aufgabenblatt04.view;

import java.io.IOException;

import aufgabenblatt04.BVAnwendung;
import aufgabenblatt04.braitenbergvehikel.BVSimulation;
import aufgabenblatt04.braitenbergvehikel.BraitenbergVehikel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import aufgabenblatt04.view.BVCanvas;

/**
 * GUI class (JavaFX Controller) for displaying the simulation.
 *
 * @author Moritz Höwer, Philip Scheer
 * @version 1.0 - 18.12.2016
 */
public class GUI {

    /**
     * main layout
     */
    @FXML
    private BorderPane borderPane;

    /**
     * container for configurations of the various {@link BraitenbergVehikel}
     */
    @FXML
    private VBox vehikelSettingsVbox;

    /**
     * used for controlling continuous simulation
     */
    @FXML
    private CheckBox simulateCheckBox;

    /**
     * the stage used by this window
     */
    private Stage stage;

    /**
     * reference to the controller (MVC Controller)
     */
    private BVAnwendung controller;

    public GUI(BVAnwendung controller) {
        this.controller = controller;
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * shows the window and waits for it to be closed
     */
    public void showAndWait() {
        stage.showAndWait();
    }

    /**
     * Initializes the contents using FXML
     * 
     * @throws IOException
     *             if the FXMLLoader fails
     */
    private void init() throws IOException {
        // Load FXML
        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("GUI.fxml"));
        fxmlLoader.setController(this);

        Parent root = fxmlLoader.load(); // might throw IOException

        // Canvas setzen
        BVSimulation sim = controller.getSimulation();

        BVCanvas canvas = new BVCanvas(400, 600, sim);
        canvas.zeichneSimulation();
        borderPane.setCenter(canvas);

        // add vehikel configurators
        for (int i = 0; i < sim.getAnzahlVehike(); i++) {
            vehikelSettingsVbox.getChildren()
                    .add(new VehikelConfiguration(sim.getVehikel(i)));
        }
        vehikelSettingsVbox.setPrefWidth(180);

        stage = new Stage();
        Scene scene = new Scene(root, 750, 430);

        stage.setTitle("Braitenberg-Vehikel!");
        stage.setScene(scene);
        stage.setMinHeight(469);
        stage.setMinWidth(766);
        stage.centerOnScreen();

        canvas.heightProperty().bind(scene.heightProperty());
        canvas.widthProperty().bind(scene.widthProperty().subtract(300));
    }

    /**
     * ActionListener for the button
     * 
     * @param e
     *            the Event
     */
    @FXML
    private void startSimulation(ActionEvent e) {
        controller.getSimulation().simulationsSchritt();
    }

    /**
     * ActionListener for the checkbox
     * 
     * @param e
     *            the Event
     */
    @FXML
    private void simuliereAction(ActionEvent e) {
        if (simulateCheckBox.isSelected()) {
            controller.startBGThread();
        } else {
            controller.stopBGThread();
        }
    }

}
