package com.coza.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.coza.dao.LoadDAO;
import com.coza.dao.LoginDAO;
import com.coza.model.Categories;
import com.coza.model.Product;
import com.coza.model.ProductAdv;
import com.coza.dao.CustomerDAO;
import com.coza.dao.SignUpDAO;
import com.coza.model.Customer;


public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CustomerController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    HttpSession session = request.getSession();
	    java.lang.Integer  userSession = (java.lang.Integer) session.getAttribute("idCustomer");
		if(userSession == null)
		{	
			String action = request.getServletPath();
			if(action.equals("/") 
					|| action.equals("/CustomerController"))
			{
				listCategories(userSession,request, response);
				
			}
			else if(action.equals("/ProductCustomer"))
			{
				showProduct(userSession,request, response);
			}
			else if(action.equals("/ProductDetail"))
			{
				int idProduct =Integer.parseInt(request.getParameter("idProduct"));
				showProductDetail(userSession,idProduct,request, response);
			}
			else
			{
				response.sendRedirect("Login.jsp");
			}
		}
		else
		{
			String action = request.getServletPath();
			switch (action) {
			case "/ProductCustomer":
				showProduct(userSession,request, response);
				break;
				
			case "/ProductDetail":
				int idProduct =Integer.parseInt(request.getParameter("idProduct"));
				showProductDetail(userSession,idProduct,request, response);
				break;
				
			case "/MyCart":
				showMyCart(userSession, request, response);
				break;
			case "/AddCart":
				int idProductAdd =Integer.parseInt(request.getParameter("idProductAdd"));
				int numProduct =Integer.parseInt(request.getParameter("num-product"));
				insertProductinCart(idProductAdd,numProduct ,userSession,request, response);
				break;
			case "/DeleteProductinCart":
				int idProductinCart =Integer.parseInt(request.getParameter("idProductinCart"));
				deleteProductinCart(idProductinCart,userSession,request, response);
				break;
			case "/RemoveProductinCart":
				int idProductinCartRemove =Integer.parseInt(request.getParameter("idProductinCartRemove"));
				removeProductinCart(idProductinCartRemove,userSession,request, response);
				break;
			case "/AddProductinCart":
				int idProductinCartAdd =Integer.parseInt(request.getParameter("idProductinCartAdd"));
				addProductinCart(idProductinCartAdd,userSession,request, response);
				break;
			case "/MyAccount":
				myAccount(userSession,request, response);
				
				break;
			case "/UpdateMyAccount":
				updateMyAccount(userSession,request, response);
				
				break;
			default:
				listCategories(userSession,request, response);
				break;
			}
		
		}
		
		
		
	}



	private void updateMyAccount(Integer userSession, HttpServletRequest request, HttpServletResponse response) {
		String fullName = request.getParameter("fullName");
		String address = request.getParameter("address");
		String phoneNumber = request.getParameter("phoneNumber");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String passwordMD5 = md5(password);
		Customer customer = new Customer(userSession,fullName, address,phoneNumber, email,passwordMD5);
		LoginDAO.updateMyProfile(customer);
		LoginDAO.updateMyAccount(customer);
		listCategories(userSession,request, response);
	}

	private void loadAboutCart(Integer userSession, HttpServletRequest request, HttpServletResponse response) {
		List<Product> listProductinCart = CustomerDAO.getCartProduct(userSession);
		float cartTotals = 0;
		for (Product product : listProductinCart) {
			cartTotals = cartTotals + product.getTotal();
		}
		int countAllProduct = CustomerDAO.countProduct(userSession);
		request.setAttribute("cartTotals", cartTotals);
		request.setAttribute("countAllProduct", countAllProduct);
		request.setAttribute("listProductinCart", listProductinCart);
	}

	private void myAccount(Integer userSession, HttpServletRequest request, HttpServletResponse response) {
		try {
			loadAboutCart(userSession,request, response);
			Customer customer = LoginDAO.getMyAccount(userSession);
			request.setAttribute("customer", customer);
			RequestDispatcher rd = request.getRequestDispatcher("MyAccount.jsp");
			rd.forward(request, response);
		} catch (ServletException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	private void addProductinCart(int idProductinCartAdd,int userSession, HttpServletRequest request, HttpServletResponse response) {
		CustomerDAO.addProductinCart(idProductinCartAdd);
		CustomerDAO.updateCountProduct(userSession);
		showMyCart(userSession, request, response);
	}

	private void removeProductinCart(int idProductinCartRemove,int userSession, HttpServletRequest request,
			HttpServletResponse response) {
		CustomerDAO.removeProductinCart(idProductinCartRemove);
		CustomerDAO.updateCountProduct(userSession);
		showMyCart(userSession, request, response);
	}

	private void deleteProductinCart(int idProductinCart, int userSession, HttpServletRequest request,
			HttpServletResponse response) {
		CustomerDAO.deleteProductinCart(idProductinCart);
		CustomerDAO.updateCountProduct(userSession);
		showMyCart(userSession, request, response);
		
	}

	private void insertProductinCart(int idProductAdd,int numProduct , int userSession, HttpServletRequest request,
			HttpServletResponse response) {
		int idProductinCart = CustomerDAO.checkProductinCart(idProductAdd,userSession);
		if (idProductinCart != 0)
		{
			CustomerDAO.updateCountProductinCart(idProductinCart,numProduct);
		}
		else
		{
			CustomerDAO.insertProductinCart(idProductAdd, userSession ,numProduct);
		}
	CustomerDAO.updateCountProduct(userSession);
	showMyCart(userSession, request, response);
		
	}

	private void showMyCart(int userSession, HttpServletRequest request, HttpServletResponse response) {
		try {
			loadAboutCart(userSession,request, response);
			RequestDispatcher rd = request.getRequestDispatcher("ShopingCart.jsp");
			rd.forward(request, response);
		} catch (ServletException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	private void showProductDetail(java.lang.Integer userSession,int idProduct, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
				try {
					Product productDetail= LoadDAO.getProductDetail(idProduct);
					List<Product> listProduct = LoadDAO.getAllProduct();
					if( userSession != null)
					{
						loadAboutCart(userSession,request, response);
					}
					request.setAttribute("listProduct", listProduct);
					request.setAttribute("productDetail", productDetail);
					RequestDispatcher rd = request.getRequestDispatcher("ProductDetail.jsp");
					rd.forward(request, response);
				} catch (ServletException e) {

					e.printStackTrace();
				} catch (IOException e) {

					e.printStackTrace();
				}
	}

	private void showProduct(java.lang.Integer userSession,HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			List<Categories> listCategories = LoadDAO.getAllCategories();
			List<Product> listProduct = LoadDAO.getAllProduct();
			if( userSession != null)
			{
				loadAboutCart(userSession,request, response);
			}
			request.setAttribute("listProduct", listProduct);
			request.setAttribute("listCategories", listCategories);
			RequestDispatcher rd = request.getRequestDispatcher("ProductCustomer.jsp");
			rd.forward(request, response);
		} catch (ServletException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	private void listCategories(java.lang.Integer userSession,HttpServletRequest request, HttpServletResponse response) {

		try {
			List<Categories> listCategories = LoadDAO.getAllCategories();
			List<ProductAdv> listProductAdvinSlider = LoadDAO.getAllProductAdvinSlider();
			List<ProductAdv> listProductAdvinBanner = LoadDAO.getAllProductAdvinBanner();
			List<Product> listProduct = LoadDAO.getAllProduct();
			if( userSession != null)
			{
				loadAboutCart(userSession,request, response);
			}
			request.setAttribute("listProduct", listProduct);
			request.setAttribute("listCategories", listCategories);
			request.setAttribute("listProductAdvinSlider", listProductAdvinSlider);
			request.setAttribute("listProductAdvinBanner", listProductAdvinBanner);
			RequestDispatcher rd = request.getRequestDispatcher("HomeCustomer.jsp");
			rd.forward(request, response);
		} catch (ServletException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		insertCustomer(request, response);
	}
	private void insertCustomer(HttpServletRequest request, HttpServletResponse response) {

		try {
			String fullname = request.getParameter("fullname");
			String address = request.getParameter("address");
			String phonenumber = request.getParameter("phonenumber");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String passwordagain = request.getParameter("passwordagain");
			if (!password.equals(passwordagain)) {
				request.setAttribute("message", "Please check your password!");
				RequestDispatcher rd = request.getRequestDispatcher("SignUp.jsp");
				rd.forward(request, response);
			}
			if (SignUpDAO.checkEmailCustomer(email) == true || SignUpDAO.checkEmailAdmin(email) == true) {
				request.setAttribute("message", "Please. This account has already existed.");
				RequestDispatcher rd = request.getRequestDispatcher("SignUp.jsp");
				rd.forward(request, response);
			}
			String passwordMD5 = md5(password);
			Customer customer = new Customer(fullname,address, phonenumber,email,passwordMD5);
			SignUpDAO.insertAccountCustomer(customer);
			SignUpDAO.insertCustomer(customer);
			SignUpDAO.insertCart(customer);
			request.setAttribute("messageSuccess", "Sign Up Success!");
			
			RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
			rd.forward(request, response);
		} catch (Exception e) {

		}

	}

	public static String md5(String str){
		String result = "";
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("MD5");
			digest.update(str.getBytes());
			BigInteger bigInteger = new BigInteger(1,digest.digest());
			result = bigInteger.toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}
}
