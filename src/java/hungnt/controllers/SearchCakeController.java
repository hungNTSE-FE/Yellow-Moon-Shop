
package hungnt.controllers;

import hungnt.constants.AppConstants;
import hungnt.tbl_Cake.Tbl_Cake_DAO;
import hungnt.tbl_Cake.Tbl_Cake_DTO;
import hungnt.tbl_Category.Tbl_Category_DAO;
import hungnt.tbl_Order.Tbl_Order_DAO;
import hungnt.tbl_Order.Tbl_Order_DTO;
import hungnt.tbl_PaymentMethod.Tbl_PaymentMethod_DAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
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
public class SearchCakeController extends HttpServlet {
    
    private static final Logger LOGGER = Logger.getLogger(SearchCakeController.class);
    private static final int RECORDS_PER_PAGE = 20;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = AppConstants.ERROR;
        HttpSession session = request.getSession();
        try {
            //get search type (default is search by name)
            String searchAction = "searchByName";
            if (request.getParameter("searchAction") != null) {
                searchAction = request.getParameter("searchAction");
            }
            
            //get search values
            String searchValue;
            if (request.getParameter("txtSearch") == null) {
                searchValue = "";
            } else {
                searchValue = request.getParameter("txtSearch");
            }
            
            //get selected page
            int page = 1;
            if (request.getParameter("SELECTEDPAGE") != null) {
                page = Integer.parseInt(request.getParameter("SELECTEDPAGE"));
            }
            
            int noOfRecords = 0;
            
            //Call DAO to get cake list and total number of records
            Tbl_Cake_DAO dao = new Tbl_Cake_DAO();
            List<Tbl_Cake_DTO> cakeList = null;
            
            switch (searchAction) {
                case "searchByName":
                    cakeList = dao.getCakeByName(searchValue,
                            (page - 1) * RECORDS_PER_PAGE, RECORDS_PER_PAGE);
                    noOfRecords = dao.getCakeRowCountByName(searchValue);
                    break;
                case "searchByCategory":
                    String  categoryIdString = request.getParameter("txtCategoryId");
                    System.out.println("cate: " + categoryIdString);
                    int categoryId = 0;
                    if (categoryIdString != null) {
                        categoryId = Integer.parseInt(categoryIdString.trim());
                    }
                    
                    cakeList = dao.getCakeByCategory(categoryId,
                            (page - 1) * RECORDS_PER_PAGE, RECORDS_PER_PAGE);
                    noOfRecords = dao.getCakeRowCount(categoryId);
                    
                    //set selected category value to request.
                    String categoryName = request.getParameter("txtCategoryName");
                    if (categoryName != null) {
                        request.setAttribute("selectedCategory", categoryName);
                    }
                    
                    //set previous search type to request for correct paging
                    request.setAttribute("previousSearch", "CATEGORY");
                    LOGGER.info("Search By Category Success");
                    break;
                case "searchByPrice":
                    String minPriceString = request.getParameter("txtMinPrice");
                    String maxPriceString = request.getParameter("txtMaxPrice");
                    int minPrice = 0;
                    int maxPrice = 0;
                    if (isNumeric(minPriceString) && isNumeric(maxPriceString)) {
                        minPrice = Integer.parseInt(minPriceString);
                        maxPrice = Integer.parseInt(maxPriceString);
                    }
                    
                    cakeList = dao.getCakeByPrice(minPrice, maxPrice,
                            (page - 1) * RECORDS_PER_PAGE, RECORDS_PER_PAGE);
                    noOfRecords = dao.getCakeRowCount(minPrice, maxPrice);
                    
                    //set previous search type to request for correct paging
                    request.setAttribute("previousSearch", "PRICE");
                    LOGGER.info("Search By Price Success");
                    break;
                case "searchByOrder":
                    String orderIdString = request.getParameter("txtSearchOrder");
                    int orderId = 0;
                    if (orderIdString != null) {
                        orderId = Integer.parseInt(orderIdString.trim());
                    }
//                    
                    Tbl_Order_DAO orderHistoryDAO = new Tbl_Order_DAO();
                    String email = (String) session.getAttribute("EMAIL");
                    if (email != null) {
                        List<Tbl_Order_DTO> orders = orderHistoryDAO.getOrdersByEmailAndOrderId(email, orderId);
                        if (orders.size() > 0) {
                            LOGGER.info("OrderId: " + orderId + " is found.");
                        } else {
                            LOGGER.info("OrderId: " + orderId + " is not found.");
                        }
                        request.setAttribute("ORDERS", orders);
                        request.getRequestDispatcher(AppConstants.ORDER_HISTORY_PAGE).forward(request, response);
                    }
                    break;
                    
                    
                default:
                    request.setAttribute("ERROR", "Search action not supported.");
                    LOGGER.info("Search Action Fail!");
                    break;
            }
            
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / RECORDS_PER_PAGE);
            
            //load categories on navigation bar.
            Tbl_Category_DAO categoryDAO = new Tbl_Category_DAO();
            
            //load paymentMethods on dropdown.
            Tbl_PaymentMethod_DAO paymentDAO = new Tbl_PaymentMethod_DAO();
            
            Map<Integer, String> categoryMap = categoryDAO.getAllCategories();
            Map<Integer, String> paymentMethodMap = paymentDAO.getAllPaymentMethod();
            session.setAttribute("CATEGORIES", categoryMap);
            session.setAttribute("PAYMENTMETHODS", paymentMethodMap);
            
            request.setAttribute("INFO", cakeList);
            request.setAttribute("PAGENUM", noOfPages);
            request.setAttribute("SELECTEDPAGE", page);
            url = AppConstants.HOME_PAGE;
            
        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage());
        } catch (NamingException ex) {
            LOGGER.error(ex.getMessage());
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
