package controller;

import model.Employee;
import model.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeService {
    public boolean saveEmployee(Employee e) throws SQLException, ClassNotFoundException;
    public boolean deleteEmployee(String code) throws SQLException, ClassNotFoundException;
    public boolean updateEmployee(Employee e) throws SQLException, ClassNotFoundException;
    public ArrayList<Employee> getAllEmployee() throws SQLException, ClassNotFoundException;
}
