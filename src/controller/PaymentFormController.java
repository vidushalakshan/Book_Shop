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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.Pay;
import model.payDeliver;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import view.tm.CartTM;
import view.tm.payTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class PaymentFormController {
    public AnchorPane root;
    public TableView tblPayment;
    public TableColumn colOrderID;
    public TableColumn colItemID;
    public TableColumn colCustomerID;
    public TableColumn colOrderDate;
    public TableColumn colOrderTime;
    public TableColumn colQTY;
    public TableColumn colPrice;
    public TableColumn colCost;
    public JFXTextField txtOrderID;
    public JFXTextField txtItemID;
    public JFXTextField txtOrderDate;
    public JFXTextField txtOrderTime;
    public JFXTextField txtqty;
    public JFXTextField txtprice;
    public JFXTextField txtCost;
    public JFXTextField txtStatus;
    public TextField txtCustomerID;


    public void initialize(){
        setPayed();
    }

    private void setPayed() {
        colOrderID.setCellValueFactory(new PropertyValueFactory<>("itemid"));
        colItemID.setCellValueFactory(new PropertyValueFactory<>("orderid"));
        colCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerid"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colOrderTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colCost.setCellValueFactory(new PropertyValueFactory<>("cost"));

        try {
            setItemToTable(new OrderController().getAllPay());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setItemToTable (ArrayList<Pay> orderDetail){

        ObservableList<payTM> oblist= FXCollections.observableArrayList();
        orderDetail.forEach(e->{  oblist.add(new payTM(
                e.getOrderid(),e.getItemid(),e.getCusid(),e.getOrderdate(),e.getOrdertime(),e.getQty(),e.getPrice(),e.getCost(),e.getPaymethod()));


        });
        tblPayment.setItems(oblist);

    }

    public void btnRemoveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (new DeliveringController().deleteOrder(txtCustomerID.getText())){
            new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure Deleted....").showAndWait();

        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
        setPayed();
        tblPayment.refresh();
    }


    public void btnSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String itemID=txtCustomerID.getText();

        payDeliver p1=new paymentController().searchOrder(itemID);

        if (p1==null){
            new Alert(Alert.AlertType.WARNING,"Empty Result Set..").show();
        }else {
            txtOrderID.setText(p1.getOrderid());
            txtCustomerID.setText(p1.getCusid());
            txtOrderDate.setText(p1.getOrderdate());
            txtOrderTime.setText(p1.getOrdertime());
            txtCost.setText(String.valueOf(p1.getCost()));
            txtStatus.setText(p1.getStatus());
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

    public void report(MouseEvent mouseEvent) throws JRException {
        JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("/view/reports/payed.jrxml"));
        JasperReport compileReport = JasperCompileManager.compileReport(design);
        ObservableList<payTM> items = tblPayment.getItems();
        JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null, new JRBeanArrayDataSource(items.toArray()));
        JasperViewer.viewReport(jasperPrint,false);
    }
}
