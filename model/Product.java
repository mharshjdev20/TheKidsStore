package com.claim.kidsstore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="product")
public class Product {
	public Product() {
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column (name = "product_id")
	private long id;
	
	@Column (name = "product_name")
	private String productName;
	
	@Column (name = "category")
	private String category;
	
	@Column (name = "description")
	private String description;
	
	@Column (name = "price")
	private double price;
	
	@Column (name = "in_stock")
	private int inStock;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@Transient
	MultipartFile file;
	private String image;
	
	public Product(long id, String productName, String category, String description, double price, int inStock,
			User user, MultipartFile file, String image) {
		super();
		this.id = id;
		this.productName = productName;
		this.category = category;
		this.description = description;
		this.price = price;
		this.inStock = inStock;
		this.user = user;
		this.file = file;
		this.image = image;
	}


	public long getId() {
		return id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getInStock() {
		return inStock;
	}
	public void setInStock(int inStock) {
		this.inStock = inStock;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName + ", category=" + category + ", description="
				+ description + ", price=" + price + ", inStock=" + inStock + ", file=" + file + ", image=" + image
				+ "]";
	}
	
}
