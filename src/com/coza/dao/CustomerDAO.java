package com.coza.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.coza.db.ConnectionDB;
import com.coza.model.Product;

public class CustomerDAO {

	public static void insertProductinCart(int idProductAdd, int userSession,int numProduct) {
		Connection conn = ConnectionDB.getConnectionDB();
		String sql = "INSERT INTO SanPhamTrongGioHang(IDSanPham,IDGioHang,SoLuong) VALUES ( ?, (Select IDGioHang From GioHang where IDKhachHang = ?),?)";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
	
			statement.setInt(1, idProductAdd);
			statement.setInt(2, userSession);
			statement.setInt(3, numProduct);
			statement.executeUpdate();
			statement.close();
			conn.close();
		} catch (SQLException e) {
	
			e.printStackTrace();
	
		}
	}

	public static void updateCountProduct(int userSession) {
		Connection conn = ConnectionDB.getConnectionDB();
		String sql = "UPDATE GioHang SET SoLuongSP = (SELECT SUM(SoLuong) FROM SanPhamTrongGioHang WHERE IDGioHang = (SELECT IDGioHang FROM GioHang WHERE IDKhachHang = ?) AND TinhTrang IS NULL) WHERE IDKhachHang= ?";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, userSession);
			statement.setInt(2, userSession);
			statement.executeUpdate();
			statement.close();
			conn.close();
		} catch (SQLException e) {
	
			e.printStackTrace();
	
		}
	}

	public static List<Product> getCartProduct(int userSession) {
		Connection conn = ConnectionDB.getConnectionDB();
		List<Product> listProduct = new ArrayList<Product>();
		String sql = "Select SanPhamTrongGioHang.IDSanPhamTrongGioHang,SanPham.IDSanPham,SanPham.TenSP, SanPham.GiaBan,SanPhamTrongGioHang.SoLuong,HinhAnhSP.TenFile From SanPhamTrongGioHang left join SanPham on SanPhamTrongGioHang.IDSanPham = SanPham.IDSanPham left join HinhAnhSP on SanPham.IDSanPham = HinhAnhSP.IDSanPham where SanPhamTrongGioHang.IDGioHang = (Select IDGioHang from GioHang where IDKhachHang = ?)  and SanPhamTrongGioHang.TinhTrang is null";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, userSession);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int idProductinCart = rs.getInt("IDSanPhamTrongGioHang");
				int idProduct = rs.getInt("IDSanPham");
				String nameProduct = rs.getString("TenSP");
				float price = rs.getFloat("GiaBan");
				int countProduct = rs.getInt("SoLuong");
				String fileName = "images/" + rs.getString("TenFile");
				float total = price * countProduct;
				Product product = new Product(idProductinCart,idProduct, nameProduct, price, fileName, countProduct, total);
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

	public static int countProduct(int userSession) {
		Connection conn = ConnectionDB.getConnectionDB();
		String sql = "SELECT SoLuongSP FROM GioHang WHERE IDKhachHang = ?";
		int countAllProduct = 0;
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, userSession);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				countAllProduct = rs.getInt("SoLuongSP");
			}
			rs.close();
			statement.close();
			conn.close();
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
		return countAllProduct;
	}

	public static int checkProductinCart(int idProductAdd, int userSession) {		
		Connection conn = ConnectionDB.getConnectionDB();
		String sql = "SELECT IDSanPhamTrongGioHang FROM SanPhamTrongGioHang WHERE IDSanPham = ? AND IDGioHang = (Select IDGioHang From GioHang where IDKhachHang = ?)";
		int idProductinCart = 0;
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, idProductAdd);
			statement.setInt(2, userSession);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				idProductinCart = rs.getInt("IDSanPhamTrongGioHang");
			}
			rs.close();
			statement.close();
			conn.close();
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
			return idProductinCart;
	}

	public static void updateCountProductinCart(int idProductinCart, int numProduct) {
		Connection conn = ConnectionDB.getConnectionDB();
		String sql = "UPDATE SanPhamTrongGioHang SET SoLuong = (Select SoLuong from SanPhamTrongGioHang where IDSanPhamTrongGioHang = ?) + ? where IDSanPhamTrongGioHang = ?";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, idProductinCart);
			statement.setInt(2, numProduct);
			statement.setInt(3, idProductinCart);
			statement.executeUpdate();
			statement.close();
			conn.close();
		} catch (SQLException e) {
	
			e.printStackTrace();
	
		}
	}

	public static void deleteProductinCart(int idProductinCart) {
		Connection conn = ConnectionDB.getConnectionDB();
		String sql = "DELETE FROM SanPhamTrongGioHang WHERE IDSanPhamTrongGioHang = ?";
		try {
	
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, idProductinCart);
			statement.executeQuery();
	
			statement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
	
		}
		
	}

	public static void addProductinCart(int idProductinCartAdd) {
		Connection conn = ConnectionDB.getConnectionDB();
		String sql = "UPDATE SanPhamTrongGioHang SET SoLuong = (Select SoLuong from SanPhamTrongGioHang where IDSanPhamTrongGioHang = ?) + 1 where IDSanPhamTrongGioHang = ?";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, idProductinCartAdd);
			statement.setInt(2, idProductinCartAdd);
			statement.executeUpdate();
			statement.close();
			conn.close();
		} catch (SQLException e) {
	
			e.printStackTrace();
	
		}
	}

	public static void removeProductinCart(int idProductinCartRemove) {
		Connection conn = ConnectionDB.getConnectionDB();
		String sql = "UPDATE SanPhamTrongGioHang SET SoLuong = (Select SoLuong from SanPhamTrongGioHang where IDSanPhamTrongGioHang = ?) - 1 where IDSanPhamTrongGioHang = ?";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, idProductinCartRemove);
			statement.setInt(2, idProductinCartRemove);
			statement.executeUpdate();
			statement.close();
			conn.close();
		} catch (SQLException e) {
	
			e.printStackTrace();
	
		}
	}



}
