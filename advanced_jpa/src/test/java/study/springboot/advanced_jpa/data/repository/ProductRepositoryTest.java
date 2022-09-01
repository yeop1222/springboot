package study.springboot.advanced_jpa.data.repository;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import study.springboot.advanced_jpa.data.entity.Product;

@SpringBootTest
public class ProductRepositoryTest {

	@Autowired
	ProductRepository productRepository;
	
	@Test
	void sortingAndPagingTest() {
		Product p1 = new Product();
		p1.setName("펜");
		p1.setPrice(1000);
		p1.setStock(100);
		p1.setCreatedAt(LocalDateTime.now());
		p1.setUpdatedAt(LocalDateTime.now());
		
		Product p2 = new Product();
		p2.setName("펜");
		p2.setPrice(5000);
		p2.setStock(300);
		p2.setCreatedAt(LocalDateTime.now());
		p2.setUpdatedAt(LocalDateTime.now());
		
		Product p3 = new Product();
		p3.setName("펜");
		p3.setPrice(500);
		p3.setStock(50);
		p3.setCreatedAt(LocalDateTime.now());
		p3.setUpdatedAt(LocalDateTime.now());
		
		Product savedProduct1 = productRepository.save(p1);
		Product savedProduct2 = productRepository.save(p2);
		Product savedProduct3 = productRepository.save(p3);
		
		System.out.println(productRepository.findByName("펜", Sort.by(Order.asc("price"))));
		System.out.println(productRepository.findByName("펜", Sort.by(Order.asc("price"),Order.desc("stock"))));
		System.out.println(productRepository.findByName("펜", getSort()));
		
		Page<Product> productPage = productRepository.findByName("펜", PageRequest.of(0, 2));
		System.out.println(productPage);
		System.out.println(productPage.getContent());
	}
	
	private Sort getSort() {
		return Sort.by(
			Order.asc("price"),
			Order.desc("stock")
		);
	}
}
