package controller;

import model.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserService {
    public boolean saveUser(User u) throws SQLException, ClassNotFoundException;
    public boolean deleteUser(String code) throws SQLException, ClassNotFoundException;
    public boolean updateUser(User u) throws SQLException, ClassNotFoundException;
    public ArrayList<User> getAllUser() throws SQLException, ClassNotFoundException;
}
