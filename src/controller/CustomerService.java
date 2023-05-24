package controller;

import model.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerService {
    public boolean saveCustomer(Customer c) throws SQLException, ClassNotFoundException;
    public boolean deleteICustomer(String code) throws SQLException, ClassNotFoundException;
    public boolean updateCustomer(Customer c) throws SQLException, ClassNotFoundException;
    public ArrayList<Customer> getAllCustomer() throws SQLException, ClassNotFoundException;
}
