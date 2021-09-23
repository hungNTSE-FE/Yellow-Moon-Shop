/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungnt.tbl_Order;

import hungnt.constants.PaymentStatus;
import hungnt.utils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author hungn
 */
public class Tbl_Order_DAO implements Serializable{
    
    private static Connection con;
    private static PreparedStatement ps;
    private static ResultSet rs ;
    
    private static final int PAID = PaymentStatus.PAID.getStatus();
    private static final int UNPAID = PaymentStatus.UNPAID.getStatus();
    
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
    
    public int insertOrder(Tbl_Order_DTO dto) 
            throws SQLException, NamingException {
        int generatedId = -1;
        try {
            con = DBHelper.getConnection();
            String sql = "Insert into tbl_Order(email, customerName, address, "
                    + "phoneNumber, orderDate, total, paymentMethodID, paymentStatusID) values(?,?,?,?,?,?,?,?)";
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, dto.getEmail());
            ps.setString(2, dto.getCustomerName());
            ps.setString(3, dto.getAddress());
            ps.setString(4, dto.getPhoneNumber());
            ps.setTimestamp(5, dto.getOrderDate());
            ps.setInt(6, dto.getTotal());
            ps.setInt(7, dto.getPaymentMethodId());
            ps.setInt(8, UNPAID);
            
            int affectedRows = ps.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("Failed to insert order.");
            }
            
            try (ResultSet generatedKeys = ps.getGeneratedKeys()){
                if (generatedKeys.next()) {
                    generatedId = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            } 
        } finally {
                closeConnection();
        }
        return generatedId;
    }
    
    public Tbl_Order_DTO getOrdersById(int id) 
            throws SQLException, NamingException {
        Tbl_Order_DTO result = null;
        int total;
        int paymentMethodId;
        int paymentStatusId;
        String customerName, address, phoneNumber;
        Timestamp orderDate;
        try {
            con = DBHelper.getConnection();
            String sql = "Select customerName, address, phoneNumber, orderDate, total, paymentMethodID, paymentStatusID "
                    + "From tbl_Order "
                    + "Where orderID = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                customerName = rs.getString(1);
                address = rs.getString(2);
                phoneNumber = rs.getString(3);
                orderDate = rs.getTimestamp(4);
                total = rs.getInt(5);
                paymentMethodId = rs.getInt(6);
                paymentStatusId = rs.getInt(7);
                result = new Tbl_Order_DTO(total, paymentMethodId, paymentStatusId, customerName, address, phoneNumber, orderDate);
                result.setOrderId(id);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public List<Tbl_Order_DTO> getOrdersByEmail(String email)
            throws SQLException, NamingException {
        List<Tbl_Order_DTO> result = null;
        int orderId, total;
        String customerName, address, phoneNumber;
        Timestamp orderDate;
        try {
            con = DBHelper.getConnection();
            String sql = "Select orderID, customerName, address, phoneNumber, orderDate, total "
                    + "From tbl_Order "
                    + "Where email = ? "
                    + "Order By orderDate DESC";
            ps = con.prepareStatement(sql);
            ps.setString(1, email.trim());
            rs = ps.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                orderId = rs.getInt(1);
                customerName = rs.getString(2);
                address = rs.getString(3);
                phoneNumber = rs.getString(4);
                orderDate = rs.getTimestamp(5);
                total = rs.getInt(6);
                Tbl_Order_DTO dto = new Tbl_Order_DTO(total, customerName, address, phoneNumber, orderDate);
                dto.setOrderId(orderId);
                
                result.add(dto);
            }
                    
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public List<Tbl_Order_DTO> getOrdersByEmailAndOrderId(String email, int id)
            throws SQLException, NamingException {
        List<Tbl_Order_DTO> result = null;
        int orderId, total;
        String customerName, address, phoneNumber;
        Timestamp orderDate;
        try {
            con = DBHelper.getConnection();
             String sql = "Select orderID, customerName, address, phoneNumber, orderDate, total "
                    + "From tbl_Order "
                    + "Where email = ? AND orderID = ? "
                    + "Order By orderDate DESC";
            ps = con.prepareStatement(sql);
            ps.setString(1, email.trim());
            ps.setInt(2, id);
            rs = ps.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                orderId = rs.getInt(1);
                customerName = rs.getString(2);
                address = rs.getString(3);
                phoneNumber = rs.getString(4);
                orderDate = rs.getTimestamp(5);
                total = rs.getInt(6);
                Tbl_Order_DTO dto = new Tbl_Order_DTO(total, customerName, address, phoneNumber, orderDate);
                dto.setOrderId(orderId);
                
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
}
