/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungnt.tbl_User;

import hungnt.utils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author hungn
 */
public class Tbl_User_DAO implements Serializable{
    
    private static Connection con;
    private static PreparedStatement ps;
    private static ResultSet rs;
    
    private static void closeConnection() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (ps != null) {
            ps.close();
        }
        if (con != null) {
            con.close();
        }
    }
    
    public String signIn(String email, String password) 
            throws SQLException, NamingException {
        String role = "failed";
        try {
            con = DBHelper.getConnection();
            String sql = "Select r.roleName "
                    + "From tbl_Role r "
                    + "Where r.roleId = (Select u.roleId From tbl_User u Where u.email = ? AND u.password = ? COLLATE SQL_Latin1_General_CP1_CS_AS)";
            ps = con.prepareStatement(sql);
            ps.setString(1, email.trim());
            ps.setString(2, password.trim());
            rs = ps.executeQuery();
            if (rs.next()) {
                role = rs.getString("roleName").trim();
            }
        } finally {
            closeConnection();
        }
        return role;
    }
    
    public String getFullName(String email) 
            throws SQLException, NamingException {
        String fullName = "failed";
        try {
            con = DBHelper.getConnection();
            String query = "Select fullName From tbl_User Where email = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, email.trim());
            rs = ps.executeQuery();
            if (rs.next()) {
                fullName = rs.getString("fullName").trim();
            }
        } finally {
            closeConnection();
        }
        return fullName;
    }
    
    public Tbl_User_DTO getUserDetailByEmail(String email)
            throws SQLException, NamingException {
        Tbl_User_DTO dto = null;
        String fullName, phoneNumber, address;
        try {
            con = DBHelper.getConnection();
            String sql = "Select fullName, phoneNumber, address From tbl_User Where email = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                fullName = rs.getString(1);
                phoneNumber =rs.getString(2);
                address = rs.getString(3);
                
                dto = new Tbl_User_DTO();
                dto.setEmail(email);
                dto.setFullName(fullName);
                dto.setPhoneNumber(phoneNumber);
                dto.setAddress(address);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
}
