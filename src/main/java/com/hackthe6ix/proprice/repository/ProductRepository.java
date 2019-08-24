package com.hackthe6ix.proprice.repository;

import com.hackthe6ix.proprice.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Integer, Product> {
}
