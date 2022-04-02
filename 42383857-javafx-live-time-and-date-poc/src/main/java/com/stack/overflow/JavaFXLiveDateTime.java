package com.stack.overflow;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Consumer;

public class JavaFXLiveDateTime extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        HBox root = new HBox();
        root.setSpacing(40);
        root.setPadding(new Insets(20, 20, 20, 20));
        root.getChildren().add(timelineDateTime());
        root.getChildren().add(animationTimerDateTime());

        Scene scene = new Scene(root, 450, 150);

        primaryStage.setTitle("JavaFX Live Date and Time");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Node timelineDateTime() {
        return liveDateTimeDisplayNode("Timeline", this.timelineDisplay());
    }

    private Node animationTimerDateTime() {
        return liveDateTimeDisplayNode("Animation Timer", this.animationTimerDisplay());
    }

    private Node liveDateTimeDisplayNode(String type, Consumer<Label> consumerType) {
        VBox animationTimerTimeBox = new VBox();

        animationTimerTimeBox.getChildren().add(new Label(type));

        Label dateTimeDisplay = new Label();
        consumerType.accept(dateTimeDisplay);
        animationTimerTimeBox.getChildren().add(dateTimeDisplay);

        animationTimerTimeBox.setSpacing(10);
        return animationTimerTimeBox;
    }

    private Consumer<Label> timelineDisplay() {
        return (Label dateTimeDisplay) -> {
            Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> setDateTime(dateTimeDisplay)),
                    new KeyFrame(Duration.seconds(.9))
            );
            clock.setCycleCount(Animation.INDEFINITE);
            clock.play();
        };
    }

    private Consumer<Label> animationTimerDisplay() {
        return (Label dateTimeDisplay) -> {
            AnimationTimer timer = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    setDateTime(dateTimeDisplay);
                }
            };
            timer.start();
        };
    }

    private void setDateTime(Label label) {
        label.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }


}
