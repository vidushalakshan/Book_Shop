package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Item;
import model.User;
import util.validationUtil;
import view.tm.ItemTM;
import view.tm.UserTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class UserFormController {

    public AnchorPane root;
    public TableColumn colUserID;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colEmail;
    public TableColumn colPhoneNumber;
    public TableColumn colGender;
    public TableColumn colPassword;
    public TableView tblUser;
    public TextField txtUserID;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtEmail;
    public TextField txtPhoneNumber;
    public TextField txtPassword;
    public JFXButton btnAddUser;

    LinkedHashMap<TextField,Pattern> map=new LinkedHashMap<>();

    Pattern userPatten=Pattern.compile("^(U-)[0-9]{4}$");
    Pattern namePatten=Pattern.compile("^[A-z ]{3,20}$");
    Pattern addressPatten=Pattern.compile("^[A-z0-9/ ]{6,50}$");
    Pattern emailPatten=Pattern.compile("^[A-z0-9]{2,40}(@gmail.com)$");
    Pattern telephonePatten=Pattern.compile("^(077|074|075|078|076|071)[-]?[0-9]{7}$");
    Pattern passwordPatten=Pattern.compile("^[A-z0-9/.,]{3,10}$");

    public void initialize() {
        btnAddUser.setDisable(true);
       user();
       validateInit();
    }

    void user(){
        try {

            colUserID.setCellValueFactory(new PropertyValueFactory<>("id"));
            colName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
            colEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
            colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("mobileno"));
            colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));


            setUserToTable(new UserController().getAllUser());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    private void validateInit() {
        btnAddUser.setDisable(true);
        map.put(txtUserID, userPatten);
        map.put(txtName, namePatten);
        map.put(txtAddress,addressPatten);
        map.put(txtEmail,emailPatten);
        map.put(txtPhoneNumber,telephonePatten);
        map.put(txtPassword,passwordPatten);
    }
    private void setUserToTable (ArrayList<User> users){

        ObservableList<UserTM> oblist= FXCollections.observableArrayList();
        users.forEach(e->{  oblist.add(new UserTM(
                e.getId(),e.getName(),e.getAddress(),e.getEmail(),e.getMobileno(),e.getPassword()));


        });
        tblUser.setItems(oblist);

    }



    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        User u1= new User(
                txtUserID.getText(),txtName.getText(),
                txtAddress.getText(),txtEmail.getText(),txtPhoneNumber.getText(),txtPassword.getText()

        );

        if (new UserController().updateUser(u1)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();
        }else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
        clear();
        user();
        tblUser.refresh();
    }


    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (new UserController().deleteUser(txtUserID.getText())){
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
        clear();
        user();
    }

    public void btnSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String UserID=txtUserID.getText();

        User u1=new UserController().searchUser(UserID);

        if (u1==null){
            new Alert(Alert.AlertType.WARNING,"Empty Result Set..").show();
        }else {
            setData(u1);
        }
    }

    public void btnAddUserOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        User u1=new User(
                txtUserID.getText(),txtName.getText(),txtAddress.getText(),txtEmail.getText(),txtPhoneNumber.getText(),
                txtPassword.getText()
        );
        if (new UserController().saveUser(u1)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Item Saved....").show();
        }else {
            new Alert(Alert.AlertType.WARNING, "Try Again......").show();
        }
        clear();
        user();
    }

    void clear(){
        txtUserID.clear();
        txtName.clear();
        txtAddress.clear();
        txtEmail.clear();
        txtPhoneNumber.clear();
        txtPassword.clear();
        txtPassword.clear();
    }

    void setData(User u){
        txtUserID.setText(u.getId());
        txtName.setText(u.getName());
        txtAddress.setText(u.getAddress());
        txtEmail.setText(u.getEmail());
        txtPhoneNumber.setText(u.getMobileno());
        txtPassword.setText(String.valueOf(u.getPassword()));
    }

    public void textFields_Key_Released(KeyEvent keyEvent) {
        Object response = validationUtil.validate(map,btnAddUser);

        if (response instanceof Boolean) {
            btnAddUser.setDisable(false);
        }

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField textField = (TextField) response;
                textField.requestFocus();
            }
        }
    }

}
