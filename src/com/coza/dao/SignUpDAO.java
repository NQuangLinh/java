package com.coza.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.coza.db.ConnectionDB;
import com.coza.model.Customer;

public class SignUpDAO {

	public static boolean checkEmailCustomer(String email) {
		Connection conn = ConnectionDB.getConnectionDB();
		String sql = "SELECT IDTaiKhoan FROM TaiKhoanKH WHERE TenTK = ?";
		int idAccount = 0;
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, email);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				idAccount = rs.getInt("IDTaiKhoan");
			}
			rs.close();
			statement.close();
			conn.close();
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
		if(idAccount != 0)
		{
			return true;
		}
		return false;
	}

	public static boolean checkEmailAdmin(String email) {
		Connection conn = ConnectionDB.getConnectionDB();
		String sql = "SELECT IDTaiKhoan FROM TaiKhoanNV WHERE TenTK = ?";
		int idAccount = 0;
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, email);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				idAccount = rs.getInt("IDTaiKhoan");
			}
			rs.close();
			statement.close();
			conn.close();
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
		if(idAccount != 0)
		{
			return true;
		}
		return false;
	}

	public static void insertAccountCustomer(Customer customer) {
		Connection conn = ConnectionDB.getConnectionDB();
		String sql = "INSERT INTO TaiKhoanKH(TenTK,MatKhauTK) VALUES ( ?, ?)";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, customer.getEmail());
			statement.setString(2, customer.getPassword());
			statement.executeUpdate();
			statement.close();
			conn.close();
		} catch (SQLException e) {
	
			e.printStackTrace();
	
		}
	}

	public static void insertCustomer(Customer customer) {
		Connection conn = ConnectionDB.getConnectionDB();
		String sql = "INSERT INTO KhachHang(TenKH,SDTKH,Email,DiaChiKH,IDTaiKhoan) VALUES (?,?,?,?,(Select IDTaiKhoan From TaiKhoanKH where TenTK=? and MatKhauTK=?))";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, customer.getFullName());
			statement.setString(2, customer.getPhoneNumber());
			statement.setString(3, customer.getEmail());
			statement.setString(4, customer.getAddress());
			statement.setString(5, customer.getEmail());
			statement.setString(6, customer.getPassword());
			statement.executeUpdate();
			statement.close();
			conn.close();
		} catch (SQLException e) {
	
			e.printStackTrace();
	
		}
	}

	public static void insertCart(Customer customer) {
		Connection conn = ConnectionDB.getConnectionDB();
		String sql = "INSERT INTO GioHang(SoLuongSP,IDKhachHang) VALUES (0,(Select IDKhachHang From KhachHang where IDTaiKhoan=(Select IDTaiKhoan From TaiKhoanKH where TenTK=? and MatKhauTK=?)))";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, customer.getEmail());
			statement.setString(2, customer.getPassword());
			statement.executeUpdate();
			statement.close();
			conn.close();
		} catch (SQLException e) {
	
			e.printStackTrace();
	
		}
	}

	public static void insertAccountAdmin(Customer customer) {
		Connection conn = ConnectionDB.getConnectionDB();
		String sql = "INSERT INTO TaiKhoanNV(TenTK,MatKhauTK) VALUES ( ?, ?)";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, customer.getEmail());
			statement.setString(2, customer.getPassword());
			statement.executeUpdate();
			statement.close();
			conn.close();
		} catch (SQLException e) {
	
			e.printStackTrace();
	
		}
	}

	public static void insertAdmin(Customer customer) {
		Connection conn = ConnectionDB.getConnectionDB();
		String sql = "INSERT INTO NhanVien(TenNV,SDTNV,Email,DiaChiNV,IDTaiKhoan) VALUES (?,?,?,?,(Select IDTaiKhoan From TaiKhoanNV where TenTK=? and MatKhauTK=?))";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, customer.getFullName());
			statement.setString(2, customer.getPhoneNumber());
			statement.setString(3, customer.getEmail());
			statement.setString(4, customer.getAddress());
			statement.setString(5, customer.getEmail());
			statement.setString(6, customer.getPassword());
			statement.executeUpdate();
			statement.close();
			conn.close();
		} catch (SQLException e) {
	
			e.printStackTrace();
	
		}
	}



}
