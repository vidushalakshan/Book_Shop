package controller;

import db.DbConnection;
import model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeController implements EmployeeService {
    public List<String> getEmployeeIDS() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Employee").executeQuery();
        List<String> ids = new ArrayList<>();
        while (rst.next()) {
            ids.add(
                    rst.getString(1)
            );
        }

        return ids;
    }

    @Override
    public boolean saveEmployee(Employee e) throws SQLException, ClassNotFoundException {
        Connection con=DbConnection.getInstance().getConnection();
        String query ="INSERT INTO Employee VALUES(?,?,?,?,?,?,?)";
        PreparedStatement stm=con.prepareStatement(query);
        stm.setObject(1,e.getId());
        stm.setObject(2,e.getNeme());
        stm.setObject(3,e.getAddress());
        stm.setObject(4,e.getEmail());
        stm.setObject(5,e.getMobileno());
        stm.setObject(6,e.getRole());
        stm.setObject(7,e.getGender());

        return stm.executeUpdate()>0;
    }

    @Override
    public boolean deleteEmployee(String code) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Employee WHERE Emp_ID='"+code+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean updateEmployee(Employee e) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Employee SET Name=?, Address=?, Email=?, Mobile_No=?, Role=?, Gender=?  WHERE Emp_ID=?");
        stm.setObject(1,e.getNeme());
        stm.setObject(2,e.getAddress());
        stm.setObject(3,e.getEmail());
        stm.setObject(4,e.getMobileno());
        stm.setObject(5,e.getRole());
        stm.setObject(6,e.getGender());
        stm.setObject(7,e.getId());
        return stm.executeUpdate()>0;
    }

    @Override
    public ArrayList<Employee> getAllEmployee() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Employee");
        ResultSet rst = stm.executeQuery();
        ArrayList<Employee> employees=new ArrayList<>();
        while (rst.next()) {
            employees.add(new Employee(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7)

            ));

        }
        return employees;

    }

    public String getCounEmployee() throws SQLException, ClassNotFoundException {
        String count=null;
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT count(Emp_ID) FROM Employee ").executeQuery();
        while (rst.next()) {
            count = rst.getString(1);
        }
        return count;
    }

    public Employee searchItem(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM Employee WHERE Emp_ID= '" + id + "'").executeQuery();

        if (rst.next()){
            return new Employee(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7)

            );
        }else {
            return null;
        }


    }


}
