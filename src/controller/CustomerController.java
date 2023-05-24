package controller;

import db.DbConnection;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerController implements CustomerService {

    public List<String> getCustomerIDS() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Customer").executeQuery();
        List<String> ids = new ArrayList<>();
        while (rst.next()) {
            ids.add(
                    rst.getString(1)
            );
        }

        return ids;
    }




    @Override
    public boolean saveCustomer(Customer c) throws SQLException, ClassNotFoundException {
        Connection con=DbConnection.getInstance().getConnection();
        String query ="INSERT INTO Customer VALUES(?,?,?,?)";
        PreparedStatement stm=con.prepareStatement(query);
        stm.setObject(1,c.getId());
        stm.setObject(2,c.getName());
        stm.setObject(3,c.getNic());
        stm.setObject(4,c.getMobilenum());

        return stm.executeUpdate()>0;
    }


    @Override
    public boolean deleteICustomer(String code) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Customer WHERE Cus_ID='"+code+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean updateCustomer(Customer c) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Customer SET Name=?, TelephoneNumber=?,nic=?  WHERE Cus_ID=?");
        stm.setObject(1,c.getName());
        stm.setObject(2,c.getMobilenum());
        stm.setObject(3,c.getNic());
        stm.setObject(4,c.getId());
        return stm.executeUpdate()>0;

    }

    public Customer searchCustomer(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM Customer WHERE Cus_ID= '" + id + "'").executeQuery();

        if (rst.next()){
            return new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)

            );
        }else {
            return null;
        }
    }

    public String getCountCustomer() throws SQLException, ClassNotFoundException {
        String count=null;
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT count(Cus_ID) FROM Customer ").executeQuery();
        while (rst.next()) {
            count = rst.getString(1);
        }
        return count;
    }

    @Override
    public ArrayList<Customer> getAllCustomer() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Customer");
        ResultSet rst = stm.executeQuery();
        ArrayList<Customer> customer=new ArrayList<>();
        while (rst.next()) {
            customer.add(new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)

            ));

        }
        return customer;

    }
    
}
