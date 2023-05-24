package controller;

import db.DbConnection;
import model.Item;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserController implements UserService {

    public List<String> getUserIDS() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM User").executeQuery();
        List<String> ids = new ArrayList<>();
        while (rst.next()) {
            ids.add(
                    rst.getString(1)
            );
        }

        return ids;
    }

    @Override
    public boolean saveUser(User u) throws SQLException, ClassNotFoundException {
        Connection con=DbConnection.getInstance().getConnection();
        String query ="INSERT INTO User VALUES(?,?,?,?,?,?)";
        PreparedStatement stm=con.prepareStatement(query);
        stm.setObject(1,u.getId());
        stm.setObject(2,u.getName());
        stm.setObject(3,u.getAddress());
        stm.setObject(4,u.getEmail());
        stm.setObject(5,u.getMobileno());
        stm.setObject(6,u.getPassword());

        return stm.executeUpdate()>0;
    }

    public User searchUser(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM User WHERE User_ID= '" + id + "'").executeQuery();

        if (rst.next()){
            return new User(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6)

            );
        }else {
            return null;
        }


    }
    public String getCountUser() throws SQLException, ClassNotFoundException {
        String count=null;
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT count(User_ID) FROM User ").executeQuery();
        while (rst.next()) {
            count = rst.getString(1);
        }
        return count;
    }

    @Override
    public boolean deleteUser(String code) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM User WHERE User_ID='"+code+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean updateUser(User u) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE User SET Name=?, address=?, Email=?, PhoneNumber=?, Password=?  WHERE User_ID=?");
        stm.setObject(1,u.getName());
        stm.setObject(2,u.getAddress());
        stm.setObject(3,u.getEmail());
        stm.setObject(4,u.getMobileno());
        stm.setObject(5,u.getPassword());
        stm.setObject(6,u.getId());
        return stm.executeUpdate()>0;
    }

    @Override
    public ArrayList<User> getAllUser() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM User");
        ResultSet rst = stm.executeQuery();
        ArrayList<User> users=new ArrayList<>();
        while (rst.next()) {
            users.add(new User(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6)

            ));

        }
        return users;
    }
}
