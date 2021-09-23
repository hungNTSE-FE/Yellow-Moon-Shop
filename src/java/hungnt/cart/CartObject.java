/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungnt.cart;

import hungnt.tbl_Cake.Tbl_Cake_DTO;
import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author hungn
 */
public class CartObject implements Serializable {
    private HashMap<Integer, Tbl_Cake_DTO> cart;

    public CartObject() {
        this.cart = new HashMap<>();
    }

    public HashMap<Integer, Tbl_Cake_DTO> getCart() {
        return cart;
    }

    public int getCartSize() {
        return cart.size();
    }
    
    public void addToCart(Tbl_Cake_DTO dto) {
        if (this.cart.containsKey(dto.getCakeId())) {
            int quantity = this.cart.get(dto.getCakeId()).getQuantity() + 1;
            dto.setQuantity(quantity);
        }
        this.cart.put(dto.getCakeId(), dto);
    }
    
    public void remove(int id) {
        if (this.cart.containsKey(id)) {
            this.cart.remove(id);
        }
    }
    
    public int getTotal() {
        int result = 0;
        for (Tbl_Cake_DTO dto : this.cart.values()) {
            result += dto.getPrice() * dto.getQuantity();
        }
        return result;
    }
    
    public void update(int id, int quantity) {
        if (this.cart.containsKey(id)) {
            this.cart.get(id).setQuantity(quantity);
        }
    }
    
    
    
}
