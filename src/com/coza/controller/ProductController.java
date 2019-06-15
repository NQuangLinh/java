package com.coza.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.coza.dao.LoadDAO;
import com.coza.dao.ProductDAO;
import com.coza.model.Categories;
import com.coza.model.Product;

@WebServlet("/ProductController")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idProductEdit =  request.getParameter("idProductEdit");
		showEditProduct(idProductEdit,request, response);
	}

	private void showEditProduct(String idProductEdit, HttpServletRequest request, HttpServletResponse response) {
		try {
		int idProductEditInt = Integer.parseInt(idProductEdit);
		Product productDetail= LoadDAO.getProductDetail(idProductEditInt);
		List<Categories> listCategories = LoadDAO.getAllCategories();
		request.setAttribute("productDetail", productDetail);
		request.setAttribute("listCategories", listCategories);
		RequestDispatcher rd = request.getRequestDispatcher("EditProduct.jsp");
		
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		insertProduct(request, response);

	}

	private void insertProduct(HttpServletRequest request, HttpServletResponse response) {
		try {
			int idProduct = Integer.parseInt(request.getParameter("idProductEdit"));
			String nameProduct = request.getParameter("nameProduct");
			int idCategories = Integer.parseInt(request.getParameter("categories"));
			Float price = Float.parseFloat(request.getParameter("price"));
			String infoproduct = request.getParameter("infoProduct");
			Part part = request.getPart("file");
			String fileName = extractFileName(part);
			String savePath = "C:\\Users\\Nguyen Vu Quang Linh\\eclipse-workspace\\CoZaStore\\WebContent\\images"
					+ File.separator + fileName;
			new File(savePath);
			part.write(savePath + File.separator);
			
			Product product = new Product(idProduct,nameProduct, idCategories, price, infoproduct, fileName, savePath);
			ProductDAO.updateProduct(product);
			ProductDAO.updateImage(product);
			RequestDispatcher rd = request.getRequestDispatcher("AdminController");
			rd.forward(request, response);
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

}
