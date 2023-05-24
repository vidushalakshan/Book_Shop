package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import model.Employee;
import util.validationUtil;
import view.tm.EmployeeTM;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class EmployeeFormController {

    public AnchorPane root;
    public JFXComboBox cmbRole;
    public JFXComboBox cmbGender;
    public TableView tblEmployee;
    public TableColumn colEmpID;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colEmail;
    public TableColumn colMobileNo;
    public TableColumn colRole;
    public TableColumn colGender;
    public TextField txtEmpID;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtEmail;
    public TextField txtMobileNO;
    public JFXButton btnAddEmployee;
    String Gender;
    String Role;
    LinkedHashMap<TextField,Pattern> map=new LinkedHashMap<>();

    Pattern empidPatten=Pattern.compile("^(E-)[0-9]{4}$");
    Pattern namePatten=Pattern.compile("^[A-z ]{3,20}$");
    Pattern addressPatten=Pattern.compile("^[A-z0-9/ ]{6,50}$");
    Pattern emailPatten=Pattern.compile("^[A-z0-9]{2,40}(@gmail.com)$");
    Pattern telephonePatten=Pattern.compile("^(077|074|075|078|076|071)[-]?[0-9]{7}$");


    public void initialize(){
             emp();


            cmbGender.getItems().addAll(
                    "Female",
                    "Male",
                    "Custome"
            );

            cmbRole.getItems().addAll(
                    "Cashier",
                    "Diliver",
                    "Employee",
                    "Admin"

            );


            cmbGender.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                Gender= (String) newValue;
            });

            cmbRole.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            Role= (String) newValue;
            });

            validateInit();


    }
    private void validateInit() {
        btnAddEmployee.setDisable(true);
        map.put(txtEmpID, empidPatten);
        map.put(txtName, namePatten);
        map.put(txtAddress,addressPatten);
        map.put(txtEmail,emailPatten);
        map.put(txtMobileNO,telephonePatten);
    }

    void emp(){
        try {

            colEmpID.setCellValueFactory(new PropertyValueFactory<>("id"));
            colName.setCellValueFactory(new PropertyValueFactory<>("neme"));
            colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
            colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            colMobileNo.setCellValueFactory(new PropertyValueFactory<>("mobileno"));
            colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
            colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));



            setItemToTable(new EmployeeController().getAllEmployee());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void setItemToTable (ArrayList<Employee> employees){

        ObservableList<EmployeeTM> oblist= FXCollections.observableArrayList();
        employees.forEach(e->{  oblist.add(new EmployeeTM(
                e.getId(),e.getNeme(),e.getAddress(),e.getEmail(),e.getMobileno(),e.getRole(),e.getGender()));


        });
        tblEmployee.setItems(oblist);

    }



    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Employee u1= new Employee(
                txtEmpID.getText(),txtName.getText(),
                txtAddress.getText(),txtEmail.getText(),txtMobileNO.getText(),Role,Gender

        );

        if (new EmployeeController().updateEmployee(u1))
            new Alert(Alert.AlertType.CONFIRMATION,"Updated..").show();
        else
            new Alert(Alert.AlertType.WARNING,"Try Again").show();

        emp();
        clear();
        tblEmployee.refresh();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (new EmployeeController().deleteEmployee(txtEmpID.getText())){
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
        emp();
        clear();

    }

    public void btnSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String EmpID=txtEmpID.getText();

        Employee e1=new EmployeeController().searchItem(EmpID);

        if (e1==null){
            new Alert(Alert.AlertType.WARNING,"Empty Result Set..").show();
        }else {
            setData(e1);
        }
    }

    public void btnAddEmployeeOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Employee e1=new Employee(
                txtEmpID.getText(),txtName.getText(),txtAddress.getText(),txtEmail.getText(),txtMobileNO.getText(),Role,
                Gender
        );
        if (new EmployeeController().saveEmployee(e1))
            new Alert(Alert.AlertType.CONFIRMATION,"Item Saved....").show();
        else
            new Alert(Alert.AlertType.WARNING,"Try Again......").show();

        emp();
        clear();
    }

    void clear(){
        txtEmpID.clear();
        txtName.clear();
        txtAddress.clear();
        txtEmail.clear();
        txtMobileNO.clear();
        cmbRole.getSelectionModel().clearSelection();
        cmbGender.getSelectionModel().clearSelection();
    }

    void setData(Employee e){
        txtEmpID.setText(e.getId());
        txtName.setText(e.getNeme());
        txtAddress.setText(e.getAddress());
        txtEmail.setText(e.getEmail());
        txtMobileNO.setText(e.getMobileno());
        cmbRole.setValue(e.getRole());
        cmbGender.setValue(e.getGender());
    }

    public void textFields_Key_Released(KeyEvent keyEvent) {
        Object response = validationUtil.validate(map,btnAddEmployee);

        if (response instanceof Boolean) {
            btnAddEmployee.setDisable(false);
        }

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField textField = (TextField) response;
                textField.requestFocus();
            }
        }
    }

}
