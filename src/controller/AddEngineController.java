package controller;

import javafx.stage.Stage;
import view.AddEngineView;

public class AddEngineController {

    private AddEngineView view;
    private Stage stage;
    private AddController addController;

    public AddEngineController(AddEngineView view, Stage stage, AddController addController) {
        this.view = view;
        this.stage = stage;
        this.addController = addController;
        setupActions();
    }

    private void setupActions() {
        view.getCancelButton().setOnAction(event -> closeWindow());
        
        //view.getSaveButton().setOnAction(event -> saveEngine());
    }

    private void closeWindow() {
        stage.close();
    }
    
}
