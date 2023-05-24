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

public class adminDashBoardFormController {

    public void initialize(){
        try {
            loadUi("AdminHomeForm");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


   public AnchorPane root;
    public AnchorPane context;

    public void UserOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("UserForm");
    }

    public void ItemsOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("addItemForm");
    }

    public void DashboardOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("AdminHomeForm");
    }

    public void SalaryOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("EmpSalaryForm");
    }

    public void EmployeeOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("EmployeeForm");

    }
    public void LogoutOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Log Out");
        alert.setHeaderText("You are about to logout !");
        alert.setContentText("Do you want to save before exiting");
        if(alert.showAndWait().get()== ButtonType.OK){
            Stage stage = (Stage) root.getScene().getWindow();
            Parent parent= FXMLLoader.load(this.getClass().getResource("../view/logingForm.fxml"));
            Scene scene=new Scene(parent);

            Stage primaryStage=(Stage)this.root.getScene().getWindow();

            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
            stage.close();
        }
    }



    public void PaymentOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("PaymentForm");
    }


    void loadUi(String fileName) throws IOException {
        URL resource = getClass().getResource("../view/"+fileName+".fxml");
        Parent load =FXMLLoader.load(resource);
        context.getChildren().clear();
        context.getChildren().add(load);
    }

}
