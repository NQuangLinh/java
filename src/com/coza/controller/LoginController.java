package com.coza.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.coza.dao.LoginDAO;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("idCustomer");
		session.removeAttribute("idEmployee");
		session.removeAttribute("idManager");
		session.removeAttribute("accountname");
		response.sendRedirect("CustomerController");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		loginCheck(request, response);
	}

	private void loginCheck(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException
	
	{
		String accountname = request.getParameter("accountname");
		String password = request.getParameter("password");
		String passwordMD5 = md5(password);
		int idCustomer = LoginDAO.checkLoginCustomer(accountname, passwordMD5);
		if (idCustomer != 0) {
			HttpSession session = request.getSession();
			session.setAttribute("idCustomer", idCustomer);
			session.setAttribute("accountname", accountname);
			response.sendRedirect("CustomerController");
		} else {
			int idAccount = LoginDAO.checkLogin(accountname, passwordMD5);
			if ( idAccount != 0) {
				int idEmployee = LoginDAO.getIDEmployee(idAccount);
				HttpSession session = request.getSession();
				if(idEmployee != 0)
				{
					session.setAttribute("idEmployee", idEmployee);
				}
				else
				{
					int idManager = LoginDAO.getIDManager(idAccount);
					session.setAttribute("idManager", idManager);
				}	
				session.setAttribute("accountname", accountname);
				response.sendRedirect("AdminController");
			} else {
				request.setAttribute("message", "Please check your account name or password.");
				request.getRequestDispatcher("Login.jsp").forward(request, response);
			}
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
