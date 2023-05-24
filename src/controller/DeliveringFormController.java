package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import model.payDeliver;
import view.tm.payDeliverTM;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class DeliveringFormController {
    public AnchorPane root;
    public TableView tblDelivering;
    public TableColumn colOrderID;
    public TableColumn colItemID;
    public TableColumn colCusID;
    public TableColumn colOrderDate;
    public TableColumn colOrderTime;
    public TableColumn colQTY;
    public TableColumn colPrice;
    public TableColumn colCost;
    public TableColumn colStatus;

    public JFXTextField txtStatus;
    public JFXComboBox<String> cmbStatus;
    public JFXTextField txtOrderID;
    public JFXTextField txtItemID;
    public JFXTextField txtOrderDate;
    public JFXTextField txtOrderTime;
    public JFXTextField txtqty;
    public JFXTextField txtprice;
    public JFXTextField txtstatus;
    public JFXTextField txtCost;
    public TextField txtCustomerID;
    String setStatus;


    public void initialize(){
        setTable();
        cmbStatus.getItems().addAll(
                "Delivered",
                "Delivering",
                "Payed"
        );
        cmbStatus.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setStatus= (String) newValue;
        });


    }
    void setTable(){
        colOrderID.setCellValueFactory(new PropertyValueFactory<>("orderid"));
        colCusID.setCellValueFactory(new PropertyValueFactory<>("cusid"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("orderdate"));
        colOrderTime.setCellValueFactory(new PropertyValueFactory<>("ordertime"));
        colCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        try {
            setItem(new OrderController().getAllOrder());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void setItem(ArrayList<payDeliver> orderDetail){

        ObservableList<payDeliverTM> oblist= FXCollections.observableArrayList();
        orderDetail.forEach(e->{  oblist.add(new payDeliverTM(
                e.getOrderid(),e.getCusid(),e.getOrderdate(),e.getOrdertime(),e.getCost(),e.getStatus()));


        });
        tblDelivering.setItems(oblist);

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (new DeliveringController().deleteOrder(txtCustomerID.getText())){
            new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure Deleted....").showAndWait();

        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
        setTable();

        tblDelivering.refresh();


    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        payDeliver p1= new payDeliver(txtOrderID.getText(),txtCustomerID.getText(),txtOrderDate.getText(),txtOrderTime.getText()
                ,Double.parseDouble(txtCost.getText()),setStatus
        );

        if (new DeliveringController().updateItem(p1)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();
        }else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
        setTable();

        tblDelivering.refresh();
    }

    public void btnSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String itemID=txtCustomerID.getText();

        payDeliver p1=new DeliveringController().searchOrder(itemID);

        if (p1==null){
            new Alert(Alert.AlertType.WARNING,"Empty Result Set..").show();
        }else {
            txtOrderID.setText(p1.getOrderid());
            txtCustomerID.setText(p1.getCusid());
            txtOrderDate.setText(p1.getOrderdate());
            txtOrderTime.setText(p1.getOrdertime());
            txtCost.setText(String.valueOf(p1.getCost()));
            cmbStatus.setValue(p1.getStatus());
        }

    }

    public void textFields_Key_Released(KeyEvent keyEvent) {
        Pattern pricePatten=Pattern.compile("^(C-)[0-9]{4}$");
        String typeSalary = txtCustomerID.getText();
        if (pricePatten.matcher(typeSalary).matches()){
            txtCustomerID.getParent().setStyle("\"-fx-border-color: green\"");
        }else {
            txtCustomerID.getParent().setStyle("-fx-border-color: red");
            txtCustomerID.requestFocus();
        }
    }
}
