package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class HomeView extends VBox {

    public HomeView() {
        setAlignment(Pos.CENTER);
        setSpacing(15);
        setPadding(new Insets(30));

        Label titleLabel = new Label("Vehicle Management System");
        titleLabel.setStyle("-fx-font-size: 26px; -fx-font-weight: bold; -fx-text-fill: #1e293b;");

        Label subtitleLabel = new Label("Manage Your Vehicles Easily");
        subtitleLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #64748b;");

        ImageView imageView = new ImageView();
        try {
            Image logo = new Image(getClass().getResourceAsStream("/resources/vehicles.jpg"));
            imageView.setImage(logo);
            imageView.setFitWidth(250);
            imageView.setPreserveRatio(true);
        } catch (Exception e) {
            System.out.println("Image not found: " + e.getMessage());
        }

        getChildren().addAll(imageView, titleLabel, subtitleLabel);
    }
}