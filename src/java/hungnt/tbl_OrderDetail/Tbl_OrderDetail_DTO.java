/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungnt.tbl_OrderDetail;

import java.io.Serializable;

/**
 *
 * @author hungn
 */
public class Tbl_OrderDetail_DTO implements Serializable{
    private int orderId;
    private int cakeId;
    private int quantity;

    public Tbl_OrderDetail_DTO() {
    }

    public Tbl_OrderDetail_DTO(int orderId, int cakeId, int quantity) {
        this.orderId = orderId;
        this.cakeId = cakeId;
        this.quantity = quantity;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCakeId() {
        return cakeId;
    }

    public void setCakeId(int cakeId) {
        this.cakeId = cakeId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
}
