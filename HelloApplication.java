package com.example.calculator;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class HelloApplication extends Application {


    private TextField display;
    private final ArrayList<String> input = new ArrayList<>();
    private double memoryValue = 0;
    private ListView<String> listView;
    private ObservableList<String> items;
    private String operator = null;

    

    @Override
    public void start(Stage stage) throws IOException {

//        Menu
        MenuBar menu = new MenuBar();
        Menu editItem = new Menu("Edit");
        Menu viewItem = new Menu("View");
        Menu helpItem = new Menu("Help");
        menu.getMenus().addAll(editItem, viewItem, helpItem);
//        set color of menubar to gray
        menu.setBackground(new Background(new BackgroundFill(Color.GRAY,  CornerRadii.EMPTY, Insets.EMPTY)));


//        input items
        listView = new ListView<>();
        items = FXCollections.observableArrayList();
        listView.setItems(items);


//        vbox
        var vbox = new VBox();

//        h boxes
        var hBox1 = new HBox();

//        h box 1 holds text area
        display = new TextField();
        display.setEditable(false);



        var hBox2 = new HBox();
//        3 buttons backspace, CE, C
        Button backspaceButton = new Button("Backspace"); backspaceButton.setMinWidth(100);
        backspaceButton.setOnAction(e -> {
            String currentText = display.getText();
            if (!currentText.isEmpty()) {
                String newText = currentText.substring(0, currentText.length() - 1);
                display.setText(newText);
            }
        });
        Button ceButton = new Button("CE"); ceButton.setMinWidth(100);
        Button cButton = new Button("C"); cButton.setMinWidth(100);
        cButton.setOnAction(e -> display.clear());

        var hBox3 = new HBox();
//        6 buttons MC, 7, 8, 9, percentage(/), sqrt
        Button mcButton = new Button("MC"); mcButton.setMinWidth(50);
        mcButton.setOnAction(e -> {
            memoryValue = 0.0;
            input.clear();
        });
        Button sevenButton = new Button("7"); sevenButton.setMinWidth(50);
        sevenButton.setOnAction(e -> display.appendText("7"));
        Button eighgtButton = new Button("8"); eighgtButton.setMinWidth(50);
        eighgtButton.setOnAction(e -> display.appendText("8"));
        Button nineButton = new Button("9"); nineButton.setMinWidth(50);
        nineButton.setOnAction(e -> display.appendText("9"));
        Button divideButton = new Button("/"); divideButton.setMinWidth(50);
        Button sqrtButton = new Button("sqrt"); sqrtButton.setMinWidth(50);


        var hBox4 = new HBox();
//        6 buttons MR, 4, 5, 6, multiply(*), percentage(%)
        Button mrButton = new Button("MR"); mrButton.setMinWidth(50);
        mrButton.setOnAction(e -> {
            if (input.isEmpty()){
                display.clear();
            }
            else{
                try {
                    memoryValue = Double.parseDouble(input.get(input.size() - 1));
                } catch (NumberFormatException a) {
                    return;
                }
                display.setText(String.valueOf(memoryValue));
            }
        });
        Button fourButton = new Button("4"); fourButton.setMinWidth(50);
        fourButton.setOnAction(e -> display.appendText("4"));
        Button fiveButton = new Button("5"); fiveButton.setMinWidth(50);
        fiveButton.setOnAction(e -> display.appendText("5"));
        Button sixButton = new Button("6"); sixButton.setMinWidth(50);
        sixButton.setOnAction(e -> display.appendText("6"));
        Button multiplyButton = new Button("*"); multiplyButton.setMinWidth(50);
        Button percentageButton = new Button("%"); percentageButton.setMinWidth(50);


        var hBox5 = new HBox();
//        6 buttons MS, 1, 2, 3, period(.), inverse(1/x)
        Button msButton = new Button("MS"); msButton.setMinWidth(50);
        msButton.setOnAction(e -> {
            try {
                input.add(display.getText());
                memoryValue = Double.parseDouble(input.get(input.size() - 1));
            } catch (NumberFormatException a) {
                return;
            }
            display.setText(input.get(input.size() - 1));
        });
        Button oneButton = new Button("1"); oneButton.setMinWidth(50);
        oneButton.setOnAction(e -> {
            display.appendText("1");});
        Button twoButton = new Button("2");twoButton.setMinWidth(50);
        twoButton.setOnAction(e -> display.appendText("2"));
        Button threeButton = new Button("3"); threeButton.setMinWidth(50);
        threeButton.setOnAction(e -> display.appendText("3"));
        Button periodButton = new Button("."); periodButton.setMinWidth(50);
        periodButton.setOnAction(e -> display.appendText("."));
        Button inverseButton = new Button("1/x"); inverseButton.setMinWidth(50);


        var hBox6 = new HBox();
//        6 buttons M+, 0, changeSign(+/-), comma(,), plus(+), solve(=)
        Button mplusButton = new Button("M+"); mplusButton.setMinWidth(50);
        mplusButton.setOnAction(e -> {
            double currentText = 0;
            try {
                if (input.isEmpty()){
                    currentText = Double.parseDouble(display.getText());
                }
                else{
                    currentText = Double.parseDouble(display.getText()) + memoryValue;
                }
            } catch (NumberFormatException a) {
                return;
            }
            display.setText(String.valueOf(currentText));
        });
        Button zeroButton = new Button("0"); zeroButton.setMinWidth(50);
        zeroButton.setOnAction(e -> display.appendText("0"));
        Button changeSignButton = new Button("+/-"); changeSignButton.setMinWidth(50);
        Button commaButton = new Button(","); commaButton.setMinWidth(50);
        commaButton.setOnAction(e -> display.appendText(","));
        Button plusButton = new Button("+"); plusButton.setMinWidth(50);
        Button solveButton = new Button("=");solveButton.setMinWidth(50);


//        Add menu and h boxes to v box
        vbox.getChildren().add(menu);
        vbox.getChildren().addAll(hBox1, hBox2, hBox3, hBox4, hBox5, hBox6);


//        set min & max stage height and width
        stage.setMinHeight(280);
        stage.setMinWidth(420);
        stage.setMaxHeight(280);
        stage.setMaxWidth(420);


//        Scene
        var scene = new Scene(vbox);


//        Display & Spacing
        display.prefWidthProperty().bind(scene.widthProperty());

//        spacing for text box
        HBox.setMargin(display, new Insets(10, 10, 10, 10));
        hBox1.getChildren().add(display);

//        backspace button spacing
        HBox.setMargin(backspaceButton, new Insets(0, 10, 10, 80));
        hBox2.getChildren().add(backspaceButton);

//        Spacing for h box 2
        Insets buttonInsets1 = new Insets(0, 0, 10, 10);
        for (Button button2 : new Button[]{ceButton, cButton}){
            HBox.setMargin(button2, buttonInsets1);
            hBox2.getChildren().addAll(button2);
        }

//        spacing for remaining h boxes
        Insets buttonInsets = new Insets(0, 10, 10, 10);
        for (Button button3 : new Button[]{mcButton, sevenButton, eighgtButton, nineButton, divideButton, sqrtButton}){
            HBox.setMargin(button3, buttonInsets);
            hBox3.getChildren().addAll(button3);
        }
        for (Button button4 : new Button[]{mrButton, fourButton, fiveButton, sixButton, multiplyButton, percentageButton}){
            HBox.setMargin(button4, buttonInsets);
            hBox4.getChildren().addAll(button4);
        }
        for (Button button5 : new Button[]{msButton, oneButton, twoButton, threeButton, periodButton, inverseButton}){
            HBox.setMargin(button5, buttonInsets);
            hBox5.getChildren().addAll(button5);
        }
        for (Button button6 : new Button[]{mplusButton, zeroButton, changeSignButton, commaButton, plusButton, solveButton}){
            HBox.setMargin(button6, buttonInsets);
            hBox6.getChildren().addAll(button6);
        }


//        Stage
        stage.setScene(scene);
        stage.show();

        //        perform function
        Button[] buttons = new Button[]{plusButton, multiplyButton, changeSignButton,
                sqrtButton, divideButton, percentageButton, inverseButton};

        for (Button button : buttons){
            button.setOnAction(e -> {
                 String inputText = display.getText().trim();
                if (!inputText.isEmpty()) {
                    items.add(inputText);
                }
                display.clear();
                handleButtonClick(((Button) e.getSource()).getText());
            });
        }
        solveButton.setOnAction(e -> solveEquation(display.getText()));
    }
    private void handleButtonClick(String buttonText) {
        ObservableList<String> value = listView.getItems();
        double operand1 = Double.parseDouble(items.get(0));
        double result = 0.0;

        switch (buttonText) {
            case "+/-":
                String currentText = String.valueOf(operand1);
                if (!currentText.isEmpty() && !currentText.equals("0")) {
                    // Toggle the sign by checking if the first character is '-'
                    if (!(currentText.charAt(0) == '-')) {
                        display.setText("-" + currentText);
                    } else {
                        display.setText(currentText.substring(1));
                    }
                }
                break;
            case "+":
                operator = operand1 + "+";
                display.clear();
                break;
            case "*":
                operator = operand1 + "*";
                break;
            case "/":
                operator = operand1 + "/";
                break;
            case "sqrt":
                result = Math.sqrt(operand1);
                display.setText(String.valueOf(result));
                break;
            case "1/x":
                if (operand1 != 0) {
                    result = 1 / operand1;
                    display.setText(String.valueOf(result));
                }
                if (operand1 == 0){
                    display.setText("Math ERROR");
                }
                break;
            case "%":
                result = operand1 / 100;
                display.setText(String.valueOf(result));
                break;

        }
        // Clear the ListView
        value.clear();
    }
     private void solveEquation(String userInput){
         StringBuilder stringBuilder = new StringBuilder();
         for (int i = 0; i < operator.length(); i++){
             if(!((operator.charAt(i) == '+') || (operator.charAt(i) == '*') ||(operator.charAt(i) == '/'))){
                 stringBuilder.append(operator.charAt(i));
             }
         }
         double operand1 = Double.parseDouble(stringBuilder.toString());
         double operand2 = Double.parseDouble(userInput);
         double result = 0.0;
         boolean valid = true;

         for (int i = 0; i < operator.length(); i++) {
             if (operator.charAt(i) == '+') {
                 result = operand1 + operand2;
             }
             if (operator.charAt(i) == '*') {
                 result = operand1 * operand2;
             }
             if (operator.charAt(i) == '/') {
                 if (operand2 == 0) {
                     valid = false;
                 }
                 result = operand1 / operand2;
             }
         }
         if (!valid){
             display.setText("Math ERROR");
         }
         else{
             display.setText(String.valueOf(result));
         }
     }


    public static void main(String[] args) {
        launch();
    }
}