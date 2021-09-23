/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungnt.tbl_OrderDetail;

import hungnt.utils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author hungn
 */
public class Tbl_OrderDetail_DAO implements Serializable{
    
    private static Connection con;
    private static PreparedStatement ps;
    private static ResultSet rs ;
    
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
    
    public void insertOderDetail(Tbl_OrderDetail_DTO dto)
            throws SQLException, NamingException {
        try {
            con = DBHelper.getConnection();
            String sql = "Insert Into tbl_orderDetail(orderID, cakeID, quantity) "
                    + "values(?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, dto.getOrderId());
            ps.setInt(2, dto.getCakeId());
            ps.setInt(3, dto.getQuantity());
            
            int affectRows = ps.executeUpdate();
            if (affectRows == 0) {
                throw new SQLException("Failed to insert order detail.");
            }
        } finally {
            closeConnection();
        }
    }
    
    public List<Tbl_OrderDetail_DTO> getOrderDetailsByOrderId(int orderID) 
            throws SQLException, NamingException {
        List<Tbl_OrderDetail_DTO> result = null;
        try {
            con = DBHelper.getConnection();
            String sql = "Select cakeID, quantity "
                    + "From tbl_OrderDetail"
                    + " Where orderID = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, orderID);
            result = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {
                int cakeId = rs.getInt(1);
                int quantity = rs.getInt(2);
                Tbl_OrderDetail_DTO orderDetailDTO = new Tbl_OrderDetail_DTO(orderID, cakeId, quantity);
                result.add(orderDetailDTO);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
}
