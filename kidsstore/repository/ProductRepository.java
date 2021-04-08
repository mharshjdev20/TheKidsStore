package com.claim.kidsstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.claim.kidsstore.model.Product;

@Repository
public interface ProductRepository extends JpaRepository <Product, Long>{
	
	@Query ("FROM Product WHERE product_name = ?1")
	List <Product> findByName(String name);
	
	@Query ("FROM Product WHERE category = ?1")
	List <Product> findByCategory(String category);
	
	@Query ("FROM Product WHERE price = ?1")
	List <Product> findByPrice(double price);
	
	@Query("SELECT c FROM Product c WHERE c.productName LIKE '%keyword%'OR c.category LIKE '%keyword%'")
	List<Product> search(@Param("keyword") String keyword);

	// @Query("DELETE FROM Product WHERE product_id = ?1")
	// void deleteById(long id);
}

