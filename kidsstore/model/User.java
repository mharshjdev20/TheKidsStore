package com.claim.kidsstore.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "users")
public class User {

	public User() {
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column (name = "user_id")
	private long userId;
	
	@Column (name = "first_name")
	private String firstName;
	
	@Column (name = "last_name")
	private String lastName;
	
	@Column (name= "phone")
	private String phone;
	
	@Column (name = "email")
	private String email;
	
	@Column (name = "password")
	private String password;
	
	@Transient //used for validation, not to be persisted in the database
	private String repeatPass;
	
	@OneToMany(mappedBy = "user")
	private List<Product> product;
	
	@Transient
	MultipartFile file;
	private String image;

	public User(long userId, String firstName, String lastName, String phone, String email, String password,
			String repeatPass, List<Product> product) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.repeatPass = repeatPass;
		this.product = product;
	}

	public List<Product> getProducts() {
		return product;
	}

	public void setProducts(List<Product> product) {
		this.product = product;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepeatPass() {
		return repeatPass;
	}

	public void setRepeatPass(String repeatPass) {
		this.repeatPass = repeatPass;

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
	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", phone=" + phone
				+ ", email=" + email + ", password=" + password + ", repeatPass=" + repeatPass + "]";
	}
	
	
	
	
}
