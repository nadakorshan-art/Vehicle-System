package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.FuelType;


public class AddEngineView extends BorderPane {

    private TextField manufactureField;
    private DatePicker manufactureDatePicker;
    private TextField modelField;
    private TextField capacityField;
    private TextField cylindersField;
    private ComboBox<FuelType> fuelTypeComboBox;

    private Button saveButton;
    private Button cancelButton;

    public AddEngineView() {

        Label titleLabel = new Label("Create new Engine");
        titleLabel.getStyleClass().add("page-title");

        HBox topBar = new HBox(titleLabel);
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setPadding(new Insets(0, 0, 15, 0));

        manufactureField = new TextField();
        manufactureField.setPromptText("Toyota");
        manufactureDatePicker = new DatePicker();
        manufactureDatePicker.setPromptText("mm/dd/yyyy");
        modelField = new TextField();
        modelField.setPromptText("M20A-FKS");
        capacityField = new TextField();
        capacityField.setPromptText("0.0");
        cylindersField = new TextField();
        cylindersField.setPromptText("0");
       
        fuelTypeComboBox = new ComboBox<>();
        fuelTypeComboBox.getItems().setAll(FuelType.values());
        fuelTypeComboBox.setPromptText("Select Fuel Type");

        GridPane grid = new GridPane();
        grid.setHgap(40);
        grid.setVgap(15);
        grid.setPadding(new Insets(10, 0, 0, 0));

        grid.add(new Label("Manufacture:"), 0, 0);
        grid.add(manufactureField, 1, 0);

        grid.add(new Label("Manufacture Date:"), 2, 0);
        grid.add(manufactureDatePicker, 3, 0);

        grid.add(new Label("Model:"), 0, 1);
        grid.add(modelField, 1, 1);

        grid.add(new Label("Capacity (L):"), 2, 1);
        grid.add(capacityField, 3, 1);

        grid.add(new Label("Cylinders:"), 0, 2);
        grid.add(cylindersField, 1, 2);

        grid.add(new Label("Fuel Type:"), 2, 2);
        grid.add(fuelTypeComboBox, 3, 2);

        VBox formContainer = new VBox(grid);
        formContainer.setPadding(new Insets(25));
        formContainer.setAlignment(Pos.TOP_CENTER);

        saveButton = new Button("Save");
        saveButton.getStyleClass().add("btn-primary");

        cancelButton = new Button("Cancel");
        cancelButton.getStyleClass().add("btn-secondary");

        HBox actionsBox = new HBox(15, saveButton, cancelButton);
        actionsBox.setAlignment(Pos.CENTER_LEFT);
        actionsBox.setPadding(new Insets(10, 0, 0, 20));

        setPadding(new Insets(20));
        setTop(topBar);
        setCenter(formContainer);
        setBottom(actionsBox);

        var css = getClass().getResource("/resources/style.css");

        if (css != null) {
            getStylesheets().add(css.toExternalForm());
        } else {
            System.out.println("style.css not found");
        }
    }

    public TextField getManufactureField(){return manufactureField;}
    public DatePicker getManufactureDatePicker(){return manufactureDatePicker;}
    public TextField getModelField(){return modelField;}
    public ComboBox<FuelType> getFuleComboBox(){return fuelTypeComboBox;}
    public TextField getCapacityField(){return capacityField;}
    public TextField getCylindersField(){return cylindersField;}
    public Button getSaveButton(){return saveButton;}
    public Button getCancelButton() {
        return cancelButton;
    }

}