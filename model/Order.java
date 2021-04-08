package com.claim.kidsstore.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "orders")
public class Order {
	public Order() {
		
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column (name = "order_id")
	private long orderId;
	
	@OneToMany(mappedBy = "user")
	private List<Product> product;
	
	@Column (name = "notes")
	private String notes;
	
	@Column (name = "order_date")
	private Date orderDate;
	
	@Column (name = "total_price")
	private double totalPrice;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	
	public Order(long orderId, List<Product> product, String notes, Date orderDate, double totalPrice, User user) {
		super();
		this.orderId = orderId;
		this.product = product;
		this.notes = notes;
		this.orderDate = orderDate;
		this.totalPrice = totalPrice;
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	

	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getPickUpDate() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setPickUpDate(Date pickUpDate) {
		// TODO Auto-generated method stub
	
	}
	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", product=" + product + ", notes=" + notes + ", orderDate=" + orderDate
				+ ", totalPrice=" + totalPrice + "]";
	}

	

}

