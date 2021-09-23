
package hungnt.controllers;

import hungnt.constants.AppConstants;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hungn
 */
@MultipartConfig
public class MainController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String action = request.getParameter("btnAction");
        System.out.println("action: " + action);
        String url = AppConstants.ERROR;
        try {
            if (action == null) {
                url = AppConstants.SEARCH;
            } else if (action.equals("Login")) {
                System.out.println("url is login");
                url = AppConstants.LOGIN;
            } else if (action.equals("Logout")) {
                url = AppConstants.LOGOUT;
            } else if (action.equals("Search")) {
                url = AppConstants.SEARCH;
            } else if (action.equals("Create")) {
                url = AppConstants.CREATE;
            } else if (action.equals("Update")) {
                url = AppConstants.UPDATE;
            } else if (action.equals("Detail")) {
                url = AppConstants.DETAIL;
            } else if (action.equals("Delete")) {
                url = AppConstants.DELETE;
            } else if (action.equals("Add to cart")) {
                url = AppConstants.ADD_TO_CART;
            } else if (action.equals("Update Cart")) {
                url  = AppConstants.UPDATE_CART_ITEM;
            } else if (action.equals("Delete Cart")) {
                url = AppConstants.DELETE_CART_ITEM;
            } else if (action.equals("Check Out")) {
                url = AppConstants.CHECKOUT;
            } else if (action.equals("Order History")) {
                url = AppConstants.ORDER_HISTORY;
            } else if (action.equals("Order Detail")) {
                url = AppConstants.ORDER_DETAIL;
            } else {
                request.setAttribute("ERROR", "Action not found.");
            }
        } catch (Exception e) {
            request.setAttribute("ERROR", e.getMessage());
            log("ERROR at MainController: " + e.getMessage());
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
