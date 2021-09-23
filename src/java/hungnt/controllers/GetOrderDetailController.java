/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungnt.controllers;

import hungnt.constants.AppConstants;
import hungnt.tbl_Cake.Tbl_Cake_DAO;
import hungnt.tbl_Cake.Tbl_Cake_DTO;
import hungnt.tbl_Order.Tbl_Order_DAO;
import hungnt.tbl_Order.Tbl_Order_DTO;
import hungnt.tbl_OrderDetail.Tbl_OrderDetail_DAO;
import hungnt.tbl_OrderDetail.Tbl_OrderDetail_DTO;
import hungnt.tbl_PaymentMethod.Tbl_PaymentMethod_DAO;
import hungnt.tbl_PaymentStatus.Tbl_PaymentStatus_DAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author hungn
 */
public class GetOrderDetailController extends HttpServlet {
    
    private static final Logger LOGGER = Logger.getLogger(GetOrderDetailController.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = AppConstants.ERROR;
        try {
            String orderIdString = null;
            if (request.getAttribute("txtOrderId") != null) {
                orderIdString = (String) request.getAttribute("txtOrderId");
                request.setAttribute("SUCCESS", "Check out successfully.");
            } else {
                orderIdString = request.getParameter("txtOrderId");
            }
            
            int orderId = -1;
            if (orderIdString != null) {
                orderId = Integer.parseInt(orderIdString.trim());
            }
            
            Tbl_Order_DAO orderDAO = new Tbl_Order_DAO();
            Tbl_Order_DTO order = orderDAO.getOrdersById(orderId);
            
            if (order != null) {
                request.setAttribute("ORDER", order);
                //get payment method name with id
                Tbl_PaymentMethod_DAO paymentMethodDAO = new Tbl_PaymentMethod_DAO();
                Map<Integer, String> paymentMethod = paymentMethodDAO.getPaymentMethodById(order.getPaymentMethodId());
                request.setAttribute("PAYMENTMETHODWITHID", paymentMethod);
                
                //get payment status name with id
                Tbl_PaymentStatus_DAO paymentStatusDAO = new Tbl_PaymentStatus_DAO();
                Map<Integer, String> paymentStatus = paymentStatusDAO.getStatusById(order.getPaymentStatusId());
                request.setAttribute("PAYMENTSTATUS", paymentStatus);
                
                
                Tbl_OrderDetail_DAO orderDetailDAO = new Tbl_OrderDetail_DAO();
                List<Tbl_OrderDetail_DTO> orderDetailDTO = orderDetailDAO.getOrderDetailsByOrderId(orderId);
                
                request.setAttribute("ORDERDETAIL", order);
                
                Tbl_Cake_DAO cakeDAO = new Tbl_Cake_DAO();
                List<Tbl_Cake_DTO> listCakes = new ArrayList<>();
                
                for (Tbl_OrderDetail_DTO orderDetail : orderDetailDTO) {
                    Tbl_Cake_DTO cake = cakeDAO.getCakeById(orderDetail.getCakeId());
                    cake.setQuantity(orderDetail.getQuantity());
                    listCakes.add(cake);
                }
                request.setAttribute("PRODUCTS", listCakes);
                url = AppConstants.ORDER_DETAIL_PAGE;
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
