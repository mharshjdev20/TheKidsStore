package com.claim.kidsstore.service;

import java.util.List;
import java.util.Optional;

import com.claim.kidsstore.model.Product;

public interface ProductService {
	
	Optional<Product> findById(long id);
	
	List<Product> findByProductName(String productName);
	
	List<Product> findByCategory(String category);
	
	List<Product> findByPrice(double price);
	
	List<Product> search(String keyword);
	
	List<Product> findAll();
	
	void deleteProduct(long id);
	
	void updateProduct(Product product);

	void saveProduct(Product product);

}
