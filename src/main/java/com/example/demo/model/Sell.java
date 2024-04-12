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
@Table
@Data
public class Sell {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int sellid;
	private String custname;
	private String addedDate;
	private double sellamount;
	private int selquantity;
	private String sellname;
	@ManyToOne
	@JoinColumn(name="inv_id")
	private Inventory inventory;
	

	public int getSellid() {
		return sellid;
	}


	public void setSellid(int sellid) {
		this.sellid = sellid;
	}
	


	public String getSellname() {
		return sellname;
	}


	public void setSellname(String sellname) {
		this.sellname = sellname;
	}


	public String getCustname() {
		return custname;
	}


	public void setCustname(String custname) {
		this.custname = custname;
	}


	public String getAddedDate() {
		return addedDate;
	}


	public void setAddedDate(String addedDate) {
		this.addedDate = addedDate;
	}


	public double getSellamount() {
		return sellamount;
	}


	public void setSellamount(double sellamount) {
		this.sellamount = sellamount;
	}


	


	public int getSelquantity() {
		return selquantity;
	}


	public void setSelquantity(int selquantity) {
		this.selquantity = selquantity;
	}


	public Inventory getInventory() {
		return inventory;
	}


	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}


	@Override
	public String toString() {
		return "Sell [sellid=" + sellid + ", custname=" + custname + ", addedDate=" + addedDate + ", sellamount="
				+ sellamount + ", selquantity=" + selquantity + ", sellname=" + sellname + ", inventory=" + inventory
				+ "]";
	}


	


	
	
	

}
