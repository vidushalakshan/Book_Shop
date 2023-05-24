package controller;

import com.jfoenix.controls.JFXButton;
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
import model.Customer;
import util.validationUtil;
import view.tm.CustomerTM;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class CustomerFormController {
    public AnchorPane root;
    public TableView tblCustomer;
    public TableColumn colCusID;
    public TableColumn colName;
    public TableColumn colNIC;
    public TableColumn colMobileNumber;
    public TextField txtCusID;
    public TextField txtName;
    public TextField txtNIC;
    public TextField txtMobileNumber;
    public JFXButton btnAddClient;
    LinkedHashMap<TextField,Pattern> map=new LinkedHashMap<>();

    Pattern cusPatten=Pattern.compile("^(C-)[0-9]{4}$");
    Pattern namePatten=Pattern.compile("^[A-z ]{3,20}$");
    Pattern nicPatten=Pattern.compile("^[0-9]{10,14}(V)?$");
    Pattern telephonePatten=Pattern.compile("^(077|074|075|078|076|071)[-]?[0-9]{7}$");


    public void initialize() {
       customer();
       validateInit();
    }
    private void validateInit() {
        btnAddClient.setDisable(true);
        map.put(txtCusID, cusPatten);
        map.put(txtName, namePatten);
        map.put(txtNIC,nicPatten);
        map.put(txtMobileNumber,telephonePatten);

    }

    void customer(){
        try {

            colCusID.setCellValueFactory(new PropertyValueFactory<>("id"));
            colName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
            colMobileNumber.setCellValueFactory(new PropertyValueFactory<>("Mobilenum"));


            setItemToTable(new CustomerController().getAllCustomer());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    private void setItemToTable (ArrayList<Customer> customers){

        ObservableList<CustomerTM> oblist= FXCollections.observableArrayList();
        customers.forEach(e->{  oblist.add(new CustomerTM(
                e.getId(),e.getName(),e.getNic(),e.getMobilenum()));


        });
        tblCustomer.setItems(oblist);

    }


    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (new CustomerController().deleteICustomer(txtCusID.getText())){
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
        customer();
        clear();
    }

    public void btnSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String CusID=txtCusID.getText();

        Customer c1=new CustomerController().searchCustomer(CusID);

        if (c1==null){
            new Alert(Alert.AlertType.WARNING,"Empty Result Set..").show();
        }else {
            setData(c1);
        }
    }

    public void btnAddClientOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Customer c1=new Customer(
                txtCusID.getText(),txtName.getText(),txtNIC.getText(),txtMobileNumber.getText()
        );
        if (new CustomerController().saveCustomer(c1))
            new Alert(Alert.AlertType.CONFIRMATION,"Item Saved....").show();
        else
            new Alert(Alert.AlertType.WARNING,"Try Again......").show();

        customer();
        clear();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Customer c1= new Customer(
                txtCusID.getText(),txtName.getText(),
                txtMobileNumber.getText(),txtNIC.getText()

        );

        if (new CustomerController().updateCustomer(c1))
            new Alert(Alert.AlertType.CONFIRMATION,"Updated..").show();
        else
            new Alert(Alert.AlertType.WARNING,"Try Again").show();

        customer();
        clear();
        tblCustomer.refresh();
    }

    void clear(){
        txtCusID.clear();
        txtName.clear();
        txtMobileNumber.clear();
        txtNIC.clear();
    }

    void setData(Customer c){
        txtCusID.setText(c.getId());
        txtName.setText(c.getName());
        txtMobileNumber.setText(c.getMobilenum());
        txtNIC.setText(c.getNic());
    }

    



    public void textFields_Key_Released(KeyEvent keyEvent) {
        Object response = validationUtil.validate(map,btnAddClient);

        if (response instanceof Boolean) {
            btnAddClient.setDisable(false);
        }

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField textField = (TextField) response;
                textField.requestFocus();
            }
        }
    }
}
