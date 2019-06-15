package com.coza.controller;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.coza.dao.AdminDAO;
import com.coza.dao.LoadDAO;
import com.coza.dao.LoginDAO;
import com.coza.dao.SignUpDAO;
import com.coza.model.Categories;
import com.coza.model.Customer;
import com.coza.model.Product;

/**
 * Servlet implementation class AdminController
 */
@WebServlet("/AdminController")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		java.lang.Integer  adminSession = (java.lang.Integer) session.getAttribute("idEmployee");
		if(adminSession == null)
		{

			response.sendRedirect("Login.jsp");
		}
		else{
			String page = request.getParameter("page");
			String idProductDlt =  request.getParameter("idProductDlt");
			try {
				

			if(idProductDlt != null)
			{
				deleteProduct(idProductDlt,request, response);
			}
			if (page.equals("newProduct")) {
					showFormNewProduct(request, response);
			}
			if (page.equals("myAccount")) {
				showMyAccount(adminSession,request, response);
			}
			} catch (Exception e) {
				listCategories(adminSession,request, response);
			}
		}
	}

	private void showMyAccount(Integer adminSession, HttpServletRequest request, HttpServletResponse response) {
		try {
			Customer customer = LoginDAO.getMyAccountAdmin(adminSession);
			request.setAttribute("customer", customer);
			RequestDispatcher rd = request.getRequestDispatcher("MyAccount.jsp");
			rd.forward(request, response);
		} catch (ServletException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	private void deleteProduct(String idProductDlt, HttpServletRequest request, HttpServletResponse response) {
		int idProductDltInt = Integer.parseInt(idProductDlt);
		AdminDAO.deleteProductImage(idProductDltInt);
		AdminDAO.deleteProductinCart(idProductDltInt);
		AdminDAO.deleteProduct(idProductDltInt);
	}

	private void showFormNewProduct(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			List<Categories> listCategories = LoadDAO.getAllCategories();
			request.setAttribute("listCategories", listCategories);
			RequestDispatcher rd = request.getRequestDispatcher("NewProduct.jsp");
			rd.forward(request, response);
		} catch (ServletException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	private void listCategories(int adminSession, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Categories> listCategories = LoadDAO.getAllCategories();
			List<Product> listProduct = LoadDAO.getAllProduct();
			request.setAttribute("listProduct", listProduct);
			request.setAttribute("listCategories", listCategories);
			RequestDispatcher rd = request.getRequestDispatcher("HomeAdmin.jsp");
			rd.forward(request, response);
		} catch (ServletException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		java.lang.Integer  adminSession = (java.lang.Integer) session.getAttribute("idEmployee");
		if(adminSession == null)
		{

			response.sendRedirect("Login.jsp");
		}
		else{
			String page = request.getParameter("page");
			try {
				if (page.equals("insertProduct")) {
					insertNewProduct(request, response);
				}
				if (page.equals("signUpAdmin")) {
					insertAdmin(request, response);
				}
				if (page.equals("updateMyAccount")) {
					updateMyAccountAdmin(adminSession,request, response);
				}
			} catch (Exception e) {
				listCategories(adminSession,request, response);
			}

		}
	}

	private void updateMyAccountAdmin(Integer adminSession, HttpServletRequest request, HttpServletResponse response) {
		String fullName = request.getParameter("fullName");
		String address = request.getParameter("address");
		String phoneNumber = request.getParameter("phoneNumber");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String passwordMD5 = md5(password);
		Customer customer = new Customer(adminSession,fullName, address,phoneNumber, email,passwordMD5);
		LoginDAO.updateMyProfileAdmin(customer);
		LoginDAO.updateMyAccountAdmin(customer);
		listCategories(adminSession,request, response);
	}

	private void insertAdmin(HttpServletRequest request, HttpServletResponse response) {
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
			System.out.println(passwordMD5);
			Customer customer = new Customer(fullname,address, phonenumber,email,passwordMD5);
			SignUpDAO.insertAccountAdmin(customer);
			SignUpDAO.insertAdmin(customer);
			request.setAttribute("messageSuccess", "Sign Up Success!");
			
			RequestDispatcher rd = request.getRequestDispatcher("SignUp.jsp");
			rd.forward(request, response);
		} catch (Exception e) {

		}

	}

	private void insertNewProduct(HttpServletRequest request, HttpServletResponse response) {
		try {
			String nameProduct = request.getParameter("nameProduct");
			int idCategories = Integer.parseInt(request.getParameter("categories"));
			Float price = Float.parseFloat(request.getParameter("price"));
			String infoProduct = request.getParameter("infoProduct");

			Part part = request.getPart("file");
			String fileName = extractFileName(part);
			String savePath = "C:\\Users\\Nguyen Vu Quang Linh\\eclipse-workspace\\CoZaStore\\WebContent\\images"
					+ File.separator + fileName;
			new File(savePath);
			part.write(savePath + File.separator);
			Product product = new Product(nameProduct, idCategories, price, infoProduct, fileName, savePath);
			AdminDAO.insertNewProduct(product);
			int idProduct = AdminDAO.getIdProduct(product);
			AdminDAO.insertImage(idProduct, product);
			request.setAttribute("message", "Insert Successful!");
			showFormNewProduct(request, response);
		} catch (Exception e) {

		}
		
	}

	private String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
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
