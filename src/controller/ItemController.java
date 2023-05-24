package controller;

import db.DbConnection;
import model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemController implements ItemService {

    public List<String> getItemIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Item").executeQuery();
        List<String> ids = new ArrayList<>();
        while (rst.next()) {
            ids.add(
                    rst.getString(1)
            );
        }

        return ids;
    }

    @Override
    public boolean saveItem(Item i) throws SQLException, ClassNotFoundException {
        Connection con=DbConnection.getInstance().getConnection();
        String query ="INSERT INTO Item VALUES(?,?,?,?,?,?)";
        PreparedStatement stm=con.prepareStatement(query);
        stm.setObject(1,i.getId());
        stm.setObject(2,i.getName());
        stm.setObject(3,i.getQtyonhand());
        stm.setObject(4,i.getCategorie());
        stm.setObject(5,i.getDescription());
        stm.setObject(6,i.getPrice());

        return stm.executeUpdate()>0;
    }

    @Override
    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Item WHERE Item_ID='"+code+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean updateItem(Item i) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Item SET name=?, qtyonhand=?, cateorie=?, description=?,price=?  WHERE Item_ID=?");
        stm.setObject(1,i.getName());
        stm.setObject(2,i.getQtyonhand());
        stm.setObject(3,i.getCategorie());
        stm.setObject(4,i.getDescription());
        stm.setObject(5,i.getPrice());
        stm.setObject(6,i.getId());
        return stm.executeUpdate()>0;
    }

    public String getCountItem() throws SQLException, ClassNotFoundException {
        String count=null;
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT count(User_ID) FROM User ").executeQuery();
        while (rst.next()) {
            count = rst.getString(1);
        }
        return count;
    }

    public Item searchItem(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM Item WHERE Item_ID= '" + id + "'").executeQuery();

        if (rst.next()){
            return new Item(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getDouble(6)

            );
        }else {
            return null;
        }


    }


    @Override
    public ArrayList<Item> getAllItem() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Item");
        ResultSet rst = stm.executeQuery();
        ArrayList<Item> item=new ArrayList<>();
        while (rst.next()) {
            item.add(new Item(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getDouble(6)

            ));

        }
        return item;

    }
}
