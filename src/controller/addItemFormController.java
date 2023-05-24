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
import model.Item;
import util.validationUtil;
import view.tm.ItemTM;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;


public class addItemFormController {

    public AnchorPane root;
    public TableColumn colItemID;
    public TableColumn colName;
    public TableColumn colQtyOnHand;
    public TableColumn colCategorie;
    public TableColumn colDescription;
    public TableColumn colPrice;
    public TableView<ItemTM> tblItem;
    public JFXComboBox<String> cmbCategories;
    public TextField txtItemID;
    public TextField txtName;
    public TextField txtQTYOnHand;
    public TextField txtDescription;
    public TextField txtPrice;
    public JFXButton btnAddItem;
    String selectCategorei;
    private final String[] cate={"Pen","Pencil","Book"};
    private final ObservableList<String> cates =FXCollections.observableArrayList(cate);


    LinkedHashMap<TextField,Pattern>map=new LinkedHashMap<>();
    Pattern itemPatten=Pattern.compile("^(I-)[0-9]{4}$");
    Pattern namePatten=Pattern.compile("^[A-z0-9 ]{3,20}$");
    Pattern qtyPatten=Pattern.compile("^[0-9]{1,4}$");
    Pattern descriptionPatten=Pattern.compile("^[A-z ]{3,20}$");
    Pattern pricePatten=Pattern.compile("^[1-9][0-9]*([.][0-9]{1})$");

    public void initialize(){


        setTable();
        cmbCategories.getItems().addAll(
                "Pencil",
                "Book",
                "Bottle",
                "Pen",
                "Paper"
        );



        cmbCategories.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectCategorei= (String) newValue;
        });

        validateInit();


    }



    private void validateInit() {
        btnAddItem.setDisable(true);
        map.put(txtItemID, itemPatten);
        map.put(txtName, namePatten);
        map.put(txtQTYOnHand,qtyPatten);
        map.put(txtDescription,descriptionPatten);
        map.put(txtPrice,pricePatten);
    }

    private void setItemToTable (ArrayList<Item> customers){

        ObservableList<ItemTM> oblist= FXCollections.observableArrayList();
        customers.forEach(e->{  oblist.add(new ItemTM(
                e.getId(),e.getName(),e.getQtyonhand(),e.getCategorie(),e.getDescription(),e.getPrice()));


        });
        tblItem.setItems(oblist);

    }


    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Item i1= new Item(
                txtItemID.getText(),txtName.getText(),
                txtQTYOnHand.getText(),selectCategorei,txtDescription.getText(),Double.parseDouble(txtPrice.getText())

        );

        if (new ItemController().updateItem(i1)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();
        }else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
        setTable();
        tblItem.refresh();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (new ItemController().deleteItem(txtItemID.getText())){
            new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure Deleted....").showAndWait();
            clear();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
        setTable();
    }

    public void btnSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String itemID=txtItemID.getText();

        Item i1=new ItemController().searchItem(itemID);

        if (i1==null){
            new Alert(Alert.AlertType.WARNING,"Empty Result Set..").show();
        }else {
            setData(i1);
        }
    }

    public void btnAddItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Item i1=new Item(
                txtItemID.getText(),txtName.getText(),txtQTYOnHand.getText(),selectCategorei,txtDescription.getText(),
                Double.parseDouble(txtPrice.getText())
        );
        if (new ItemController().saveItem(i1)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Item Saved....").show();
        }else {
            new Alert(Alert.AlertType.WARNING, "Try Again......").show();
        }
        setTable();
        clear();
    }

    void clear(){
        txtItemID.clear();
        txtName.clear();
        txtQTYOnHand.clear();
        txtDescription.clear();
        txtPrice.clear();
        cmbCategories.getSelectionModel().clearSelection();
    }

    void setTable(){
        try {

            colItemID.setCellValueFactory(new PropertyValueFactory<>("id"));
            colName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyonhand"));
            colCategorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
            colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
            colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));



            setItemToTable(new ItemController().getAllItem());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    void setData(Item i){
        txtItemID.setText(i.getId());
        txtName.setText(i.getName());
        txtQTYOnHand.setText(i.getQtyonhand());
        cmbCategories.setValue(i.getCategorie());
        txtDescription.setText(i.getDescription());
        txtPrice.setText(String.valueOf(i.getPrice()));
    }


    public void textFields_Key_Released(KeyEvent keyEvent) {

        Object response = validationUtil.validate(map,btnAddItem);

        if (response instanceof Boolean) {
            btnAddItem.setDisable(false);
        }

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField textField = (TextField) response;
                textField.requestFocus();
            }
        }
    }

}