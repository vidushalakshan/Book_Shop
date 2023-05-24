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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.Employee;
import model.EmployeeSalary;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import view.tm.EmployeeSalaryTM;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class EmpSalaryFormController {

    public AnchorPane root;
    
    
    public TableView<EmployeeSalaryTM> tblSalary;
    public TableColumn colEmpID;
    public TableColumn colName;
    public TableColumn colRole;
    public TableColumn comSalary;
    public TextField txtName;
    public TextField txtRole;
    public TextField txtSalary;
    public JFXButton btnAddSalary;
    String selectCategorie;
    public JFXComboBox<String> cmbEmpID;


    public void initialize(){
        btnAddSalary.setDisable(true);
        empsalary();
        try {

            loadEmployeeIds();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        cmbEmpID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectCategorie=newValue;
            try {
                setEmployeeData(newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    void empsalary(){
        try {

            colEmpID.setCellValueFactory(new PropertyValueFactory<>("id"));
            colName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
            comSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));

            setItemToTable(new EmployeeSalaryController().getAllEmployeeSalary());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    private void setItemToTable(ArrayList<EmployeeSalary> empSalary) {
        ObservableList<EmployeeSalaryTM> oblist= FXCollections.observableArrayList();
        empSalary.forEach(e->{  oblist.add(new EmployeeSalaryTM(
                e.getId(),e.getName(),e.getRole(),e.getSalary()));


        });
        tblSalary.setItems(oblist);
    }

    private void setEmployeeData(String employeeID) throws SQLException, ClassNotFoundException {
        Employee e1=new EmployeeController().searchItem(employeeID);
        if (e1==null){

        }else {
            txtName.setText(e1.getNeme());
            txtRole.setText(e1.getRole());
        }
    }

    private void loadEmployeeIds() throws SQLException, ClassNotFoundException {
        List<String> employeeIDS = new EmployeeController().getEmployeeIDS();
        cmbEmpID.getItems().addAll(employeeIDS);
    }



    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (new EmployeeSalaryController().deleteEmployeeSalary(txtName.getText())){
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
        clear();

        empsalary();

    }



    public void btnAddSalaryOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        EmployeeSalary i1=new EmployeeSalary(
                cmbEmpID.getValue(),txtName.getText(),txtRole.getText(),Double.parseDouble(txtSalary.getText())
        );
        if (new EmployeeSalaryController().saveEmployeeSalary(i1))
            new Alert(Alert.AlertType.CONFIRMATION,"Item Saved....").show();
        else
            new Alert(Alert.AlertType.WARNING,"Try Again......").show();

        clear();

        empsalary();

    }

    public void btnSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String EmpID=txtName.getText();

        EmployeeSalary e1=new EmployeeSalaryController().searchEmpSalary(EmpID);

        if (e1==null){
            new Alert(Alert.AlertType.WARNING,"Empty Result Set..").show();
        }else {
            setData(e1);
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        EmployeeSalary e1= new EmployeeSalary(
                selectCategorie,txtName.getText(),
                txtRole.getText(),Double.parseDouble(txtSalary.getText())

        );


        if (new EmployeeSalaryController().updateEmpSalary(e1)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();
        }else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }

        empsalary();
        tblSalary.refresh();
    }

    void clear(){
        txtName.clear();
        txtRole.clear();
        txtSalary.clear();
        cmbEmpID.getSelectionModel().clearSelection();
    }

    void setData(EmployeeSalary e){
        cmbEmpID.setValue(e.getId());
        txtName.setText(e.getName());
        txtRole.setText(e.getRole());
        txtSalary.setText(String.valueOf(e.getSalary()));
    }

    public void salaryOnKeyReleased(KeyEvent keyEvent) {
        Pattern pricePatten=Pattern.compile("^[1-9][0-9]*([.][0-9]{1})$");
        String typeSalary = txtSalary.getText();
        if (pricePatten.matcher(typeSalary).matches()){
            txtSalary.getParent().setStyle("\"-fx-border-color: green\"");
            btnAddSalary.setDisable(false);
        }else {
            txtSalary.getParent().setStyle("-fx-border-color: red");
            txtSalary.requestFocus();
        }

    }


    public void report(MouseEvent mouseEvent) throws JRException {
        JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("/view/reports/EmpSalary.jrxml"));
        JasperReport compileReport = JasperCompileManager.compileReport(design);
        ObservableList<EmployeeSalaryTM> items = tblSalary.getItems();
        JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null, new JRBeanArrayDataSource(items.toArray()));
        JasperViewer.viewReport(jasperPrint,false);
    }
}
