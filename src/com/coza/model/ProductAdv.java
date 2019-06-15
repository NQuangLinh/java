package com.coza.model;

public class ProductAdv {
	private int idproductAdv;
	private String titleAdv;
	private String describeAdv;
	private String fileNameAdv;
	private String savePathAdv;
	private String locationAdv;
	public ProductAdv(int idproductAdv, String titleAdv, String describeAdv, String fileNameAdv, String savePathAdv,
			String locationAdv) {
		this.idproductAdv = idproductAdv;
		this.titleAdv = titleAdv;
		this.describeAdv = describeAdv;
		this.fileNameAdv = fileNameAdv;
		this.savePathAdv = savePathAdv;
		this.locationAdv = locationAdv;
	}

	public ProductAdv(int idproductAdv, String titleAdv, String describeAdv, String fileNameAdv) {
		this.idproductAdv = idproductAdv;
		this.titleAdv = titleAdv;
		this.describeAdv = describeAdv;
		this.fileNameAdv = fileNameAdv;
	}

	public int getIdproductAdv() {
		return idproductAdv;
	}
	public void setIdproductAdv(int idproductAdv) {
		this.idproductAdv = idproductAdv;
	}
	public String getTitleAdv() {
		return titleAdv;
	}
	public void setTitleAdv(String titleAdv) {
		this.titleAdv = titleAdv;
	}
	public String getDescribeAdv() {
		return describeAdv;
	}
	public void setDescribeAdv(String describeAdv) {
		this.describeAdv = describeAdv;
	}
	public String getFileNameAdv() {
		return fileNameAdv;
	}
	public void setFileNameAdv(String fileNameAdv) {
		this.fileNameAdv = fileNameAdv;
	}
	public String getSavePathAdv() {
		return savePathAdv;
	}
	public void setSavePathAdv(String savePathAdv) {
		this.savePathAdv = savePathAdv;
	}
	public String getLocationAdv() {
		return locationAdv;
	}
	public void setLocationAdv(String locationAdv) {
		this.locationAdv = locationAdv;
	}
	

	
	
	
}
