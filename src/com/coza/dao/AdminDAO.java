package com.coza.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.coza.model.Product;
import com.coza.db.ConnectionDB;

public class AdminDAO {

	public static void insertNewProduct(Product product) {
		Connection conn = ConnectionDB.getConnectionDB();
		String sql = "INSERT INTO SanPham(TenSP,IDDanhMuc,GiaBan,ThongTin,ThoiGianThem) VALUES ( ?, ?, ?, ?,?)";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, product.getNameproduct());
			statement.setInt(2, product.getIdCategorie());
			statement.setFloat(3, product.getPrice());
			statement.setString(4, product.getInfoproduct());
			statement.setObject(5, java.time.LocalDateTime.now());
			statement.executeUpdate();
			statement.close();
			conn.close();
		} catch (SQLException e) {

			e.printStackTrace();

		}
	}

	public static int getIdProduct(Product product) {
		Connection conn = ConnectionDB.getConnectionDB();
		String sql = "select IDSanPham from SanPham where TenSP = ? AND ThongTin = ? AND GiaBan =? AND IDDanhMuc=?";
		int idProduct = 0;
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, product.getNameproduct());
			statement.setString(2, product.getInfoproduct());
			statement.setFloat(3, product.getPrice());
			statement.setInt(4, product.getIdCategorie());
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				idProduct = rs.getInt("IDSanPham");
			}
			rs.close();
			statement.close();
			conn.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return idProduct;
	}

	public static void insertImage(int idProduct, Product product) {
		Connection conn = ConnectionDB.getConnectionDB();
		String sql = "INSERT INTO HinhAnhSP(LinkHA,IDSanPham,TenFile) VALUES ( ?, ?, ?)";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, product.getSavePath());
			statement.setInt(2, idProduct);
			statement.setString(3, product.getFileName());
			statement.executeUpdate();
			statement.close();
			conn.close();
		} catch (SQLException e) {

			e.printStackTrace();

		}
	}

	public static void deleteProductImage(Integer idProductDlt) {
		Connection conn = ConnectionDB.getConnectionDB();
		String sql = "DELETE FROM HinhAnhSP WHERE IDSanPham=?";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, idProductDlt);
			statement.executeQuery();
	
			statement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
	
		}
	}

	public static void deleteProductinCart(Integer idProductDlt) {
		Connection conn = ConnectionDB.getConnectionDB();
		String sql = "DELETE FROM SanPhamTrongGioHang WHERE IDSanPham=?";
		try {
	
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, idProductDlt);
			statement.executeQuery();
	
			statement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
	
		}
	}

	public static void deleteProduct(Integer idProductDlt) {
		Connection conn = ConnectionDB.getConnectionDB();
		String sql = "DELETE FROM SanPham WHERE IDSanPham=?";
		try {
	
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, idProductDlt);
			statement.executeQuery();
	
			statement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
	
		}
	}

}
