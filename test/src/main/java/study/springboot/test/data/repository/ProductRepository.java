package study.springboot.test.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import study.springboot.test.data.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
