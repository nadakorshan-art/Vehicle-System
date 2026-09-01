package view;

import java.util.Arrays;
import java.util.function.Consumer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import model.Automobile;
import model.Car;
import model.Motorcycle;
import model.Truck;

public class MainView extends BorderPane {

    private Button addButton;
    private TableView<Automobile> vehicleTable;
    private TableColumn<Automobile, Void> actionsColumn;
    private TextField searchField;
    private Button searchButton;

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
        searchField = new TextField();
        searchField.setPromptText("Search...");
        searchField.getStyleClass().add("search-field");

        searchButton = new Button("Search");
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
        plateNumberColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().plateNum));
        TableColumn<Automobile, String> brandColumn = new TableColumn<>("Brand");
        brandColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().manufactureCompany));
        TableColumn<Automobile, String> modelColumn = new TableColumn<>("Model");
        modelColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().model));
        TableColumn<Automobile, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setCellValueFactory(cellData -> {
            Automobile vehicle = cellData.getValue();
            String type;
            if (vehicle instanceof Car) {
                type = "Car";
            } else if (vehicle instanceof Truck) {
                type = "Truck";
            } else if (vehicle instanceof Motorcycle) {
                type = "Motorcycle";
            } else {
                type = "";
            }
            return new javafx.beans.property.SimpleStringProperty(type);
        });

        TableColumn<Automobile, String> bodySerialNumberColumn = new TableColumn<>("Body Serial Number");
        bodySerialNumberColumn.setCellValueFactory(new PropertyValueFactory<>("bodySerialNum"));
        TableColumn<Automobile, String> manufactureDateColumn = new TableColumn<>("Manufacture Date");
        manufactureDateColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().manufactureDate != null ? cellData.getValue().manufactureDate.toString() : ""));
        actionsColumn = new TableColumn<>("Actions");

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
    
    public void setActionHandlers(Consumer<Automobile> editHandler,Consumer<Automobile> detailsHandler){

        actionsColumn.setCellFactory(param -> new TableCell<>() {
            private final Button editBtn = new Button();
            private final Button detailsBtn = new Button();
            private final HBox container = new HBox(8, editBtn, detailsBtn);

            {
                editBtn.getStyleClass().addAll("icon-button", "edit-button");
                detailsBtn.getStyleClass().addAll("icon-button", "details-button");
                container.setStyle("-fx-alignment: CENTER;");
                var editStream = getClass().getResourceAsStream("/resources/Pencil-Icon.png");
                ImageView editIcon = new ImageView(new Image(editStream));
                editIcon.setFitWidth(18);
                editIcon.setFitHeight(18);
                editBtn.setGraphic(editIcon);

                var detailsStream = getClass().getResourceAsStream("/resources/icons-rubbish.png");
                ImageView detailsIcon = new ImageView(new Image(detailsStream));
                detailsIcon.setFitWidth(18);
                detailsIcon.setFitHeight(18);
                detailsBtn.setGraphic(detailsIcon);

                editBtn.setOnAction(event -> {
                    Automobile vehicle = getTableView().getItems().get(getIndex());
                    editHandler.accept(vehicle);
                });

                detailsBtn.setOnAction(event -> {
                    Automobile vehicle = getTableView().getItems().get(getIndex());
                    detailsHandler.accept(vehicle);
                });
            }

           @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : container);
            }
        });

    }
    public Button getAddButton() {
        return addButton;
    }
    public TableView<Automobile> getVehicleTable() {
        return vehicleTable;
    }
    public TextField getSearchField() {
        return searchField;
    }
    public Button getSearchButton() {
        return searchButton;
    }
}