/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungnt.controllers;

import hungnt.cart.CartObject;
import hungnt.constants.AppConstants;
import hungnt.tbl_Cake.Tbl_Cake_DTO;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hungn
 */
public class AddToCartController extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = AppConstants.ERROR;
        try {
            //get shopping cart from session
            //initialize cart if adding to cart for the first time.
            HttpSession session = request.getSession();
            CartObject cart = (CartObject) session.getAttribute("shoppingCart");
            if (cart == null) {
                cart = new CartObject();
            }
            
            String cakeIdString = request.getParameter("txtCakeId");
            String cakeName = request.getParameter("txtCakeName");
            String cakeImage = request.getParameter("txtImage");
            String description = request.getParameter("txtDescription");
            String priceString = request.getParameter("txtPrice");
            String expirationDateString = request.getParameter("txtExpirationDate");
            
            //convert String To Date
            String[] expirationDateArr = expirationDateString.split("-");
            int expirationYear = Integer.parseInt(expirationDateArr[0]) - 1900;
            int expirationMonth = Integer.parseInt(expirationDateArr[1]) - 1;
            int expirationDay = Integer.parseInt(expirationDateArr[2]);
            Date expirationDate = new Date(expirationYear, expirationMonth, expirationDay);
            
            int price = 0;
            int cakeId = 0;
            if (isNumeric(priceString)) {
                price = Integer.parseInt(priceString);
            }
            if (isNumeric(cakeIdString)) {
                cakeId = Integer.parseInt(cakeIdString);
            }
            
            Tbl_Cake_DTO dto = new Tbl_Cake_DTO();
            dto.setCakeId(cakeId);
            dto.setCakeName(cakeName);
            System.out.println("cakeName: " + dto.getCakeName());
            dto.setCakeImage(cakeImage);
            dto.setDescription(description);
            dto.setPrice(price);
            dto.setQuantity(1);
            dto.setExpirationDate(expirationDate);
            
            cart.addToCart(dto);
            session.setAttribute("shoppingCart", cart);
            url = AppConstants.SEARCH;
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }
    
    private static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
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
