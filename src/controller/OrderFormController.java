package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import model.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import util.validationUtil;
import view.tm.CartTM;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;
import java.util.regex.Pattern;

public class OrderFormController {
    public AnchorPane root;
    public TableColumn colItem_ID;
    public TableColumn colCategories;
    public TableColumn colQTY;
    public TableColumn colUnitePrice;
    public TableColumn colDiscount;
    public TableColumn colCost;
    public JFXComboBox<String> cmbCus_ID;
    public JFXComboBox<String> cmbItem_ID;
    public Label lblOrder;
    public Label lblTotal;
    public Label lblTime;
    public Label lblDate;
    public TableView<CartTM> tblCart;
    public TableColumn colCustomerID;
    public TextField txtQTYOnHand;
    public TextField txtCategories;
    public TextField txtCustomerName;
    public TextField txtItemName;
    public TextField txtPrice;
    public TextField txtDiscount;
    public TextField txtQty;
    public JFXButton btnAddToCart;
    public JFXComboBox<String> cmbPayMethod;
    String customerID;
    String itemID;
    String payID;
    LinkedHashMap<TextField,Pattern> map=new LinkedHashMap<>();

    Pattern discountPatten=Pattern.compile("^[0-9]*([.][0-9]{1})$");
    Pattern qtyPatten=Pattern.compile("^[0-9]{1,4}$");


    int CartRemove = -1;

    public void initialize(){
        colItem_ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCustomerID.setCellValueFactory(new PropertyValueFactory<>("cusid"));
        colCategories.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitePrice.setCellValueFactory(new PropertyValueFactory<>("unitePrice"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colCost.setCellValueFactory(new PropertyValueFactory<>("cost"));



        try {
            loadItemID();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            loadCustomerID();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        cmbItem_ID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setItemData(newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        cmbCus_ID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setCustomerData(newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        cmbPayMethod.getItems().addAll(
                "Payed",
                "Delivering"
        );

        loadDateAndTime();


        tblCart.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            CartRemove= (int) newValue;
        });

        setOrderID();

        cmbCus_ID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            customerID= (String) newValue;
        });

        cmbItem_ID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            itemID= (String) newValue;
        });

        cmbPayMethod.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            payID= (String) newValue;
        });

        validateInit();

    }

    private void validateInit() {
        btnAddToCart.setDisable(true);
        map.put(txtDiscount, discountPatten);
        map.put(txtQty,qtyPatten );
    }

    private Label setOrderID() {
        try {
            lblOrder.setText(new OrderController().oderID());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return lblOrder;
    }

    private void loadItemID() throws SQLException, ClassNotFoundException {
        List<String> ItemIDS = new ItemController().getItemIds() ;
        cmbItem_ID.getItems().addAll(ItemIDS);
    }

    private void setItemData(String itemID) throws SQLException, ClassNotFoundException {
        Item e1=new ItemController().searchItem(itemID);
        if (e1==null){
            new Alert(Alert.AlertType.WARNING,"Empty Result Set ....").show();
        }else {
            txtItemName.setText(e1.getName());
            txtQTYOnHand.setText(e1.getQtyonhand());
            txtCategories.setText(e1.getCategorie());
            txtPrice.setText(String.valueOf(e1.getPrice()));
        }
    }

    private void setCustomerData(String customerID) throws SQLException, ClassNotFoundException {
        Customer e1=new CustomerController().searchCustomer(customerID);
        if (e1==null){
            new Alert(Alert.AlertType.WARNING,"Empty Result Set ....").show();
        }else {
            txtCustomerName.setText(e1.getName());
        }
    }

    private void loadCustomerID() throws SQLException, ClassNotFoundException {
        List<String> CustomerIDS = new CustomerController().getCustomerIDS();
        cmbCus_ID.getItems().addAll(CustomerIDS);
    }

    private void loadDateAndTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));

        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            lblTime.setText(
                    currentTime.getHour() + " : " + currentTime.getMinute() +
                            " : " + currentTime.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }





    ObservableList<CartTM> oblist= FXCollections.observableArrayList();

    public void btnAddOrderOnAction(ActionEvent actionEvent) {
        String categorie=txtCategories.getText();
        int qtyonhand=Integer.parseInt(txtQTYOnHand.getText());
        int qty=Integer.parseInt(txtQty.getText());
        double unitePrice=Double.parseDouble(txtPrice.getText());
        double discount = Double.parseDouble(txtDiscount.getText());
        double total=qty*unitePrice*((100-discount)/100);

        if (qtyonhand<=qty){
            new Alert(Alert.AlertType.WARNING,"Invalid QTY...").show();
            return;
        }


        CartTM tm=new CartTM(
              cmbItem_ID.getValue(),
                cmbCus_ID.getValue(),
                categorie,
                qty,
                unitePrice,
              discount,
              total
        );
        int rowNumber=isExists(tm);

        if (isExists(tm)==-1){
            oblist.add(tm);
        }else {

           CartTM temp=oblist.get(rowNumber);
           CartTM newTm=new CartTM(
                   temp.getId(),
                   temp.getCusid(),
                   temp.getCategorie(),
                   temp.getQty()+qty,
                   temp.getUnitePrice(),
                   temp.getDiscount(),
                   total+temp.getCost()
           );
            if (qtyonhand<=temp.getQty()){
                new Alert(Alert.AlertType.WARNING,"Invalid QTY...").show();
                return;
            }

           oblist.remove(rowNumber);
           oblist.add(newTm);
        }
        tblCart.setItems(oblist);
        calculateCost();


    }




    public void btnPayOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        ArrayList<OrderDetails> items=new ArrayList<>();
        double total=0;
        for (CartTM tempTm:oblist
        ){
            total+=tempTm.getCost();
            items.add(
                    new OrderDetails(
                            tempTm.getId(),
                            tempTm.getUnitePrice(),
                            tempTm.getQty()
                    )
            );
        }
        Order order=new Order(
                lblOrder.getText(),
                cmbCus_ID.getValue(),
                lblDate.getText(),
                lblTime.getText(),
                total,
                cmbPayMethod.getValue(),
                items

        );


        if (new OrderController().payOrder(order)){
            new Alert(Alert.AlertType.CONFIRMATION,"success...").show();
            setOrderID();
        }else {
            new Alert(Alert.AlertType.WARNING,"Try Again..").show();
        }


    }



    private int isExists(CartTM tm){
        for (int i = 0; i < oblist.size(); i++) {
            if (tm.getId().equals(oblist.get(i).getId())){
                return i;
            }
        }
        return -1;
    }

    void calculateCost(){
        double ttl=0;
        for (CartTM tm:oblist
        ){
            ttl+=tm.getCost();
        }
        lblTotal.setText(ttl+"  /=");
    }

    public void btnRemoveOnAction(ActionEvent actionEvent) {
        if (CartRemove==-1){
            new Alert(Alert.AlertType.WARNING,"Please Select a Row").show();
        }else {
            oblist.remove(CartRemove);
            calculateCost();
            tblCart.refresh();
        }
    }


    public void report(MouseEvent mouseEvent) {
        try {
            JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("/view/reports/billPay.jrxml"));
            JasperReport compileReport = JasperCompileManager.compileReport(design);
            ObservableList<CartTM> items = tblCart.getItems();

            String orderid = lblOrder.getText();
            String total = lblTotal.getText();

            HashMap map=new HashMap();
            map.put("OrderID",orderid);
            map.put("Total",total);

            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, map, new JRBeanArrayDataSource(items.toArray()));
            JasperViewer.viewReport(jasperPrint,false);

        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    public void textFields_Key_Released(KeyEvent keyEvent) {
        Object response = validationUtil.validate(map,btnAddToCart);

        if (response instanceof Boolean) {
           btnAddToCart.setDisable(false);
        }

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField textField = (TextField) response;
                textField.requestFocus();
            }
        }
    }




    public void btnResertOnAction(ActionEvent actionEvent) {
        cmbCus_ID.getSelectionModel().clearSelection();
        cmbItem_ID.getSelectionModel().clearSelection();
        txtPrice.clear();
        txtCategories.clear();
        txtQTYOnHand.clear();
        txtItemName.clear();
        txtCustomerName.clear();
        txtDiscount.clear();
        txtQty.clear();
    }
}
