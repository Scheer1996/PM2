/*
 * Praktikum Programmiermethodik 2 - WS 2016
 * Gruppe:  Philip Scheer (Philip.Scheeer@haw-hamburg.de),
 *          Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 28.11.2016 
 * Aufgabe: Praktikum 3
 */

package aufgabenblatt03;

import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author Moritz Höwer, Philip Scheer
 * @version 1.0 - 28.11.2016
 */
public class GUI implements Observer {

    private static final int SOME_WEIRD_OVERHEAD = 35;
    private static final int MARGIN_BETWEEN = 20;
    
    private HBox root;
    private Label lblStation;
    private Label lblQueue;
    private VBox vbxStation;
    private VBox vbxQueue;
    private VBox vbxPlatformsStation;
    private VBox vbxPlatformsQueue;
    private int numberOfPlatforms;

    private Stage primaryStage;
    private Simulation sim;

    public GUI(Stage primaryStage) {
        this.primaryStage = primaryStage;

        if (getNumberOfPlatforms()) {
            initComponents();
            sim = new Simulation(numberOfPlatforms);
            sim.getStation().addObserver(this);
            Thread simThread = new Thread(sim);
            simThread.setDaemon(true);
            simThread.start();
        }
    }

    private boolean getNumberOfPlatforms() {
        TextInputDialog tid = new TextInputDialog("10");
        tid.setTitle("Platform Count");
        tid.setHeaderText("Please enter how many Platforms you whish to have:");
        tid.setContentText("I want the station to have ");

        Optional<String> result = tid.showAndWait();
        if (!result.isPresent()) {
            return false;
        }
        try {
            numberOfPlatforms = Integer.parseInt(result.get());
            if (numberOfPlatforms < 1) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("That was not a Number you fool!");
            alert.setContentText("Please enter a positive integer number.");
            alert.setResult(ButtonType.OK);
            alert.showAndWait();
            return false;
        }
        return true;
    }

    private void initComponents() {
        // Construct
        lblStation = new Label("Station");
        lblQueue = new Label("Queue");
        vbxPlatformsStation = new VBox();
        vbxPlatformsQueue = new VBox();
        vbxStation = new VBox(lblStation, vbxPlatformsStation);
        vbxQueue = new VBox(lblQueue, vbxPlatformsQueue);
        root = new HBox(vbxStation, vbxQueue);

        // Properties
        lblStation.setFont(Font.font(18));
        lblQueue.setFont(Font.font(18));
        vbxStation.setPrefWidth(200);
        HBox.setMargin(vbxQueue, new Insets(0, 0, 0, MARGIN_BETWEEN));

        // TODO: Remove debug colors
        // DEBUG
        vbxPlatformsStation.setStyle("-fx-background-color: red;");
        vbxPlatformsQueue.setStyle("-fx-background-color: green;");
        // DEBUG

        // create scene
        Scene scene = new Scene(root, 1000, 600);
        primaryStage.setTitle("Best TrainSim ever!");
        primaryStage.setScene(scene);

        // Bind sizes of platform containers to fit window size
        vbxPlatformsStation.prefHeightProperty()
                .bind(scene.heightProperty().subtract(lblStation.getHeight())
                        .subtract(SOME_WEIRD_OVERHEAD));

        vbxPlatformsQueue.prefHeightProperty().bind(scene.heightProperty()
                .subtract(lblQueue.getHeight()).subtract(SOME_WEIRD_OVERHEAD));

        vbxPlatformsQueue.minWidthProperty().bind(scene.widthProperty()
                .subtract(vbxStation.getPrefWidth()).subtract(MARGIN_BETWEEN));

        addPlatforms();

        primaryStage.show();
    }

    private void addPlatforms() {
        for (int i = 0; i < numberOfPlatforms; i++) {
            TrackVisualisation tv = new TrackVisualisation(i, true);

            // Bind sizes so they always fit window
            tv.widthProperty().bind(vbxStation.widthProperty());
            tv.heightProperty().bind(vbxPlatformsStation.prefHeightProperty()
                    .divide(numberOfPlatforms));

            vbxPlatformsStation.getChildren().add(tv);
            
            // TODO: prepare queue
        }

    }

    @Override
    public void update(Observable obs, Object arg) {
        if (arg instanceof TrainEvent) {
            TrainEvent te = (TrainEvent) arg;
            TrackVisualisation tv;
            switch (te.getAction()) {
                case ARRIVE :
                     tv = (TrackVisualisation) vbxPlatformsStation
                            .getChildren().get(te.getPlatform());
                    tv.setDriverID(te.getId());
                    break;
                case DEPART :
                    tv = (TrackVisualisation) vbxPlatformsStation
                            .getChildren().get(te.getPlatform());
                    tv.setDriverID(TrackVisualisation.NO_DRIVER);
                    break;
                case WAIT :
                    // TODO: Implement queue
                    break;
            }
        }

    }

}
