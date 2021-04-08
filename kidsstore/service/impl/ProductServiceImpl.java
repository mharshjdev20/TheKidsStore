package com.claim.kidsstore.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claim.kidsstore.model.Product;
import com.claim.kidsstore.repository.ProductRepository;
import com.claim.kidsstore.service.ProductService;



@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;
	

	public ProductServiceImpl(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}

	@Override
	public Optional<Product> findById(long id) {
		// TODO Auto-generated method stub
		return productRepository.findById(id);
	}

	@Override
	public List<Product> findByProductName(String productName) {
		// TODO Auto-generated method stub
		return productRepository.findByName(productName);
	}

	@Override
	public List<Product> findByCategory(String category) {
		// TODO Auto-generated method stub
		return productRepository.findByCategory(category);
	}

	@Override
	public List<Product> findByPrice(double price) {
		// TODO Auto-generated method stub
		return productRepository.findByPrice(price);
	}

	@Override
	public List<Product> search(String keyword) {
		// TODO Auto-generated method stub
		return productRepository.search(keyword);
	}

	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return productRepository.findAll();
	}

	@Override
	public void deleteProduct(long id) {
		// TODO Auto-generated method stub
		productRepository.deleteById(id);
	}

	@Override
	public void updateProduct(Product product) {
		// TODO Auto-generated method stub
		productRepository.findById(product.getId()).ifPresent(p -> {
			p.setCategory(product.getCategory());
			p.setDescription(product.getDescription());
			p.setInStock(product.getInStock());
			p.setPrice(product.getPrice());
			p.setProductName(product.getProductName());
		});
	}

	@Override
	public void saveProduct(Product product) {
		// TODO Auto-generated method stub
		productRepository.save(product);
	}


}
