package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class MainView extends BorderPane {

    public MainView() {

        //Header Section 
        Label titleLabel = new Label("Vehicles");
        titleLabel.getStyleClass().add("page-title");

        ComboBox<String> filterComboBox = new ComboBox<>();
        filterComboBox.getItems().addAll("All", "Car", "Truck", "Motorcycle");
        filterComboBox.setValue("All");
        filterComboBox.getStyleClass().add("filter-combo");

        HBox titleBox = new HBox(15, titleLabel, filterComboBox);
        titleBox.setAlignment(Pos.CENTER_LEFT);

        //Search & Add 
        TextField searchField = new TextField();
        searchField.setPromptText("Search...");
        searchField.getStyleClass().add("search-field");

        Button searchButton = new Button("Search");
        searchButton.getStyleClass().add("btn-secondary");

        Button addButton = new Button("+ Add");
        addButton.getStyleClass().add("btn-primary");

        HBox actionsBox = new HBox(10, searchField, searchButton, addButton);
        actionsBox.setAlignment(Pos.CENTER_RIGHT);

        BorderPane topBar = new BorderPane();
        topBar.setLeft(titleBox);
        topBar.setRight(actionsBox);
        topBar.setPadding(new Insets(0, 0, 20, 0));

        //Table Section
        TableView<String> vehicleTable = new TableView<>();
        vehicleTable.getStyleClass().add("custom-table");

        TableColumn<String, String> plateNumberColumn = new TableColumn<>("Plate Number");
        TableColumn<String, String> brandColumn = new TableColumn<>("Brand");
        TableColumn<String, String> modelColumn = new TableColumn<>("Model");
        TableColumn<String, String> typeColumn = new TableColumn<>("Type");
        TableColumn<String, String> bodySerialNumberColumn = new TableColumn<>("Body Serial Number");
        TableColumn<String, String> manufactureDateColumn = new TableColumn<>("Manufacture Date");
        TableColumn<String, String> actionsColumn = new TableColumn<>("Actions");

        // Set column widths
        plateNumberColumn.setPrefWidth(120);
        brandColumn.setPrefWidth(120);
        modelColumn.setPrefWidth(120);
        typeColumn.setPrefWidth(100);
        bodySerialNumberColumn.setPrefWidth(160);
        manufactureDateColumn.setPrefWidth(140);
        actionsColumn.setPrefWidth(100);

        vehicleTable.getColumns().addAll(
                plateNumberColumn,
                brandColumn,
                modelColumn,
                typeColumn,
                bodySerialNumberColumn,
                manufactureDateColumn,
                actionsColumn
        );

        // Layout 
        setPadding(new Insets(25));
        setTop(topBar);
        setCenter(vehicleTable);

        //CSS Style
        try {
            getStylesheets().add(getClass().getResource("/resources/style.css").toExternalForm());
        } catch (Exception e) {
            // In case CSS is not found in resources
            System.out.println("Warning: Could not load style.css file. Using default JavaFX styles.");
        }
    }
}