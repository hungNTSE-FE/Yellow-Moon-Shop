/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungnt.controllers;

import hungnt.constants.AppConstants;
import hungnt.tbl_Cake.Tbl_Cake_DAO;
import hungnt.tbl_Cake.Tbl_Cake_DTO;
import hungnt.utils.FileUtils;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
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
public class UpdateCakeController extends HttpServlet {
    
    private static final Logger LOGGER = Logger.getLogger(UpdateCakeController.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = AppConstants.ERROR;
        try {
            //get data from create page
            String fileName = FileUtils.uploadFile(request);
            String cakeName = request.getParameter("txtCakeName").trim();
            String description = request.getParameter("txtDescription").trim();
            String createDateString = request.getParameter("txtCreateDate").trim();
            String quantityString = request.getParameter("txtQuantity").trim();
            String priceString = request.getParameter("txtPrice").trim();
            String expirationDateString = request.getParameter("txtExpirationDate").trim();
            String categoryIdString = request.getParameter("txtCategoryId").trim();
            String statusIdString = request.getParameter("txtStatusId").trim();
            String cakeIdString = request.getParameter("txtCakeId").trim();
            
            //Get current date
            Date lastModified = new Date(System.currentTimeMillis());
            
            //Get EMAIL
            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("EMAIL");
            
            //convert strings to integer
            int price = Integer.parseInt(priceString);
            int quantity = Integer.parseInt(quantityString);
            int categoryId = Integer.parseInt(categoryIdString);
            int statusId = Integer.parseInt(statusIdString);
            int foodId = Integer.parseInt(cakeIdString);
            
            //convert strings to date
            String[] expirationDateArr = expirationDateString.split("-");
            int expirationYear = Integer.parseInt(expirationDateArr[0]) - 1900;
            int expirationMonth = Integer.parseInt(expirationDateArr[1]) - 1;
            int expirationDay = Integer.parseInt(expirationDateArr[2]);
            Date expirationDate = new Date(expirationYear, expirationMonth, expirationDay);
            
            String[] createDateArr = expirationDateString.split("-");
            int createYear = Integer.parseInt(createDateArr[0]) - 1900;
            int createMonth = Integer.parseInt(createDateArr[1]) - 1;
            int createDay = Integer.parseInt(createDateArr[2]);
            Date createDate = new Date(createYear, createMonth, createDay);
            
            Tbl_Cake_DTO dto = new Tbl_Cake_DTO(foodId, categoryId, statusId, price, quantity,
                    cakeName, fileName, description, createDate, expirationDate);
            dto.setLastModified(lastModified);
            dto.setLastModifier(email);
            
            Tbl_Cake_DAO dao = new Tbl_Cake_DAO();
            if (dao.updateCake(dto)) {
                url = AppConstants.SEARCH;
                LOGGER.info("Update Cake Success!");
            } else {
                request.setAttribute("ERROR", "Failed to update cake.");
                LOGGER.info("Update Cake Fail!");
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
