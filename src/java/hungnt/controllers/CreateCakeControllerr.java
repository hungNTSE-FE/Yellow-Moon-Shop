
package hungnt.controllers;

import hungnt.constants.AppConstants;
import hungnt.tbl_Cake.Tbl_Cake_DAO;
import hungnt.tbl_Cake.Tbl_Cake_DTO;
import hungnt.utils.FileUtils;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author hungn
 */
@MultipartConfig(maxFileSize = 16177215) // 16MB
public class CreateCakeControllerr extends HttpServlet {
    
    private static final Logger LOGGER = Logger.getLogger(CreateCakeControllerr.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = AppConstants.ERROR;
        try {
            //get data from create page
            String fileName = FileUtils.uploadFile(request);
            String cakeName = request.getParameter("txtCakeName").trim();
            String description = request.getParameter("txtDescription").trim();
            String quantityString = request.getParameter("txtQuantity").trim();
            String priceString = request.getParameter("txtPrice").trim();
            String expirationDateString = request.getParameter("txtExpirationDate").trim();
            String categoryIdString = request.getParameter("txtCategoryId").trim();
            
            //convert String to Date
            String[] expirationDateArr = expirationDateString.split("-");
            int expirationYear = Integer.parseInt(expirationDateArr[0]) - 1900;
            int expirationMonth = Integer.parseInt(expirationDateArr[1]) - 1;
            int expirationDay = Integer.parseInt(expirationDateArr[2]);
            Date expirationDate = new Date(expirationYear, expirationMonth, expirationDay);
            
            //Check date
            Date currentDate = new Date(System.currentTimeMillis());

            if (expirationDate.before(currentDate)) {
                url = AppConstants.CREATE_PAGE;
                request.setAttribute("ERROR", "Please don't choose previous date from current date.");
                LOGGER.info("Invalid date of manufacture");
                return;
            }
            
            //convert Strings to Integer
            int price = Integer.parseInt(priceString);
            int quantity = Integer.parseInt(quantityString);
            int categoryID = Integer.parseInt(categoryIdString);
            

            Tbl_Cake_DTO dto = new Tbl_Cake_DTO(categoryID, price, quantity, cakeName, fileName, description, expirationDate);
            Tbl_Cake_DAO dao = new Tbl_Cake_DAO();
            if (dao.insertCake(dto)) {
                url = AppConstants.SEARCH;
                LOGGER.info("Create Cake Success!");
            } else {
                request.setAttribute("ERROR", "Create failed");
                LOGGER.info("Create Cake Fail!");
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
