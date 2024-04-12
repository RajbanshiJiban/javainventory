package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="Category_tbl")

public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "Category_id")
	private int categoryid;
	@Override
	public String toString() {
		return "Category [id=" + categoryid + ", categoryname=" + categoryname + ", photo=" + photo + ", addeddate=" + addeddate
				+ ", status=" + status + ", getCategoryname()=" + getCategoryname() + ", getPhoto()=" + getPhoto()
				+ ", getAddeddate()=" + getAddeddate() + ", getStatus()=" + getStatus() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	public int getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}

	private String categoryname;
	private String photo;
	private String addeddate;
	private int status;
	public String getCategoryname() {
		return categoryname;
	}
	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getAddeddate() {
		return addeddate;
	}
	public void setAddeddate(String addeddate) {
		this.addeddate = addeddate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

}
