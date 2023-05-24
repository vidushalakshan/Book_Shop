package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;


public class CashierDashBoardController {

    public void initialize(){
        try {
            loadUi("CashierHomeForm");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AnchorPane root;
    public AnchorPane context;


    public void CustomerOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("Customer");
    }

    public void OrderOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("OrderForm");
    }


    public void DeliveringOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("DiliveringForm");
    }


    public void DashBoardOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("CashierHomeForm");
    }

    void loadUi(String fileName) throws IOException {
        URL resource = getClass().getResource("../view/"+fileName+".fxml");
        Parent load =FXMLLoader.load(resource);
        context.getChildren().clear();
        context.getChildren().add(load);
    }

    public void LogoutOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Log Out");
        alert.setHeaderText("You are about to logout !");
        alert.setContentText("Do you want to save before exiting");
        if(alert.showAndWait().get()== ButtonType.OK){
            Stage stage = (Stage) root.getScene().getWindow();
            Parent parent= FXMLLoader.load(this.getClass().getResource("../view/CashierLoginForm.fxml"));
            Scene scene=new Scene(parent);

            Stage primaryStage=(Stage)this.root.getScene().getWindow();

            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
            stage.close();
        }
    }
}
