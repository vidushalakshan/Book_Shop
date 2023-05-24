package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import db.DbConnection;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class logingFormController {
    public JFXTextField txtUserName;
    public JFXPasswordField txtPassWord;
    public AnchorPane root;

    public void LoginFormOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        Parent parent= FXMLLoader.load(this.getClass().getResource("../view/CashierLoginForm.fxml"));
        Scene scene=new Scene(parent);

        Stage primaryStage=(Stage)this.root.getScene().getWindow();

        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
    }

    public void AdminLoginOnAction(ActionEvent actionEvent) throws IOException {
        if (txtUserName.getText().equals("admin") && txtPassWord.getText().equals("2021")) {
            txtUserName.setStyle("-fx-border-color: transparent");
            txtPassWord.setStyle("-fx-border-color: transparent");
            new Alert(Alert.AlertType.CONFIRMATION,"Login Cashier Dashboard....").showAndWait();
            Parent parent = FXMLLoader.load(this.getClass().getResource("../view/adminDashBoardForm.fxml"));
            Scene scene = new Scene(parent);

            Stage primaryStage = (Stage) this.root.getScene().getWindow();

            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
        } else {
            txtUserName.setStyle("-fx-border-color: red");
            txtPassWord.setStyle("-fx-border-color: red");
            txtUserName.requestFocus();
            new Alert(Alert.AlertType.WARNING, "Please Check Your Username Or Password !", ButtonType.CLOSE).show();
        }
    }
}
