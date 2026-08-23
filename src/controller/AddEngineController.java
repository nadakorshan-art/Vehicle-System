package controller;

import javafx.stage.Stage;
import view.AddEngineView;

public class AddEngineController {

    private AddEngineView view;
    private Stage stage;

    public AddEngineController(AddEngineView view, Stage stage) {
        this.view = view;
        this.stage = stage;
        setupActions();
    }

    private void setupActions() {
        view.getCancelButton().setOnAction(
                event -> closeWindow()
        );
    }

    private void closeWindow() {
        stage.close();
    }
    
}
