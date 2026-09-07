package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import model.Engine;
import model.GearType;

public class AddView extends BorderPane {

    private ComboBox<String> typeComboBox;
    private TextField plateNumField;
    private TextField manufactureNameField;
    private TextField modelField;
    private ComboBox<Engine> engineComboBox;
    private Button createEngineButton;
    private DatePicker manufactureDatePicker;
    private TextField bodySerialField;
    private ComboBox<GearType> gearTypeComboBox;
    private TextField lengthField;
    private ColorPicker colorPicker = new ColorPicker(javafx.scene.paint.Color.BLACK);

    private TextField extraField1;
    private CheckBox leatherCheckBox;
    private TextField extraField2;
    private TextField extraField3;

    private Button saveButton;
    private Button cancelButton;
    private GridPane grid2;
    private Label firstExtraLabel;
    private Label secondExtraLabel;
    private Label thirdExtraLabel;
    private Label titleLabel;

    public AddView() {

        titleLabel = new Label("Add Vehicle");
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
        typeComboBox.setOnAction(e -> updateFieldsForType(typeComboBox.getValue()));

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
        gearTypeComboBox.getItems().setAll(GearType.values());
        gearTypeComboBox.setPromptText("Select Gear Type");
        lengthField = new TextField();
        lengthField.setPromptText("0.0");

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

        grid1.add(new Label("Length (m):"), 0, 4);
        grid1.add(lengthField, 1, 4);

        grid1.add(new Label("Color:"), 2, 4);
        grid1.add(colorPicker, 3, 4);

        Label section2Label = new Label("Additional Information");
        section2Label.getStyleClass().add("section-title");
        grid2 = new GridPane();
        grid2.setHgap(15);
        grid2.setVgap(10);
        grid2.setPadding(new Insets(10, 0, 0, 0));
        VBox additionalInfoBox = new VBox(5, section2Label, grid2);
        additionalInfoBox.getStyleClass().add("additional-info-box");

        extraField1 = new TextField();
        extraField2 = new TextField();
        extraField3 = new TextField();
        leatherCheckBox = new CheckBox("Leather");
        
        firstExtraLabel = new Label();
        secondExtraLabel = new Label();
        thirdExtraLabel = new Label();

        updateFieldsForType("Car");
        
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
    public Label getTitleLabel(){
        return titleLabel;
    }

    public ComboBox<String> getTypeComboBox() {
        return typeComboBox;
    }

    public TextField getPlateNumField() {
        return plateNumField;
    }

    public TextField getManufactureNameField() {
        return manufactureNameField;
    }

    public TextField getModelField() {
        return modelField;
    }
    public Button getCreateEngineButton() {
        return createEngineButton;
    }

    public ComboBox<Engine> getEngineComboBox() {
        return engineComboBox; 
    }

    public DatePicker getManufactureDatePicker() {
        return manufactureDatePicker;
    }

    public TextField getBodySerialField() {
        return bodySerialField;
    }

    public ComboBox<GearType> getGearTypeComboBox() {
        return gearTypeComboBox;
    }

    public TextField getLengthField() {
        return lengthField;
    }

    public ColorPicker getColorPicker() {
        return colorPicker;
    }

    public TextField getExtraField1() { return extraField1; }
    public TextField getExtraField2() { return extraField2; }
    public TextField getExtraField3() { return extraField3; }
    public CheckBox getLeatherCheckBox() { return leatherCheckBox;}

    public Button getSaveButton() {
        return saveButton;
    }

    public Button getCancelButton() {
        return cancelButton;
    }

    public void updateFieldsForType(String type) {
        grid2.getChildren().clear();
        switch (type) {
            case "Car":
                firstExtraLabel.setText("Width (m):");
                extraField1.setPromptText("0.0");
                secondExtraLabel.setText("Number of Seats:");
                extraField2.setPromptText("0");
                thirdExtraLabel.setText("Furniture:");

                grid2.add(firstExtraLabel, 0, 0);
                grid2.add(extraField1, 1, 0);
                grid2.add(secondExtraLabel, 2, 0);
                grid2.add(extraField2, 3, 0);
                grid2.add(thirdExtraLabel, 2, 1);
                grid2.add(leatherCheckBox, 3, 1);
                break;

            case "Truck":
                firstExtraLabel.setText("Width (m):");
                extraField1.setPromptText("0.0");
                secondExtraLabel.setText("Free Weight (kg):");
                extraField2.setPromptText("0.0");
                thirdExtraLabel.setText("Full Weight (kg):");
                extraField3.setPromptText("0.0");

                grid2.add(firstExtraLabel, 0, 0);
                grid2.add(extraField1, 1, 0);
                grid2.add(secondExtraLabel, 2, 0);
                grid2.add(extraField2, 3, 0);
                grid2.add(thirdExtraLabel, 2, 1);
                grid2.add(extraField3, 3, 1);
                break;

            case "Motorcycle":
                firstExtraLabel.setText("Tire Diameter (m):");
                extraField1.setPromptText("0.0");

                grid2.add(firstExtraLabel, 0, 0);
                grid2.add(extraField1, 1, 0);
                break;
        }
    }
}