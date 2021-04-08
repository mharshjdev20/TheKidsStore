package com.claim.kidsstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.claim.kidsstore.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	List<Order> findAll();

	@Query("FROM Order WHERE order_id = ?1")
	Optional<Order> findByOrderId(long orderId);

}

