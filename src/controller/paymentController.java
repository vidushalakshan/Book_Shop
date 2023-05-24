package controller;

import db.DbConnection;
import model.payDeliver;

import java.sql.ResultSet;
import java.sql.SQLException;

public class paymentController implements paymentServic{
    @Override
    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM `order detail` WHERE Cus_ID='"+code+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }

    public payDeliver searchOrder(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM `order` WHERE Cus_ID='"+ id +"'").executeQuery();

        if (rst.next()){
            return new payDeliver(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDouble(5),
                    rst.getString(6)

            );
        }else {
            return null;
        }


    }

}
