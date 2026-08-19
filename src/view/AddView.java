package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class AddView extends BorderPane {

    private ComboBox<String> typeComboBox;
    private TextField plateNumField;
    private TextField manufactureNameField;
    private TextField modelField;
    private ComboBox<String> engineComboBox;
    private Button createEngineButton;
    private DatePicker manufactureDatePicker;
    private TextField bodySerialField;
    private ComboBox<String> gearTypeComboBox;

    private TextField lengthField;
    private TextField widthField;
    private TextField colorField;
    private TextField seatsField;
    private CheckBox leatherCheckBox;

    private Button saveButton;
    private Button cancelButton;

    public AddView() {

        Label titleLabel = new Label("Add Vehicle");
        titleLabel.getStyleClass().add("page-title");

        HBox topBar = new HBox(titleLabel);
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setPadding(new Insets(0, 0, 15, 0));

        Label section1Label = new Label("Vehicle Information");
        section1Label.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");

        GridPane grid1 = new GridPane();
        grid1.setHgap(15);
        grid1.setVgap(10);
        grid1.setPadding(new Insets(10, 0, 15, 0));

        typeComboBox = new ComboBox<>();
        typeComboBox.getItems().addAll("Car", "Truck", "Motorcycle");
        typeComboBox.setValue("Car");

        plateNumField = new TextField();
        plateNumField.setPromptText("PK23 P147");
        manufactureNameField = new TextField();
        manufactureNameField.setPromptText("KIA");
        modelField = new TextField();
        modelField.setPromptText("2.0L");

        engineComboBox = new ComboBox<>();
        engineComboBox.setPromptText("Select Engine");
        createEngineButton = new Button("+Create new");
        createEngineButton.getStyleClass().add("btn-secondary");
        HBox engineBox = new HBox(5, engineComboBox, createEngineButton);

        manufactureDatePicker = new DatePicker();
        manufactureDatePicker.setPromptText("mm/dd/yyyy");
        bodySerialField = new TextField();
        bodySerialField.setPromptText("BSN-2025-001234");

        gearTypeComboBox = new ComboBox<>();
        gearTypeComboBox.getItems().addAll("Normal", "Automatic");

        grid1.add(new Label("Automobile Type:"), 0, 0);
        grid1.add(typeComboBox, 1, 0);

        grid1.add(new Label("Plate Number:"), 0, 1);
        grid1.add(plateNumField, 1, 1);

        grid1.add(new Label("Manufacture Name:"), 0, 2);
        grid1.add(manufactureNameField, 1, 2);

        grid1.add(new Label("Model:"), 0, 3);
        grid1.add(modelField, 1, 3);

        grid1.add(new Label("Select Engine:"), 2, 0);
        grid1.add(engineBox, 3, 0);

        grid1.add(new Label("Manufacture Date:"), 2, 1);
        grid1.add(manufactureDatePicker, 3, 1);

        grid1.add(new Label("Body Serial Number:"), 2, 2);
        grid1.add(bodySerialField, 3, 2);

        grid1.add(new Label("Gear Type:"), 2, 3);
        grid1.add(gearTypeComboBox, 3, 3);

        Label section2Label = new Label("Additional Information");
        section2Label.getStyleClass().add("section-title");
        GridPane grid2 = new GridPane();
        grid2.setHgap(15);
        grid2.setVgap(10);
        grid2.setPadding(new Insets(10, 0, 0, 0));
        VBox additionalInfoBox = new VBox(5, section2Label, grid2);
        additionalInfoBox.getStyleClass().add("additional-info-box");

        lengthField = new TextField();
        lengthField.setPromptText("0.0");
        Label lengthUnit = new Label("m");
        lengthUnit.getStyleClass().add("unit-label");
        HBox lengthBox = new HBox(8, lengthField, lengthUnit);
        lengthBox.setAlignment(Pos.CENTER_LEFT);

        widthField = new TextField();
        widthField.setPromptText("0.0");
        Label widthUnit = new Label("m");
        widthUnit.getStyleClass().add("unit-label");
        HBox widthBox = new HBox(8, widthField, widthUnit);
        widthBox.setAlignment(Pos.CENTER_LEFT);

        colorField = new TextField();
        colorField.setPromptText("Red");
        seatsField = new TextField();
        seatsField.setPromptText("0");
        leatherCheckBox = new CheckBox("Leather");

        grid2.add(new Label("Length:"), 0, 0);
        grid2.add(lengthBox, 1, 0);

        grid2.add(new Label("Width:"), 2, 0);
        grid2.add(widthBox, 3, 0);

        grid2.add(new Label("Color:"), 0, 1);
        grid2.add(colorField, 1, 1);

        grid2.add(new Label("Number of Seats:"), 2, 1);
        grid2.add(seatsField, 3, 1);

        grid2.add(new Label("Furniture:"), 2, 2);
        grid2.add(leatherCheckBox, 3, 2);

        saveButton = new Button("Save");
        saveButton.getStyleClass().add("btn-primary");

        cancelButton = new Button("Cancel");
        cancelButton.getStyleClass().add("btn-secondary");

        HBox actionsBox = new HBox(15, saveButton, cancelButton);
        actionsBox.setAlignment(Pos.CENTER_LEFT);
        actionsBox.setPadding(new Insets(10, 0, 0, 0));

        VBox formContainer = new VBox(10, 
            section1Label, grid1, 
            new Separator(), 
            additionalInfoBox, 
            actionsBox
        );

        setPadding(new Insets(25));
        setTop(topBar);
        setCenter(formContainer);

        try {
            getStylesheets().add(getClass().getResource("/resources/style.css").toExternalForm());
        } catch (Exception e) {
            System.out.println("Warning: Could not load style.css file.");
        }
    }
}