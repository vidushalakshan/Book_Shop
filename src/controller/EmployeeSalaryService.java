package controller;

import model.Employee;
import model.EmployeeSalary;
import model.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeSalaryService {
    public boolean saveEmployeeSalary(EmployeeSalary e) throws SQLException, ClassNotFoundException;
    public boolean deleteEmployeeSalary(String code) throws SQLException, ClassNotFoundException;
    public boolean updateEmpSalary(EmployeeSalary e ) throws SQLException, ClassNotFoundException;
    public ArrayList<EmployeeSalary> getAllEmployeeSalary() throws SQLException, ClassNotFoundException;
}
