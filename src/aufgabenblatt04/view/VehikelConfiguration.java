/*
 * Praktikum Programmiermethodik 2 - WS 2016
 * Gruppe:  Philip Scheer (Philip.Scheeer@haw-hamburg.de),
 *          Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 18.12.2016 
 * Aufgabe: Praktikum 4
 */

package aufgabenblatt04.view;

import aufgabenblatt04.braitenbergvehikel.BVBewegung;
import aufgabenblatt04.braitenbergvehikel.BVBewegungAbstossung;
import aufgabenblatt04.braitenbergvehikel.BVBewegungAttraktion;
import aufgabenblatt04.braitenbergvehikel.BraitenbergVehikel;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * GUI container for configuring a {@link BraitenbergVehikel}
 *
 * @author Moritz Höwer, Philip Scheer
 * @version 1.0 - 18.12.2016
 */
public class VehikelConfiguration extends HBox {

    private static final Insets GLOBAL_PADDING = new Insets(6, 0, 0, 0);

    private static final Insets NAME_PADDING = new Insets(4);

    private static final int NAME_WIDTH = 50;

    /**
     * the {@link BraitenbergVehikel} to be configured
     */
    private BraitenbergVehikel vehikel;

    /**
     * Options for selection of movement
     */
    private ComboBox<BVBewegung> movement;

    /**
     * used to display the name of the {@link BraitenbergVehikel}
     */
    private Label name;

    public VehikelConfiguration(BraitenbergVehikel vehikel) {
        this.vehikel = vehikel;
        initialize();
    }

    /**
     * initializes this and all subcomponents
     */
    private void initialize() {
        name = new Label();
        movement = new ComboBox<>();

        name.setText(vehikel.getName());
        initializeComboBoxWithMovementTypes();

        movement.getSelectionModel().select(vehikel.getBewegung());
        name.setPadding(NAME_PADDING);
        name.setPrefWidth(NAME_WIDTH);

        movement.getSelectionModel().selectedItemProperty()
                .addListener(this::movementChanged);

        getChildren().add(name);
        getChildren().add(movement);
        this.setPadding(GLOBAL_PADDING);
    }

    /**
     * initializes the combo box with all possible movement types.
     * 
     * NOT DYNAMIC! New movement types must be added by hand!
     */
    private void initializeComboBoxWithMovementTypes() {
        movement.getItems().clear();
        movement.getItems().add(new BVBewegungAbstossung());
        movement.getItems().add(new BVBewegungAttraktion());
    }

    /**
     * ChangeListener for combo box.
     * 
     * Fires if movement change is requested
     * 
     * @param ov
     *            required parameter
     * @param old
     *            the value before the change
     * @param current
     *            the current value after the change
     */
    private void movementChanged(ObservableValue<? extends BVBewegung> ov,
            BVBewegung old, BVBewegung current) {
        vehikel.setBewegung(current);
    }

}
