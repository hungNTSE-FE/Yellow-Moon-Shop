/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungnt.tbl_Cake;

import hungnt.constants.AppConstants;
import hungnt.constants.CakeStatus;
import hungnt.utils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
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
public class Tbl_Cake_DAO implements Serializable{
    
    private static Connection con;
    private static PreparedStatement ps;
    private static ResultSet rs;
    
    private static final int ACTIVE = CakeStatus.ACTIVE.getStatus();
    private static final int INACTIVE = CakeStatus.INACTIVE.getStatus();
    
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
    
    public int getCakeRowCount() throws SQLException, NamingException {
        int count = 0;
        try {
            con = DBHelper.getConnection();
            String sql = "Select Count(*) as RowNumber "
                    + "From tbl_Cake "
                    + "Where [statusID] AND quantity > 0";
            ps = con.prepareStatement(sql);
            ps.setInt(1, ACTIVE);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } finally {
            closeConnection();
        }
        return count;
    }
    
    public int getCakeRowCountByName(String cakeName) throws SQLException, NamingException {
        int count = 0;
        try {
            con = DBHelper.getConnection();
            String sql = "Select Count(*) as RowNumber "
                    + "From tbl_Cake "
                    + "Where cakeName LIKE ? AND [statusID] = ? AND quantity > 0";
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + cakeName + "%");
            ps.setInt(2, ACTIVE);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } finally {
            closeConnection();
        }
        return count;
    }
    
    public int getCakeRowCount(int categoryID) throws SQLException, NamingException {
        int count = 0;
        try {
            con = DBHelper.getConnection();
            String sql = "Select Count(*) as RowNumber "
                    + "From tbl_Cake "
                    + "Where categoryID = ? AND [statusID] = ? AND quantity > 0";
            ps = con.prepareStatement(sql);
            ps.setInt(1, categoryID);
            ps.setInt(2, ACTIVE);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } finally {
            closeConnection();
        }
        return count;
    }
    
    public int getCakeRowCount(int minPrice, int maxPrice) throws SQLException, NamingException {
        int count = 0;
        try {
            con = DBHelper.getConnection();
            String sql = "Select Count(*) as RowNumber "
                    + "From tbl_Cake "
                    + "Where price >= ? AND price <= ? "
                    + "AND [statusID] = ? AND quantity > 0";
            ps = con.prepareStatement(sql);
            ps.setInt(1, minPrice);
            ps.setInt(2, maxPrice);
            ps.setInt(3, ACTIVE);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } finally {
            closeConnection();
        }
        return count;
    }
    
    public List<Tbl_Cake_DTO> getCakeByName(String search, int offset, int rowCount)
            throws SQLException, NamingException {
        List<Tbl_Cake_DTO> cakeList = null;
        int cakeId;
        String cakeName;
        String description;
        String image;
        Date createDate;
        Date expirationDate;
        int price, quantity, categoryId;
        Tbl_Cake_DTO cakeDTO = null;
        try {
            con = DBHelper.getConnection();
            String sql = "Select cakeID, cakeName, categoryID, cakeImage, description, price, quantity, createDate, expirationDate "
                    + "From tbl_Cake "
                    + "Where [statusID] = ? AND expirationDate >= GETDATE() AND quantity > 0 AND cakeName LIKE ? "
                    + "Order By createDate DESC "
                    + "Offset ? ROWS "
                    + "FETCH NEXT ? ROWS ONLY";
            ps = con.prepareStatement(sql);
            ps.setInt(1, ACTIVE);
            ps.setNString(2, "%" + search + "%");
            ps.setInt(3, offset);
            ps.setInt(4, rowCount);
            
            rs = ps.executeQuery();
            cakeList = new ArrayList<>();
            while (rs.next()) {
                cakeId = rs.getInt("cakeID");
                cakeName = rs.getString("cakeName").trim();
                categoryId = rs.getInt("categoryID");
                image = AppConstants.IMAGE_PATH + rs.getString("cakeImage");
                description = rs.getString("description");
                price = rs.getInt("price");
                quantity = rs.getInt("quantity");
                createDate = rs.getDate("createDate");
                expirationDate = rs.getDate("expirationDate");
                
                cakeDTO = new Tbl_Cake_DTO(categoryId, 1, price, quantity, cakeName, createDate, expirationDate);
                cakeDTO.setCakeId(cakeId);
                cakeDTO.setDescription(description);
                cakeDTO.setCakeImage(image);
                
                cakeList.add(cakeDTO);
            }
        } finally {
            closeConnection();
        }
        return cakeList;
    }
    
    public List<Tbl_Cake_DTO> getCakeByCategory(int categoryId, int offset, int rowCount)
            throws SQLException, NamingException {
        List<Tbl_Cake_DTO> cakeList = null;
        int cakeId;
        String cakeName;
        String description;
        String image;
        Date createDate;
        Date expirationDate;
        int price, quantity;
        Tbl_Cake_DTO cakeDTO = null;
        try {
            con = DBHelper.getConnection();
            String sql = "Select cakeID, cakeName, cakeImage, description, price, "
                    + "quantity, createDate, expirationDate "
                    + "From tbl_Cake "
                    + "Where [statusID] = ? AND expirationDate >= GETDATE() AND quantity > 0 AND categoryID = ? "
                    + "Order By createDate DESC "
                    + "Offset ? ROWS "
                    + "FETCH NEXT ? ROWS ONLY";
            ps = con.prepareStatement(sql);
            ps.setInt(1, ACTIVE);
            ps.setInt(2, categoryId);
            ps.setInt(3, offset);
            ps.setInt(4, rowCount);
            
            rs = ps.executeQuery();
            cakeList = new ArrayList<>();
            while (rs.next()) {
                cakeId = rs.getInt("cakeID");
                cakeName = rs.getString("cakeName").trim();
                image = AppConstants.IMAGE_PATH + rs.getString("cakeImage");
                description = rs.getString("description");
                price = rs.getInt("price");
                quantity = rs.getInt("quantity");
                createDate = rs.getDate("createDate");
                expirationDate = rs.getDate("expirationDate");
                
                cakeDTO = new Tbl_Cake_DTO(categoryId, 1, price, quantity, cakeName, createDate, expirationDate);
                cakeDTO.setCakeId(cakeId);
                cakeDTO.setDescription(description);
                cakeDTO.setCakeImage(image);
                
                cakeList.add(cakeDTO);
            }
        } finally {
            closeConnection();
        }
        return cakeList;
    }
    
    public List<Tbl_Cake_DTO> getCakeByPrice(int minPrice, int maxPrice, int offset, int rowCount)
            throws SQLException, NamingException {
        List<Tbl_Cake_DTO> cakeList = null;
        int cakeId;
        String cakeName;
        String description;
        String image;
        Date createDate;
        Date expirationDate;
        int price, quantity, categoryId;
        Tbl_Cake_DTO cakeDTO = null;
        try {
            con = DBHelper.getConnection();
            String sql = "Select cakeID, cakeName, cakeImage, description, price, "
                    + "quantity, createDate, expirationDate, categoryID "
                    + "From tbl_Cake "
                    + "Where [statusID] = ? AND expirationDate >= GETDATE() AND quantity > 0 "
                    + "AND price >= ? AND price <= ? "
                    + "Order By createDate DESC "
                    + "Offset ? ROWS "
                    + "FETCH NEXT ? ROWS ONLY";
            ps = con.prepareStatement(sql);
            ps.setInt(1, ACTIVE);
            ps.setInt(2, minPrice);
            ps.setInt(3, maxPrice);
            ps.setInt(4, offset);
            ps.setInt(5, rowCount);
            rs = ps.executeQuery();
            cakeList = new ArrayList<>();
            while (rs.next()) {
                cakeId = rs.getInt("cakeID");
                cakeName = rs.getString("cakeName").trim();
                image = AppConstants.IMAGE_PATH + rs.getString("cakeImage");
                description = rs.getString("description");
                price = rs.getInt("price");
                quantity = rs.getInt("quantity");
                createDate = rs.getDate("createDate");
                expirationDate = rs.getDate("expirationDate");
                categoryId = rs.getInt("categoryID");
                
                cakeDTO = new Tbl_Cake_DTO(categoryId, 1, price, quantity, cakeName, createDate, expirationDate);
                cakeDTO.setCakeId(cakeId);
                cakeDTO.setDescription(description);
                cakeDTO.setCakeImage(image);
                cakeDTO.setCategoryId(categoryId);
                
                cakeList.add(cakeDTO);
            }
        } finally {
            closeConnection();
        }
        return cakeList;
    }
    
    public Tbl_Cake_DTO getCakeById(int id) throws SQLException, NamingException {
        Tbl_Cake_DTO dto = null;
        String cakeName;
        String description;
        String image;
        Date createDate;
        Date expirationDate;
        int price, quantity, categoryId, statusId;
        try {
            con = DBHelper.getConnection();
            String sql = "Select cakeName, categoryID, cakeImage, description, price, "
                    + "quantity, createDate, expirationDate, statusID "
                    + "From tbl_Cake "
                    + "Where cakeID = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                cakeName = rs.getString(1);
                categoryId = rs.getInt(2);
                image =  AppConstants.IMAGE_PATH + rs.getString(3);
                description = rs.getString(4);
                price = rs.getInt(5);
                quantity = rs.getInt(6);
                createDate = rs.getDate(7);
                expirationDate = rs.getDate(8);
                statusId = rs.getInt(9);
                
                dto = new Tbl_Cake_DTO(id, categoryId, statusId, price, quantity, cakeName,
                        image, description, createDate, expirationDate);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
    
    public boolean insertCake(Tbl_Cake_DTO dto) throws SQLException, NamingException {
        boolean result = false;
        
        try {
            con = DBHelper.getConnection();
            String sql = "Insert Into tbl_Cake(cakeName, categoryID, cakeImage, description, price, "
                    + "quantity, createDate, expirationDate, statusID) "
                    + "values(?,?,?,?,?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, dto.getCakeName());
            ps.setInt(2, dto.getCategoryId());
            ps.setString(3, dto.getCakeImage());
            ps.setString(4, dto.getDescription());
            ps.setInt(5, dto.getPrice());
            ps.setInt(6, dto.getQuantity());
            
            Date createDateString = new Date(System.currentTimeMillis());
            
            ps.setDate(7, createDateString);
            ps.setDate(8, dto.getExpirationDate());
            ps.setInt(9, ACTIVE);
            
            result = ps.executeUpdate() > 0;
        } finally {
            closeConnection();
        }     
        return result;
    }
    
    public boolean updateCake(Tbl_Cake_DTO dto) throws SQLException, NamingException {
        boolean result = false;
        try {
            con = DBHelper.getConnection();
            String sql = "";
            if (dto.getCakeImage().isEmpty()) {
                sql = "Update tbl_Cake "
                        + "Set cakeName = ?, price = ?, description = ?, categoryID = ?, "
                        + "quantity = ?, statusID = ?, expirationDate = ?, lastModified = ?, lastModifier = ? "
                        + "Where cakeID = ?";
            } else {
                sql = "Update tbl_Cake "
                        + "Set cakeName = ?, price = ?, description = ?, categoryID = ?, "
                        + "quantity = ?, statusID = ?, expirationDate = ?, lastModified = ?, lastModifier = ?, cakeImage = ? "
                        + "Where cakeID = ?";
            }
            ps = con.prepareStatement(sql);
            ps.setString(1, dto.getCakeName());
            ps.setInt(2, dto.getPrice());
            ps.setString(3, dto.getDescription());
            ps.setInt(4, dto.getCategoryId());
            ps.setInt(5, dto.getQuantity());
            ps.setInt(6, dto.getStatusId());
            ps.setDate(7, dto.getExpirationDate());
            ps.setDate(8, dto.getLastModified());
            ps.setString(9, dto.getLastModifier());
            if (dto.getCakeImage().isEmpty()) {
                ps.setInt(10, dto.getCakeId());
            } else {
                ps.setString(10, dto.getCakeImage());
                ps.setInt(11, dto.getCakeId());
            }
            result = ps.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public boolean deleteCake(int cakeId)
            throws SQLException, NamingException {
        boolean result = false;
        try {
            con = DBHelper.getConnection();
            String sql = "Update tbl_Cake Set statusID = ? Where cakeID = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, INACTIVE);
            ps.setInt(2, cakeId);
            result = ps.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public int getQuantity(int cakeId) 
            throws SQLException, NamingException {
        int quantity = -1;
        try {
            con = DBHelper.getConnection();
            String sql = "Select quantity From tbl_Cake Where cakeID = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, cakeId);
            rs = ps.executeQuery();
            if (rs.next()) {
                quantity = rs.getInt(1);
            }
        } finally {
            closeConnection();
        }
        return quantity;
    }
    
    public boolean updateQuantity(int cakeId, int quantity)
            throws SQLException, NamingException {
        boolean result = false;
        try {
            //Check quantity before decrease it
            con = DBHelper.getConnection();
            String sql = "Declare @inventory int "
                    + "Declare @cakeID int "
                    + "Set @cakeID = ? "
                    + "Set @inventory = (Select quantity From tbl_Cake Where cakeID = @cakeID) "
                    + "Update tbl_Cake "
                    + "Set quantity = quantity - ? "
                    + "Where cakeID = @cakeID";
            ps = con.prepareStatement(sql);
            ps.setInt(1, cakeId);
            ps.setInt(2, quantity);
            result = ps.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }
}
