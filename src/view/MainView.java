package view;

import java.util.Arrays;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import model.Automobile;

public class MainView extends BorderPane {

    private Button addButton;
    private TableView<Automobile> vehicleTable;

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

        addButton = new Button("+ Add");
        addButton.getStyleClass().add("btn-primary");

        HBox actionsBox = new HBox(10, searchField, searchButton, addButton);
        actionsBox.setAlignment(Pos.CENTER_RIGHT);

        BorderPane topBar = new BorderPane();
        topBar.setLeft(titleBox);
        topBar.setRight(actionsBox);
        topBar.setPadding(new Insets(0, 0, 20, 0));

        //Table Section
        vehicleTable = new TableView<>();
        vehicleTable.getStyleClass().add("custom-table");

        TableColumn<Automobile, String> plateNumberColumn = new TableColumn<>("Plate Number");
        TableColumn<Automobile, String> brandColumn = new TableColumn<>("Brand");
        TableColumn<Automobile, String> modelColumn = new TableColumn<>("Model");
        TableColumn<Automobile, String> typeColumn = new TableColumn<>("Type");
        TableColumn<Automobile, String> bodySerialNumberColumn = new TableColumn<>("Body Serial Number");
        TableColumn<Automobile, String> manufactureDateColumn = new TableColumn<>("Manufacture Date");
        TableColumn<Automobile, String> actionsColumn = new TableColumn<>("Actions");

        vehicleTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        plateNumberColumn.setMaxWidth(1f * Integer.MAX_VALUE);
        brandColumn.setMaxWidth(1f * Integer.MAX_VALUE);
        modelColumn.setMaxWidth(1f * Integer.MAX_VALUE);
        typeColumn.setMaxWidth(1f * Integer.MAX_VALUE);
        bodySerialNumberColumn.setMaxWidth(1f * Integer.MAX_VALUE);
        manufactureDateColumn.setMaxWidth(1f * Integer.MAX_VALUE);

       actionsColumn.setMaxWidth(120);
       actionsColumn.setMinWidth(100);

        vehicleTable.getColumns().addAll(Arrays.asList(
                plateNumberColumn,
                brandColumn,
                modelColumn,
                typeColumn,
                bodySerialNumberColumn,
                manufactureDateColumn,
                actionsColumn
        ));

      /*actionsColumn.setCellFactory(param -> new TableCell<>() {
            private final Button editBtn = new Button();
            private final Button detailsBtn = new Button();
            private final HBox container = new HBox(8, editBtn, detailsBtn);

            {
                editBtn.getStyleClass().addAll("icon-button", "edit-button");
                detailsBtn.getStyleClass().addAll("icon-button", "details-button");
                container.setStyle("-fx-alignment: CENTER;");
            }

           @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : container);
            }
        });*/

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
    
    public Button getAddButton() {
        return addButton;
    }
    public TableView<Automobile> getVehicleTable() {
        return vehicleTable;
    }

}