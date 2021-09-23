/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungnt.controllers;

import hungnt.cart.CartObject;
import hungnt.constants.AppConstants;
import java.io.IOException;
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
public class UpdateCartItemController extends HttpServlet {
    
    private static final Logger LOGGER = Logger.getLogger(UpdateCartItemController.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String quantity = request.getParameter("txtQuantity");
        String cakeId = request.getParameter("txtCakeId");
        String url = AppConstants.ERROR;
        try {
            HttpSession session = request.getSession();
            CartObject cart = (CartObject) session.getAttribute("shoppingCart");
            int quantityInt = Integer.parseInt(quantity);

            if (cart.getCartSize() < 1) {
                LOGGER.warn("Cart null!");
                cart = null;
            }
            if (quantityInt < 1) {
                cart.remove(Integer.parseInt(cakeId));
                LOGGER.warn("Delete cake in cart!");
            } else {
                cart.update(Integer.parseInt(cakeId), quantityInt);
                LOGGER.info("Update the quantity of cakes in the cart!");
            }
            session.setAttribute("shoppingCart", cart);
            response.sendRedirect(AppConstants.CART_PAGE);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
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
