/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungnt.tbl_CakeStatus;

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
public class Tbl_CakeStatus_DAO implements Serializable{
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
    
    public Map<Integer, String> getStatusById(int id) 
        throws SQLException, NamingException {
        Map<Integer, String> status = null;
        try {
            con = DBHelper.getConnection();
            String sql = "Select statusName From tbl_CakeStatus Where statusID = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            status = new HashMap<>();
            if (rs.next()) {
                status.put(id, rs.getString(1));
            }
        } finally {
            closeConnection();
        }
        return status;
    }
    
    public Map<Integer, String> getAllStatus()
            throws SQLException, NamingException {
        Map<Integer, String> status = null;
        try {
            con = DBHelper.getConnection();
            String sql = "Select statusID, statusName From tbl_CakeStatus";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            status = new HashMap<>();
            while (rs.next()) {
                status.put(rs.getInt(1), rs.getString(2));
            }
        } finally {
            closeConnection();
        }
        return status;
    }
}
