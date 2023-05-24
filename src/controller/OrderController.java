package controller;

import db.DbConnection;
import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class OrderController implements OrderService{
    public String oderID() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT orderId FROM `Order` ORDER BY orderId DESC LIMIT 1").executeQuery();
        if (rst.next()){

            int tempId = Integer.parseInt(rst.getString(1).split("-")[1]);
            tempId=tempId+1;
            if (tempId<9){
                return "O-00"+tempId;
            }else if(tempId<99){
                return "O-0"+tempId;
            }else{
                return "O-"+tempId;
            }

        }else{
            return "O-001";
        }
    }

  public boolean payOrder(Order order){
      Connection con=null;
      try {

          con=DbConnection.getInstance().getConnection();
          con.setAutoCommit(false);
          PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO `Order` VALUES(?,?,?,?,?,?)");

          stm.setObject(1,order.getOrderid());
          stm.setObject(2,order.getCustomerid());
          stm.setObject(3,order.getOrderdate());
          stm.setObject(4,order.getOrdertime());
          stm.setObject(5,order.getCost());
          stm.setObject(6,order.getPaymethod());

          if (stm.executeUpdate()>0){

             if( saveOrderDetail(order.getOrderid(),order.getItems(),order.getOrderdate(),order.getOrdertime(),order.getCustomerid(),order.getCost(),order.getPaymethod())){
                 con.commit();
                 return true;
             }else {
                 con.rollback();
                 return false;
             }
          }else {
              con.rollback();
              return false;
          }

      } catch (SQLException throwables) {
          throwables.printStackTrace();
      } catch (ClassNotFoundException e) {
          e.printStackTrace();
      }finally {
          try {
              con.setAutoCommit(true);
          } catch (SQLException throwables) {
              throwables.printStackTrace();
          }
      }

      return false;
  }

    public String getCountPayed() throws SQLException, ClassNotFoundException {
        String count=null;
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT count(Order_ID) FROM `order detail` ").executeQuery();
        while (rst.next()) {
            count = rst.getString(1);
        }
        return count;
    }

  private boolean saveOrderDetail(String orderID, ArrayList<OrderDetails>order,String date,String time,String CustomerID,double Cost,String paymethod) throws SQLException, ClassNotFoundException {
      for (OrderDetails temp : order
      ) {
          PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO `order detail` VALUES (?,?,?,?,?,?,?,?,?)");

          stm.setObject(1, temp.getItemcode());
          stm.setObject(2, orderID);
          stm.setObject(3, CustomerID);
          stm.setObject(4, date);
          stm.setObject(5, time);
          stm.setObject(6, temp.getQty());
          stm.setObject(7, temp.getUniteprice());
          stm.setObject(8, Cost);
          stm.setObject(9,paymethod);

          if (stm.executeUpdate() > 0) {

          } else {
              return false;
          }
      }
      return true;
  }

    public ArrayList<Pay> getAllPay() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM `order detail`");
        ResultSet rst = stm.executeQuery();
        ArrayList<Pay> pay=new ArrayList<>();
        while (rst.next()) {
            pay.add(new Pay(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getInt(6),
                    rst.getDouble(7),
                    rst.getDouble(8),
                    rst.getString(9)

            ));

        }
        return pay;
    }

    public ArrayList<payDeliver> getAllOrder() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM `order`");
        ResultSet rst = stm.executeQuery();
        ArrayList<payDeliver> pay=new ArrayList<>();
        while (rst.next()) {
            pay.add(new payDeliver(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDouble(5),
                    rst.getString(6)

            ));

        }
        return pay;
    }


    public String getCountItem() throws SQLException, ClassNotFoundException {
        String count = null;
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT count(Order_ID) FROM `order detail").executeQuery();
        while (rst.next()) {
            count = rst.getString(1);
        }
        return count;
    }


}
