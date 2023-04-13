/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entity.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import tools.BCrypt;
import tools.MaConnection;


/**
 *
 * @author Ayoub
 */
public class UserService {
    
    Connection cnx;
    public static int code;
    public UserService() {
        cnx = MaConnection.getInstance().getCnx();    
    }
    
    
    public void createuser(User u){
        try {
            String req = "INSERT INTO `user`(`email`, `roles`, `password`, `is_verified`, `date_naissance`, `nom`, `prenom`, `genre`) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, u.getEmail());
            st.setString(2, u.getRoles());
            st.setString(3, BCrypt.hashpw(u.getPassword(), BCrypt.gensalt()));
            st.setBoolean(4, false);
            st.setDate(5, (Date) u.getDate_naissance());
            st.setString(6,u.getNom());
            st.setString(7, u.getPrenom());
            st.setString(8, u.getGenre());
            
            st.executeUpdate();
            System.out.println(" User Added successfuly");
                 
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public ArrayList<User> getAllUsers() {
        ArrayList<User> userList = new ArrayList<>();
        try {
            String req = "SELECT * FROM `user`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setEmail(rs.getString("email"));
                u.setRoles(rs.getString("roles"));
                u.setPassword(rs.getString("password"));
                u.setIs_verified(rs.getBoolean("is_verified"));
                u.setDate_naissance(rs.getDate("date_naissance")); 
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                u.setGenre(rs.getString("genre"));
                userList.add(u);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return userList;
    }
    public User getUserById(int id) {
        User u = new User();
        try {
            String req = "SELECT * FROM `user` WHERE id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                u.setId(rs.getInt("id"));
                u.setEmail(rs.getString("email"));
                u.setRoles(rs.getString("roles"));
                u.setPassword(rs.getString("password"));
                u.setIs_verified(rs.getBoolean("is_verified"));
                u.setDate_naissance(rs.getDate("date_naissance"));
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                u.setGenre(rs.getString("genre"));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return u;
    }
    public void updateUser(User u) {
        try {
            String req = "UPDATE `user` SET `email`=?,`roles`=?,`password`=?,`is_verified`=?,`date_naissance`=?,`nom`=?,`prenom`=?,`genre`=? WHERE id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, u.getEmail());
            st.setString(2, u.getRoles());
            st.setString(3, BCrypt.hashpw(u.getPassword(), BCrypt.gensalt()));
            st.setBoolean(4, u. getIs_verified());
            st.setDate(5, (Date) u.getDate_naissance());
            st.setString(6,u.getNom());
            st.setString(7, u.getPrenom());
            st.setString(8, u.getGenre());
            st.setInt(9, u.getId());
            
            st.executeUpdate();
            System.out.println(" User Updated successfuly");
                 
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public void deleteUser(User u) {
        try {
            String req = "DELETE FROM `user` WHERE id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, u.getId());
            st.executeUpdate();
            System.out.println(" User Deleted successfuly");
                 
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }


    


    
}
    
    
    
    
    
   
