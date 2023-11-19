package com.example.sierpinskitriangle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private Canvas canvas;
    private GraphicsContext gc;
    private TextField orderTextField;

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Sierpinski Triangle");

        // Create UI components
        canvas = new Canvas(400, 400);
        gc = canvas.getGraphicsContext2D();
        orderTextField = new TextField("0");
//        orderTextField.setPrefWidth(50);

        // Set action for the text field
        orderTextField.setOnAction(e -> drawSierpinskiTriangle());

        // Create layout
        BorderPane layout = new BorderPane();
        layout.setCenter(canvas);
        layout.setBottom(createControls());

        // Create scene
        Scene scene = new Scene(layout);


        // Set scene to stage
        stage.setScene(scene);
        stage.setMinWidth(400);
        stage.setMinHeight(500);
        stage.setMaxHeight(500);
        stage.setMaxWidth(400);
//        orderTextField.prefWidthProperty().bind(scene.widthProperty().multiply(0.1));


        // Draw the initial Sierpinski Triangle
        drawSierpinskiTriangle();

        // Show stage
        stage.show();
    }

    private BorderPane createControls() {
        BorderPane controls = new BorderPane();
        var hbox = new HBox();

        Label title = new Label("Enter an order: ");
        title.setStyle("-fx-font-size: 20px;");
        orderTextField.setAlignment(Pos.CENTER_RIGHT);
        HBox.setMargin(orderTextField, new Insets(0,10,10,0));
        HBox.setMargin(title, new Insets(0,5,10,10));
        hbox.getChildren().addAll(title, orderTextField);
        hbox.setAlignment(Pos.CENTER);
        controls.setCenter(hbox);
        BorderPane.setAlignment(hbox, Pos.CENTER_RIGHT);
        return controls;
    }

    private void drawSierpinskiTriangle() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        try {
            int order = Integer.parseInt(orderTextField.getText().trim());
            drawSierpinskiTriangle(order, 50, 350, 350, 350, 200, 50);
        } catch (NumberFormatException e) {
            // Handle non-integer input
            System.out.println("Invalid order. Please enter a valid integer.");
        }
    }
    private void drawSierpinskiTriangle(int order, double x1, double y1, double x2, double y2, double x3, double y3) {
        if (order == 0) {
            // Draw a triangle for the base case
            gc.strokePolygon(new double[]{x1, x2, x3}, new double[]{y1, y2, y3}, 3);
        } else {
            // Calculate midpoints of line segments
            double mx1 = (x1 + x2) / 2;
            double my1 = (y1 + y2) / 2;
            double mx2 = (x2 + x3) / 2;
            double my2 = (y2 + y3) / 2;
            double mx3 = (x3 + x1) / 2;
            double my3 = (y3 + y1) / 2;

            // Recursively draw smaller triangles
            drawSierpinskiTriangle(order - 1, x1, y1, mx1, my1, mx3, my3);
            drawSierpinskiTriangle(order - 1, mx1, my1, x2, y2, mx2, my2);
            drawSierpinskiTriangle(order - 1, mx3, my3, mx2, my2, x3, y3);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}