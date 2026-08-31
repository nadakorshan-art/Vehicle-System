package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DetailsView extends BorderPane {

    private final Label plateNumberValue = new Label();;
    private final Label typeValue = new Label();;
    private final Label lengthValue = new Label();;
    private final Label widthValue = new Label();;
    private final Label colorValue = new Label();;
    private final Label modelValue = new Label();;
    private final Label manufactureDateValue = new Label();;
    private final Label bodySerialValue = new Label();;
    private final Label engineModelValue = new Label();;
    private final Label cylindersValue = new Label();;
    private final Label fuelTypeValue = new Label();;
    private final Label capacityValue = new Label();;

    private final Button deleteButton = new Button("Delete");
    private final Button updateButton = new Button("Update");
    private final Button printButton = new Button("Print");

    public DetailsView() {

        Label titleLabel = new Label("Vehicle Information");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #111111;");

        Separator titleSeparator = new Separator();
        VBox topSection = new VBox(8, titleLabel, titleSeparator);
        topSection.setPadding(new Insets(0, 0, 20, 0));

        GridPane informationGrid = new GridPane();
        informationGrid.setHgap(25);
        informationGrid.setVgap(10);
        informationGrid.setPadding(new Insets(15, 20, 15, 20));


        addInformationRow(
            informationGrid,
            "Plate Number:",
            plateNumberValue,
            0
        );

        addInformationRow(
            informationGrid,
            "Type:",
            typeValue,
            1
        );

        addInformationRow(
            informationGrid,
            "Length:",
            lengthValue,
            2
        );

        addInformationRow(
            informationGrid,
            "Width:",
            widthValue,
            3
        );

        addInformationRow(
            informationGrid,
            "Color:",
            colorValue,
            4
        );

        addInformationRow(
            informationGrid,
            "Model:",
            modelValue,
            5
        );

        addInformationRow(
            informationGrid,
            "Manufacture Date:",
            manufactureDateValue,
            6
        );

        addInformationRow(
            informationGrid,
            "Body Serial Number:",
            bodySerialValue,
            7
        );

        addInformationRow(
            informationGrid,
            "Engine Model:",
            engineModelValue,
            8
        );

        addInformationRow(
            informationGrid,
            "Cylinders:",
            cylindersValue,
            9
        );

        addInformationRow(
            informationGrid,
            "Fuel Type:",
            fuelTypeValue,
            10
        );

        addInformationRow(
            informationGrid,
            "Capacity:",
            capacityValue,
            11
        );


        VBox informationBox = new VBox(informationGrid);
        informationBox.setStyle(
            "-fx-background-color: white; -fx-border-color: #555555;" +
            "-fx-border-width: 1px; -fx-background-radius: 2px; -fx-border-radius: 2px;"
        );

        deleteButton.setStyle(
            "-fx-background-color: #c95a4a;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 14px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 3px;" +
            "-fx-border-color: #9f4034;" +
            "-fx-border-radius: 3px;" +
            "-fx-padding: 8px 55px;"
        );

        updateButton.setStyle(
            "-fx-background-color: #2fbe4f;" +
            "-fx-text-fill: black;" +
            "-fx-font-size: 14px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 3px;" +
            "-fx-border-color: #218b38;" +
            "-fx-border-radius: 3px;" +
            "-fx-padding: 8px 55px;"
        );

        printButton.setStyle(
            "-fx-background-color: #d9d9d9;" +
            "-fx-text-fill: black;" +
            "-fx-font-size: 14px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 3px;" +
            "-fx-border-color: #888888;" +
            "-fx-border-radius: 3px;" +
            "-fx-padding: 8px 55px;"
        );


        HBox actionsBox = new HBox(
            20,
            deleteButton,
            updateButton,
            printButton
        );

        actionsBox.setAlignment(Pos.CENTER_LEFT);
        actionsBox.setPadding(new Insets(25, 0, 0, 0));

        VBox mainContent = new VBox(
            20,
            topSection,
            informationBox,
            actionsBox
        );

        mainContent.setPadding(new Insets(25, 0, 0, 0));

        setStyle("-fx-background-color: #f7f7f7;" );
        setPadding(new Insets(0, 35, 25, 35));
        setCenter(mainContent);
    }


    private void addInformationRow( GridPane grid, String title, Label value, int row) {
        Label titleLabel = new Label(title);
        titleLabel.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #111111;"
        );
        value.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-text-fill: #222222;"
        );

        grid.add(titleLabel, 0, row);
        grid.add(value, 1, row);
    }

    public Label getPlateNumberValue() { return plateNumberValue; }
    public Label getTypeValue() { return typeValue; }
    public Label getLengthValue() { return lengthValue; }
    public Label getWidthValue() { return widthValue; }
    public Label getColorValue() { return colorValue; }
    public Label getModelValue() { return modelValue; }
    public Label getManufactureDateValue() { return manufactureDateValue; }
    public Label getBodySerialValue() { return bodySerialValue; }
    public Label getEngineModelValue() { return engineModelValue; }
    public Label getCylindersValue() { return cylindersValue; }
    public Label getFuelTypeValue() { return fuelTypeValue; }
    public Label getCapacityValue() { return capacityValue; }

    public Button getDeleteButton() { return deleteButton; }
    public Button getUpdateButton() { return updateButton; }
    public Button getPrintButton() { return printButton; }

}