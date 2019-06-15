package com.coza.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.coza.model.Product;
import com.coza.db.ConnectionDB;

public class ProductDAO {

	public static void updateProduct(Product product) {
		Connection conn = ConnectionDB.getConnectionDB();
		String sql = "UPDATE SanPham SET TenSP= ?,IDDanhMuc=?,GiaBan=?,ThongTin=?,ThoiGianThem = ? WHERE IDSanPham=?";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, product.getNameproduct());
			statement.setInt(2, product.getIdCategorie());
			statement.setFloat(3, product.getPrice());
			statement.setString(4, product.getInfoproduct());
			statement.setObject(5, java.time.LocalDateTime.now());
			statement.setInt(6, product.getIdproduct());
			statement.executeUpdate();
			statement.close();
			conn.close();
		} catch (SQLException e) {

			e.printStackTrace();

		}
	}

	public static void updateImage(Product product) {
		Connection conn = ConnectionDB.getConnectionDB();
		String sql = "UPDATE HinhAnhSP SET LinkHA= ?,TenFile=? WHERE IDSanPham=?";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, product.getSavePath());
			statement.setString(2, product.getFileName());
			statement.setInt(3, product.getIdproduct());
			statement.executeUpdate();
			statement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

}
