package com.stack.overflow;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class JavaFXSmoothWindowDragging extends Application {

    private double gapX = 0, gapY = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.initStyle(StageStyle.TRANSPARENT);

        HBox layout = new HBox();
        layout.getChildren().addAll(new Label("Draggable Window"), exitButton());
        layout.setSpacing(20);
        layout.setPadding(new Insets(60, 60, 60, 60));

        layout.setOnMouseDragged(e -> this.dragStage(e, primaryStage));
        layout.setOnMouseMoved(e -> this.calculateGap(e, primaryStage));

        Scene scene = new Scene(layout, 320, 150);
        scene.setFill(Color.TRANSPARENT);

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    private Node exitButton() {
        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> Platform.exit());
        return exitButton;
    }

    private void calculateGap(MouseEvent event, Stage stage) {
        gapX = event.getScreenX() - stage.getX();
        gapY = event.getScreenY() - stage.getY();
    }

    private void dragStage(MouseEvent event, Stage stage) {
        stage.setX(event.getScreenX() - gapX);
        stage.setY(event.getScreenY() - gapY);
    }
}
