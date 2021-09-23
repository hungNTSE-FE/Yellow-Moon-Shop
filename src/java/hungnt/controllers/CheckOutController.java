/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungnt.controllers;

import hungnt.cart.CartObject;
import hungnt.constants.AppConstants;
import hungnt.tbl_Cake.Tbl_Cake_DAO;
import hungnt.tbl_Cake.Tbl_Cake_DTO;
import hungnt.tbl_Order.Tbl_Order_DAO;
import hungnt.tbl_Order.Tbl_Order_DTO;
import hungnt.tbl_OrderDetail.Tbl_OrderDetail_DAO;
import hungnt.tbl_OrderDetail.Tbl_OrderDetail_DTO;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author hungn
 */
public class CheckOutController extends HttpServlet {
    
    private static final Logger LOGGER = Logger.getLogger(CheckOutController.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        CartObject cart = (CartObject) session.getAttribute("shoppingCart");
        String url = AppConstants.ERROR;
        
        int cakeId = 0;
        int quantity = 0;
        try {
            String customerName = request.getParameter("txtCustomerName");
            String phoneNumber = request.getParameter("txtPhoneNumber");
            String address = request.getParameter("txtAddress");
            String paymentMethodIdString = request.getParameter("txtPaymentMethodId").trim();
            
            //Convert Strings to Integer
            int paymentMethodID = Integer.parseInt(paymentMethodIdString);
            
            //Get current date
            Timestamp orderDate = new Timestamp(System.currentTimeMillis());
            
            //Call Tbl_Cake_DAO to check stock
            Tbl_Cake_DAO cakeDAO = new Tbl_Cake_DAO();
            for (Tbl_Cake_DTO cake : cart.getCart().values()) {
                int inventoryQuantity = cakeDAO.getQuantity(cake.getCakeId());
                int cartQuantity = cake.getQuantity();
                if (inventoryQuantity < cartQuantity) {
                    url = AppConstants.CART_PAGE;
                    request.setAttribute("ERROR", "Cake " + cake.getCakeName() + " is out of stock. " + "Please choose other cakes.");
                    LOGGER.warn("Cake " + cake.getCakeName() + " is out of stock. ");
                    return;
                }
            }
            
            if (cart != null) {
                Tbl_Order_DTO order = new Tbl_Order_DTO(cart.getTotal(), customerName, address, phoneNumber, orderDate);
                order.setPaymentMethodId(paymentMethodID);
                
                String email = (String) session.getAttribute("EMAIL");
                if (email != null) {
                    order.setEmail(email);
                }
                
                Tbl_Order_DAO orderDAO = new Tbl_Order_DAO();
                
//                Insert order to DB
                int orderId = orderDAO.insertOrder(order);
                
                if (orderId >= 0) {
                    Tbl_OrderDetail_DAO orderDetailDAO = new Tbl_OrderDetail_DAO();
                    Tbl_OrderDetail_DTO orderDetailDTO = null;
                    
                    for (Map.Entry<Integer, Tbl_Cake_DTO> entry : cart.getCart().entrySet()) {
                        cakeId = entry.getValue().getCakeId();
                        quantity = entry.getValue().getQuantity();
                        
                        orderDetailDTO = new Tbl_OrderDetail_DTO(orderId, cakeId, quantity);
                        orderDetailDAO.insertOderDetail(orderDetailDTO);
                        
                        cakeDAO.updateQuantity(cakeId, quantity);
                    }
                    url = AppConstants.ORDER_DETAIL;
                    request.setAttribute("txtOrderId", "" + orderId);
                    LOGGER.info("Check out success!!");
                    //Only clear cart after a successful check out.
                    session.removeAttribute("shoppingCart");
                } else {
                    request.setAttribute("ERROR", "Failed to check out your order. Please try again later.");
                }
            }
        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage());
        } catch (NamingException ex) {
            LOGGER.error(ex.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
