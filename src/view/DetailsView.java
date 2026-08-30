package view;

import java.util.HashMap;
import java.util.Map;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class DetailsView extends BorderPane {

    private final TextField plateNumberValue = new TextField();
    private TextField typeValue = new TextField();
    private TextField lengthValue = new TextField();
    private TextField colorValue = new TextField();
    private TextField modelValue = new TextField();
    private TextField manufactureDateValue = new TextField();
    private TextField bodySerialValue = new TextField();
    private TextField gearTypeValue = new TextField();

    private TextField engineModelValue = new TextField();
    private TextField cylindersValue = new TextField();
    private TextField fuelTypeValue = new TextField();
    private TextField capacityValue = new TextField();
    
    private TextField widthValue = new TextField();
    private TextField tireDiameterValue = new TextField();
    private TextField freeWeightValue = new TextField();
    private TextField fullWeightValue = new TextField();
    private final TextField seatsValue = new TextField();
    private CheckBox furnitureValue = new CheckBox("Leather");

    private Button deleteButton = new Button("Delete");
    private Button updateButton = new Button("Update");
    private Button printButton = new Button("Print");
    //A map for managing showing and hiding rows based on vehicle type
    private final Map<String, HBox> rowContainers = new HashMap<>();

    public DetailsView() {
        Label titleLabel = new Label("Vehicle Information");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #111111;");
        Separator titleSeparator = new Separator();

        VBox topSection = new VBox(8, titleLabel, titleSeparator);
        topSection.setPadding(new Insets(0, 0, 15, 0));

        GridPane informationGrid = new GridPane();
        informationGrid.setHgap(25);
        informationGrid.setVgap(12);
        informationGrid.setPadding(new Insets(20));

        buildGridRows(informationGrid);

        VBox informationBox = new VBox(informationGrid);
        informationBox.setStyle(
            "-fx-background-color: white; -fx-border-color: #d0d0d0; " +
            "-fx-border-width: 1px; -fx-background-radius: 5px; -fx-border-radius: 5px;"
        );

        setupButtons();

        HBox actionsBox = new HBox(15, deleteButton, updateButton, printButton);
        actionsBox.setAlignment(Pos.CENTER_LEFT);
        actionsBox.setPadding(new Insets(15, 0, 0, 0));

        VBox mainContent = new VBox(20, topSection, informationBox, actionsBox);
        setStyle("-fx-background-color: #f7f7f7;");
        setPadding(new Insets(25, 35, 25, 35));
        setCenter(mainContent);
    }

    private void setupButtons() {
        deleteButton.setStyle("-fx-background-color: #c95a4a; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 8px 35px; -fx-cursor: hand;");
        updateButton.setStyle("-fx-background-color: #2fbe4f; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 8px 35px; -fx-cursor: hand;");
        printButton.setStyle("-fx-background-color: #d9d9d9; -fx-text-fill: black; -fx-font-weight: bold; -fx-padding: 8px 35px; -fx-cursor: hand;");
    }

    private void buildGridRows(GridPane grid) {
        addRow(grid, "Plate Number:", plateNumberValue, 0, 0, "plateNumber");
        addRow(grid, "Type:", typeValue, 0, 1, "type");
        addRow(grid, "Model:", modelValue, 0, 2, "model");
        addRow(grid, "Manufacture Date:", manufactureDateValue, 0, 3, "manufactureDate");
        addRow(grid, "Body Serial Number:", bodySerialValue, 0, 4, "bodySerial");
        addRow(grid, "Length (m):", lengthValue, 0, 5, "length");
        addRow(grid, "Width (m):", widthValue, 0, 6, "width");
        addRow(grid, "Color:", colorValue, 0, 7, "color");

        addRow(grid, "Engine Model:", engineModelValue, 2, 0, "engineModel");
        addRow(grid, "Cylinders:", cylindersValue, 2, 1, "cylinders");
        addRow(grid, "Fuel Type:", fuelTypeValue, 2, 2, "fuelType");
        addRow(grid, "Capacity (L):", capacityValue, 2, 3, "capacity");
        addRow(grid, "Gear Type:", gearTypeValue, 2, 4, "gearType");

        addRow(grid, "Tire Diameter (m):", tireDiameterValue, 2, 5, "tireDiameter");
        addRow(grid, "Free Weight (kg):", freeWeightValue, 2, 6, "freeWeight");
        addRow(grid, "Full Weight (kg):", fullWeightValue, 2, 7, "fullWeight");
        addRow(grid, "Number of Seats:", seatsValue, 2, 8, "seats");
        addRow(grid, "Furniture:", furnitureValue, 2, 9, "furniture");
    }

    private void addRow(GridPane grid, String title, Node inputNode, int col, int row, String key) {
        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #111111;");

        HBox rowContainer = new HBox(10, titleLabel, inputNode);
        rowContainers.put(key, rowContainer);

        grid.add(titleLabel, col, row);
        grid.add(inputNode, col + 1, row);
    }

    public TextField getPlateNumberValue() { 
        return plateNumberValue; 
    }
    public TextField getTypeValue() { 
        return typeValue; 
    }
    public TextField getLengthValue() { 
        return lengthValue; 
    }
    public TextField getWidthValue() {
        return widthValue; 
    }
    public TextField getColorValue() { 
        return colorValue; 
    }
    public TextField getModelValue() { 
        return modelValue; 
    }
    public TextField getManufactureDateValue() { 
        return manufactureDateValue; 
    }
    public TextField getBodySerialValue() { 
        return bodySerialValue; 
    }
    public TextField getEngineModelValue() { 
        return engineModelValue; 
    }
    public TextField getCylindersValue() { 
        return cylindersValue; 
    }
    public TextField getFuelTypeValue() { 
        return fuelTypeValue; 
    }
    public TextField getCapacityValue() { 
        return capacityValue; 
    }
    public TextField getGearTypeValue(){
        return gearTypeValue;
    }
    public TextField getTireDiameterValue() {return tireDiameterValue;}
    public TextField getFreeWeightValue() { return freeWeightValue;}
    public TextField getFullWeightValue() { return fullWeightValue;}
    public TextField getSeatsValue() { return seatsValue;}
    public CheckBox getFurnitureValue() { return furnitureValue;}
    public Button getDeleteButton() { return deleteButton; }
    public Button getUpdateButton() { return updateButton; }
    public Button getPrintButton() { return printButton; }

}