package com.coza.model;

public class Product {
	private int idproduct;
	private int idProductinCart;
	private String nameproduct;
	private int idCategorie;
	private String categories;
	private float price;
	private String infoproduct;
	private String dateTimeUpdate;
	private String fileName;
	private String savePath;
	private int countproduct;
	private float total;
	private float cartTotals;
	




	public Product(String nameproduct, int idCategorie, float price, String infoproduct, String fileName,
			String savePath) {
		this.nameproduct = nameproduct;
		this.idCategorie = idCategorie;
		this.price = price;
		this.infoproduct = infoproduct;
		this.fileName = fileName;
		this.savePath = savePath;
	}

	public Product(int idproduct, String nameproduct, int idCategorie, float price, String infoproduct,
			String fileName) {
		this.idproduct = idproduct;
		this.nameproduct = nameproduct;
		this.idCategorie = idCategorie;
		this.price = price;
		this.infoproduct = infoproduct;
		this.fileName = fileName;
	}

	public Product(int idproduct, String nameproduct, String categories, float price, String infoproduct,
			String fileName) {
		this.idproduct = idproduct;
		this.nameproduct = nameproduct;
		this.categories = categories;
		this.price = price;
		this.infoproduct = infoproduct;
		this.fileName = fileName;
	}

	
	public Product(int idProductinCart,int idproduct,String nameproduct, float price, String fileName, int countproduct, float total) {
		this.idProductinCart = idProductinCart;
		this.idproduct = idproduct;
		this.nameproduct = nameproduct;
		this.price = price;
		this.fileName = fileName;
		this.countproduct = countproduct;
		this.total = total;
	}
	
	

	public Product(int idproduct, String nameproduct, String categories, float price, String infoproduct,
			String dateTimeUpdate, String fileName) {
		this.idproduct = idproduct;
		this.nameproduct = nameproduct;
		this.categories = categories;
		this.price = price;
		this.infoproduct = infoproduct;
		this.dateTimeUpdate = dateTimeUpdate;
		this.fileName = fileName;
	}

	
	public Product(int idproduct,String nameproduct, int idCategorie, float price, String infoproduct, String fileName, String savePath) {
		this.idproduct = idproduct;
		this.nameproduct = nameproduct;
		this.idCategorie = idCategorie;
		this.price = price;
		this.infoproduct = infoproduct;
		this.fileName = fileName;
		this.savePath = savePath;
	}

	public String getDateTimeUpdate() {
		return dateTimeUpdate;
	}

	public void setDateTimeUpdate(String dateTimeUpdate) {
		this.dateTimeUpdate = dateTimeUpdate;
	}

	public int getCountproduct() {
		return countproduct;
	}

	public void setCountproduct(int countproduct) {
		this.countproduct = countproduct;
	}

	public int getIdproduct() {
		return idproduct;
	}

	public void setIdproduct(int idproduct) {
		this.idproduct = idproduct;
	}

	public String getNameproduct() {
		return nameproduct;
	}

	public void setNameproduct(String nameproduct) {
		this.nameproduct = nameproduct;
	}

	public int getIdCategorie() {
		return idCategorie;
	}

	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getInfoproduct() {
		return infoproduct;
	}

	public void setInfoproduct(String infoproduct) {
		this.infoproduct = infoproduct;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public String getCategories() {
		return categories;
	}

	public void setCategories(String categories) {
		this.categories = categories;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public float getCartTotals() {
		return cartTotals;
	}

	public void setCartTotals(float cartTotals) {
		this.cartTotals = cartTotals;
	}

	public int getIdProductinCart() {
		return idProductinCart;
	}

	public void setIdProductinCart(int idProductinCart) {
		this.idProductinCart = idProductinCart;
	}
	
	
}
