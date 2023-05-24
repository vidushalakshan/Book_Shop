package controller;

import db.DbConnection;
import model.payDeliver;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeliveringController implements DeliveringService {
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


    public boolean updateItem(payDeliver p) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE `order` SET orderId=?, orderDate=?, orderTime=?, cost=?, status=? WHERE Cus_ID=?");
        stm.setObject(1,p.getOrderid());
        stm.setObject(2,p.getOrderdate());
        stm.setObject(3,p.getOrdertime());
        stm.setObject(4,p.getCost());
        stm.setObject(5,p.getStatus());
        stm.setObject(6,p.getCusid());

        return stm.executeUpdate()>0;
    }


    @Override
    public boolean deleteOrder(String code) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM `order` WHERE Cus_ID='"+code+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }

}
