package com.coza.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.coza.db.ConnectionDB;
import com.coza.model.Customer;

public class LoginDAO {

	public static int checkLoginCustomer(String accountname, String password) {
		
		Connection conn = ConnectionDB.getConnectionDB();
		String sql = "SELECT IDKhachHang FROM KhachHang WHERE IDTaiKhoan = (SELECT IDTaiKhoan FROM TaiKhoanKH WHERE TenTK = ? AND MatKhauTK= ?)";
		int idCustomer = 0;
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, accountname);
			statement.setString(2, password);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				idCustomer = rs.getInt("IDKhachHang");
			}
			rs.close();
			statement.close();
			conn.close();
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
			return idCustomer;
	}

	public static int checkLogin(String accountname, String password) {
		Connection conn = ConnectionDB.getConnectionDB();
		String sql = "SELECT IDTaiKhoan FROM TaiKhoanNV WHERE TenTK = ? AND MatKhauTK= ?";
		int idAccount = 0;
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, accountname);
			statement.setString(2, password);
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
	return idAccount;

	}

	public static int getIDEmployee(int idAccount) {
		Connection conn = ConnectionDB.getConnectionDB();
		String sql = "SELECT IDNhanVien FROM NhanVien WHERE IDTaiKhoan = ?";
		int idEmployee = 0;
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, idAccount);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				idEmployee = rs.getInt("IDNhanVien");
			}
			rs.close();
			statement.close();
			conn.close();
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
		return idEmployee;
	}

	public static int getIDManager(int idAccount) {
		Connection conn = ConnectionDB.getConnectionDB();
		String sql = "SELECT IDQuanLi FROM QuanLi WHERE IDTaiKhoan = ?";
		int idManager = 0;
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, idAccount);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				idManager = rs.getInt("IDQuanLi");
			}
			rs.close();
			statement.close();
			conn.close();
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
		return idManager;
	}

	public static Customer getMyAccount(Integer userSession) {
		Connection conn = ConnectionDB.getConnectionDB();
		Customer customer = null;
		String sql = "Select KhachHang.IDKhachHang,KhachHang.TenKH,KhachHang.SDTKH,KhachHang.Email,KhachHang.DiaChiKH,TaiKhoanKH.MatKhauTK from KhachHang Left Join TaiKhoanKH on KhachHang.IDTaiKhoan = TaiKhoanKH.IDTaiKhoan where KhachHang.IDKhachHang = ?";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, userSession);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int idCustomer = rs.getInt("IDKhachHang");
				String fullName = rs.getString("TenKH");
				String address = rs.getString("DiaChiKH");
				String phoneNumber = rs.getString("SDTKH");
				String email = rs.getString("Email");
				String password = rs.getString("MatKhauTK");
				customer = new Customer(idCustomer,fullName, address, phoneNumber, email, password);
			}
			rs.close();
			statement.close();
			conn.close();
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
		return customer;
	}

	public static void updateMyProfile(Customer customer) {
		Connection conn = ConnectionDB.getConnectionDB();
		String sql = "UPDATE KhachHang SET TenKH= ?,SDTKH=?,Email=?,DiaChiKH=? WHERE IDKhachHang=?";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, customer.getFullName());
			statement.setString(2, customer.getPhoneNumber());
			statement.setString(3, customer.getEmail());
			statement.setString(4, customer.getAddress());
			statement.setInt(5, customer.getIdCustomer());
			statement.executeUpdate();
			statement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
	
		}
	}

	public static void updateMyAccount(Customer customer) {
		Connection conn = ConnectionDB.getConnectionDB();
		String sql = "UPDATE TaiKhoanKH SET TenTK= ?,MatKhauTK=? WHERE IDTaiKhoan = (SELECT IDTaiKhoan FROM KhachHang WHERE IDKhachHang = ?)";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, customer.getEmail());
			statement.setString(2, customer.getPassword());
			statement.setInt(3, customer.getIdCustomer());
			statement.executeUpdate();
			statement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
	
		}
	}

	public static Customer getMyAccountAdmin(Integer adminSession) {
		Connection conn = ConnectionDB.getConnectionDB();
		Customer customer = null;
		String sql = "Select NhanVien.IDNhanVien,NhanVien.TenNV,NhanVien.SDTNV,NhanVien.Email,NhanVien.DiaChiNV,TaiKhoanNV.MatKhauTK from NhanVien Left Join TaiKhoanNV on NhanVien.IDTaiKhoan = TaiKhoanNV.IDTaiKhoan where NhanVien.IDNhanVien = ?";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, adminSession);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int idCustomer = rs.getInt("IDNhanVien");
				String fullName = rs.getString("TenNV");
				String address = rs.getString("DiaChiNV");
				String phoneNumber = rs.getString("SDTNV");
				String email = rs.getString("Email");
				String password = rs.getString("MatKhauTK");
				customer = new Customer(idCustomer,fullName, address, phoneNumber, email, password);
			}
			rs.close();
			statement.close();
			conn.close();
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
		return customer;
	}

	public static void updateMyProfileAdmin(Customer customer) {
		Connection conn = ConnectionDB.getConnectionDB();
		String sql = "UPDATE NhanVien SET TenNV= ?,SDTNV=?,Email=?,DiaChiNV=? WHERE IDNhanVien=?";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, customer.getFullName());
			statement.setString(2, customer.getPhoneNumber());
			statement.setString(3, customer.getEmail());
			statement.setString(4, customer.getAddress());
			statement.setInt(5, customer.getIdCustomer());
			statement.executeUpdate();
			statement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
	
		}
	}

	public static void updateMyAccountAdmin(Customer customer) {
		Connection conn = ConnectionDB.getConnectionDB();
		String sql = "UPDATE TaiKhoanNV SET TenTK= ?,MatKhauTK=? WHERE IDTaiKhoan = (SELECT IDTaiKhoan FROM NhanVien WHERE IDNhanVien = ?)";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, customer.getEmail());
			statement.setString(2, customer.getPassword());
			statement.setInt(3, customer.getIdCustomer());
			statement.executeUpdate();
			statement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
	
		}
	}
	
	
}

