package com.example.demo.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table
public class Inventory {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int invid;
	private String invname;
	private String photo;
	private String addedDate;
	private double sellprice;
	private double costprice;
	private int quantity;
	private int status;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	

	@Override
	public String toString() {
		return "Inventory [invid=" + invid + ", invname=" + invname + ", photo=" + photo + ", addedDate=" + addedDate
				+ ", sellprice=" + sellprice + ", costprice=" + costprice + ", quantity=" + quantity + ", status="
				+ status + ", product=" + product + "]";
	}

	public int getInvid() {
		return invid;
	}

	public void setInvid(int invid) {
		this.invid = invid;
	}

	public String getInvname() {
		return invname;
	}

	public void setInvname(String invname) {
		this.invname = invname;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(String addedDate) {
		this.addedDate = addedDate;
	}

	public double getSellprice() {
		return sellprice;
	}

	public void setSellprice(double sellprice) {
		this.sellprice = sellprice;
	}

	public double getCostprice() {
		return costprice;
	}

	public void setCostprice(double costprice) {
		this.costprice = costprice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	
	
	
	
	
	
	
	
	
	
	

}
