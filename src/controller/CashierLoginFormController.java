package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import db.DbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CashierLoginFormController {

    public JFXTextField txtUserName;
    public JFXPasswordField txtPassWord;
    public AnchorPane root;
    public Label lblUserName;
    public Label lblPassword;

    public void initialize(){
        lblUserName.setVisible(false);
        lblPassword.setVisible(false);
    }

    public void LoginOnAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {



        String username = txtUserName.getText();
        String password=txtPassWord.getText();

        Connection connection = DbConnection.getInstance().getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("select * from User where Name = ? and Password = ?");

        preparedStatement.setObject(1,username);
        preparedStatement.setObject(2,password);

        ResultSet set = preparedStatement.executeQuery();

        boolean next = set.next();

        if (next){
            txtUserName.setStyle("-fx-border-color: transparent");
            txtPassWord.setStyle("-fx-border-color: transparent");

            lblUserName.setVisible(false);
            lblPassword.setVisible(false);

            new Alert(Alert.AlertType.CONFIRMATION,"Login Cashier Dashboard....").showAndWait();

            Parent parent= FXMLLoader.load(this.getClass().getResource("../view/CashierDashBoard.fxml"));
            Scene scene=new Scene(parent);

            Stage primaryStage=(Stage)this.root.getScene().getWindow();

            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
        }else {
             txtUserName.setStyle("-fx-border-color: red");
             txtPassWord.setStyle("-fx-border-color: red");

             lblUserName.setVisible(true);
             lblPassword.setVisible(true);

             new Alert(Alert.AlertType.WARNING,"Invalid User Name Or Password...").showAndWait();

             txtUserName.clear();
             txtPassWord.clear();

             txtUserName.requestFocus();
        }


    }

    public void AdminLoginFormOnMouseClicked(MouseEvent mouseEvent) throws IOException {

        Parent parent= FXMLLoader.load(this.getClass().getResource("../view/logingForm.fxml"));
        Scene scene=new Scene(parent);

        Stage primaryStage=(Stage)this.root.getScene().getWindow();

        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();

    }

}

