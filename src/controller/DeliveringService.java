package controller;


import java.sql.SQLException;

public interface DeliveringService {
    public boolean deleteOrder(String code) throws SQLException, ClassNotFoundException;
}
