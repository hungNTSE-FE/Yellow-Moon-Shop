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
public class AppConstants {
    
    public static final String ERROR = "error.jsp";
    public static final String HOME_PAGE = "dashboard.jsp";
    public static final String LOGIN_PAGE = "login.html";
    public static final String CREATE_PAGE = "createCake.jsp";
    public static final String UPDATE_PAGE = "updateCake.jsp";
    public static final String CART_PAGE = "cart.jsp";
    public static final String ORDER_DETAIL_PAGE = "orderdetail.jsp";
    public static final String ORDER_HISTORY_PAGE = "orderhistory.jsp";
    
    public static final String LOGIN = "LoginController";
    public static final String LOGOUT = "LogoutController";
    public static final String SEARCH = "SearchCakeController";
    public static final String CREATE = "CreateCakeControllerr";
    public static final String UPDATE = "UpdateCakeController";
    public static final String DETAIL = "GetCakeDetailController";
    public static final String DELETE = "DeleteCakeController";
    
    public static final String ADD_TO_CART = "AddToCartController";
    public static final String UPDATE_CART_ITEM = "UpdateCartItemController";
    public static final String DELETE_CART_ITEM = "DeleteCartItemController";
    public static final String CHECKOUT = "CheckOutController";
    
    public static final String ORDER_DETAIL = "GetOrderDetailController";
    public static final String ORDER_HISTORY = "GetOrderHistoryController";
    
    public static final String IMAGE_PATH = "./resources/images/";
    
    public static String GOOGLE_CLIENT_ID = "856688289616-e521vj4emlmfp6lguvpe7b65e7sljvbh.apps.googleusercontent.com";
    public static String GOOGLE_CLIENT_SECRET = "Zx9qAsaU6_feo6Czo6WhvIHI";
    public static String GOOGLE_REDIRECT_URI = "http://localhost:8080/YellowMoonShop/login-google";
    public static String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";
    public static String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";
    public static String GOOGLE_GRANT_TYPE = "authorization_code";
}
