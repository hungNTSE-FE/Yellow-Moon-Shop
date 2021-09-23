/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungnt.tbl_Category;

import hungnt.utils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.naming.NamingException;

/**
 *
 * @author hungn
 */
public class Tbl_Category_DAO implements Serializable{
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
    
    public Map<Integer, String> getAllCategories() 
            throws SQLException, NamingException {
        Map<Integer, String> categories = null;
        try {
            con = DBHelper.getConnection();
            String sql = "Select categoryID, categoryName From tbl_Category";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            categories = new HashMap<>();
            while (rs.next()) {
                categories.put(rs.getInt("categoryID"), rs.getString("categoryName"));
            }
        } finally {
            closeConnection();
        }
        return categories;
    }
    
    public Map<Integer, String> getCategoryById(int id) 
            throws SQLException, NamingException {
        Map<Integer, String> category = null;
        try {
            con = DBHelper.getConnection();
            String sql = "Select categoryName From tbl_Category Where categoryID = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            category = new HashMap<>();
            while (rs.next()) {
                category.put(id, rs.getString(1));
            }
        } finally {
            closeConnection();
        }
        return category;
    }
}
