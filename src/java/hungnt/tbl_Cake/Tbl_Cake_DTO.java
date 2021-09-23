/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungnt.tbl_Cake;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author hungn
 */
public class Tbl_Cake_DTO implements Serializable {
    private int cakeId;
    private int categoryId;
    private int statusId;
    private int price;
    private int quantity;
    private String cakeName;
    private String cakeImage;
    private String description;
    private Date createDate;
    private Date expirationDate;
    private Date lastModified;
    private String lastModifier;
    
    public Tbl_Cake_DTO() {
    }

    //Constructor for retrieving cake list
    public Tbl_Cake_DTO(int categoryId, int statusId, int price, int quantity, String cakeName, Date createDate, Date expirationDate) {
        this.categoryId = categoryId;
        this.statusId = statusId;
        this.price = price;
        this.quantity = quantity;
        this.cakeName = cakeName;
        this.createDate = createDate;
        this.expirationDate = expirationDate;
    }

    //Constructor for creating new cake
    public Tbl_Cake_DTO(int categoryId, int price, int quantity, String cakeName, String cakeImage, String description, Date expirationDate) {
        this.categoryId = categoryId;
        this.price = price;
        this.quantity = quantity;
        this.cakeName = cakeName;
        this.cakeImage = cakeImage;
        this.description = description;
        this.expirationDate = expirationDate;
    }
    
    
    
    //All args contructor
    public Tbl_Cake_DTO(int cakeId, int categoryId, int statusId, int price, int quantity, String cakeName, String cakeImage, String description, Date createDate, Date expirationDate) {
        this.cakeId = cakeId;
        this.categoryId = categoryId;
        this.statusId = statusId;
        this.price = price;
        this.quantity = quantity;
        this.cakeName = cakeName;
        this.cakeImage = cakeImage;
        this.description = description;
        this.createDate = createDate;
        this.expirationDate = expirationDate;
    }

    public int getCakeId() {
        return cakeId;
    }

    public void setCakeId(int cakeId) {
        this.cakeId = cakeId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCakeName() {
        return cakeName;
    }

    public void setCakeName(String cakeName) {
        this.cakeName = cakeName;
    }

    public String getCakeImage() {
        return cakeImage;
    }

    public void setCakeImage(String cakeImage) {
        this.cakeImage = cakeImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getLastModifier() {
        return lastModifier;
    }

    public void setLastModifier(String lastModifier) {
        this.lastModifier = lastModifier;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }
    
    
}
