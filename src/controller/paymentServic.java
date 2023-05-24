package controller;

import java.sql.SQLException;

public interface paymentServic {
    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException;
}
