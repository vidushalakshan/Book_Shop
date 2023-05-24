package controller;

import db.DbConnection;
import model.EmployeeSalary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeSalaryController implements EmployeeSalaryService {
    @Override
    public boolean saveEmployeeSalary(EmployeeSalary e) throws SQLException, ClassNotFoundException {
        Connection con=DbConnection.getInstance().getConnection();
        String query ="INSERT INTO EmpSalary VALUES(?,?,?,?)";
        PreparedStatement stm=con.prepareStatement(query);
        stm.setObject(1,e.getId());
        stm.setObject(2,e.getName());
        stm.setObject(3,e.getRole());
        stm.setObject(4,e.getSalary());

        return stm.executeUpdate()>0;
    }

    @Override
    public boolean deleteEmployeeSalary(String code) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM EmpSalary WHERE Name='"+code+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean updateEmpSalary(EmployeeSalary e) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE empsalary SET name=?, Role=?, salary=? WHERE Emp_ID=? ");
        stm.setObject(1,e.getName());
        stm.setObject(2,e.getRole());
        stm.setObject(3,e.getSalary());
        stm.setObject(4,e.getId());
        return stm.executeUpdate()>0;
    }


    public EmployeeSalary searchEmpSalary(String Name) throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM empsalary WHERE Name= '" + Name + "'").executeQuery();

        if (rst.next()){
            return new EmployeeSalary(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4)

            );
        }else {
            return null;
        }


    }

    @Override
    public ArrayList<EmployeeSalary> getAllEmployeeSalary() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM empsalary");
        ResultSet rst = stm.executeQuery();
        ArrayList<EmployeeSalary> empsalary=new ArrayList<>();
        while (rst.next()) {
            empsalary.add(new EmployeeSalary(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4)

            ));

        }
        return empsalary;
    }


}
