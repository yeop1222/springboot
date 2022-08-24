package study.springboot.jpa.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import study.springboot.jpa.data.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
