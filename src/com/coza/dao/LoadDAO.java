package com.coza.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.coza.model.Product;
import com.coza.model.ProductAdv;
import com.coza.db.ConnectionDB;
import com.coza.model.Categories;

public class LoadDAO {

	public static List<Categories> getAllCategories() {
		Connection conn = ConnectionDB.getConnectionDB();
		List<Categories> listCategories = new ArrayList<Categories>();
		String sql = "SELECT * FROM DanhMucSP";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int idCategories = rs.getInt("IDDanhMuc");
				String nameCategories = rs.getString("TenDM");
	
				Categories categories = new Categories(idCategories, nameCategories);
				listCategories.add(categories);
			}
			rs.close();
			statement.close();
			conn.close();
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
		return listCategories;
	}

	public static List<Product> getAllProduct() {
		Connection conn = ConnectionDB.getConnectionDB();
		List<Product> listProduct = new ArrayList<Product>();
		String sql = "SELECT SanPham.IDSanPham,TenSP,IDDanhMuc,GiaBan,ThongTin,TenFile FROM SanPham LEFT JOIN HinhAnhSP ON SanPham.IDSanPham=HinhAnhSP.IDSanPham";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int idproduct = rs.getInt("IDSanPham");
				String nameproduct = rs.getString("TenSP");
				int idCategories = rs.getInt("IDDanhMuc");
				Float price = rs.getFloat("GiaBan");
				String infoproduct = rs.getString("ThongTin");
				String fileName = "images/" + rs.getString("TenFile");
				Product product = new Product(idproduct, nameproduct,idCategories, price, infoproduct, fileName);
				listProduct.add(product);
			}
			rs.close();
			statement.close();
			conn.close();
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
		return listProduct;
	}

	public static List<ProductAdv> getAllProductAdvinSlider() {
		Connection conn = ConnectionDB.getConnectionDB();
		List<ProductAdv> listProductAdvinSlider = new ArrayList<ProductAdv>();
		String sql = "SELECT * FROM SPQuangCao WHERE ViTri = 'Slider'";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int idproductAdv = rs.getInt("IDSPQuangCao");
				String titleAdv = rs.getString("TieuDe");
				String describeAdv = rs.getString("MoTa");
				String fileNameAdv ="images/" + rs.getString("TenFile");
				ProductAdv productAdv = new ProductAdv(idproductAdv, titleAdv,describeAdv,fileNameAdv);
				listProductAdvinSlider.add(productAdv);
			}
			rs.close();
			statement.close();
			conn.close();
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
		return listProductAdvinSlider;
	}

	public static List<ProductAdv> getAllProductAdvinBanner() {
		Connection conn = ConnectionDB.getConnectionDB();
		List<ProductAdv> listProductAdvinBanner = new ArrayList<ProductAdv>();
		String sql = "SELECT * FROM SPQuangCao WHERE ViTri = 'Banner'";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int idproductAdv = rs.getInt("IDSPQuangCao");
				String titleAdv = rs.getString("TieuDe");
				String describeAdv = rs.getString("MoTa");
				String fileNameAdv ="images/" + rs.getString("TenFile");
				ProductAdv productAdv = new ProductAdv(idproductAdv, titleAdv,describeAdv,fileNameAdv);
				listProductAdvinBanner.add(productAdv);
			}
			rs.close();
			statement.close();
			conn.close();
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
		return listProductAdvinBanner;
	}

	public static Product getProductDetail(int idProduct) {
		Connection conn = ConnectionDB.getConnectionDB();
	    Product productDetail = null ;
		String sql = "SELECT SanPham.IDSanPham,TenSP,TenDM,GiaBan,ThongTin,ThoiGianThem,TenFile FROM SanPham LEFT JOIN HinhAnhSP ON SanPham.IDSanPham=HinhAnhSP.IDSanPham LEFT JOIN DanhMucSP ON SanPham.IDDanhMuc = DanhMucSP.IDDanhMuc WHere SanPham.IDSanPham = ?";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, idProduct);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int idproduct = rs.getInt("IDSanPham");
				String nameproduct = rs.getString("TenSP");
				String categories = rs.getString("TenDM");
				Float price = rs.getFloat("GiaBan");
				String infoproduct = rs.getString("ThongTin");
				String dateTimeUpdate =rs.getDate("ThoiGianThem").toString()+ "/" + rs.getTime("ThoiGianThem").toString();
				String fileName = "images/" + rs.getString("TenFile");
				productDetail = new Product(idproduct, nameproduct,categories, price, infoproduct,dateTimeUpdate, fileName);
			}
			rs.close();
			statement.close();
			conn.close();
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
		return productDetail;
	}


	
}
