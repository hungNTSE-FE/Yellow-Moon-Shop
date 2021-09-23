/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungnt.constants;

/**
 *
 * @author hungn
 */
public enum PaymentStatus {
    PAID(1),
    UNPAID(2);
    
    private int status;

    public int getStatus() {
        return status;
    }

    private PaymentStatus(int status) {
        this.status = status;
    }
}
