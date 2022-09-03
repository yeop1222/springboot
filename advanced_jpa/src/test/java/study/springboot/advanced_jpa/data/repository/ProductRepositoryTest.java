package study.springboot.advanced_jpa.data.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.extern.slf4j.Slf4j;
import study.springboot.advanced_jpa.data.entity.Product;
import study.springboot.advanced_jpa.data.entity.QProduct;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class ProductRepositoryTest {

	@Autowired
	ProductRepository productRepository;
	
	@Test
	@org.junit.jupiter.api.Order(1)
	public void sortingAndPagingTest() {
		log.info("************* 1 *************");
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
	
	//////////////////////////////////////////////////////////////
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Test
	@org.junit.jupiter.api.Order(2)
	void queryDslTest() {
		log.info("************* 2 *************");
		JPAQuery<Product> query = new JPAQuery(entityManager);
		QProduct qProduct = QProduct.product;
		
		List<Product> productList = query
				.from(qProduct)
				.where(qProduct.name.eq("펜"))
				.orderBy(qProduct.price.asc())
				.fetch();
		
		for(Product product : productList) {
			System.out.println("---------------------");
			System.out.println("Number : "+product.getNumber());
			System.out.println("Name : "+product.getName());
			System.out.println("Price : "+product.getPrice());
			System.out.println("Stock : "+product.getStock());
			System.out.println("---------------------");
		}
	}
	
	@Test
	@org.junit.jupiter.api.Order(3)
	void queryDslTest2() {
		log.info("************* 3 *************");
		JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
		QProduct qProduct = QProduct.product;
		
		List<Product> productList = jpaQueryFactory.selectFrom(qProduct)
				.where(qProduct.name.eq("펜"))
				.orderBy(qProduct.price.asc())
				.fetch();

		for(Product product : productList) {
			System.out.println("---------------------");
			System.out.println("Number : "+product.getNumber());
			System.out.println("Name : "+product.getName());
			System.out.println("Price : "+product.getPrice());
			System.out.println("Stock : "+product.getStock());
			System.out.println("---------------------");
		}
	}

	@Test
	@org.junit.jupiter.api.Order(4)
	void queryDslTest3() {
		log.info("************* 4 *************");
		JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
		QProduct qProduct = QProduct.product;
		
		List<String> productList = jpaQueryFactory
				.select(qProduct.name)
				.from(qProduct)
				.where(qProduct.name.eq("펜"))
				.orderBy(qProduct.price.asc())
				.fetch();

		for(String product : productList) {
			System.out.println("---------------------");
			System.out.println("Name : "+product);
			System.out.println("---------------------");
		}
		
		List<Tuple> tupleList = jpaQueryFactory
				.select(qProduct.name, qProduct.price)
				.from(qProduct)
				.where(qProduct.name.eq("펜"))
				.orderBy(qProduct.price.asc())
				.fetch();

		for(Tuple product : tupleList) {
			System.out.println("---------------------");
			System.out.println("Name : "+product.get(qProduct.name));
			System.out.println("Price : "+product.get(qProduct.price));
			System.out.println("---------------------");
		}
	}
	
	//////////////////////////////////////////////////////////////
	
	@Autowired
	JPAQueryFactory jpaQueryFactory;
	
	@Test
	@org.junit.jupiter.api.Order(5)
	void queryDslTest4() {
		log.info("************* 5 *************");
		QProduct qProduct = QProduct.product;
		
		List<String> productList = jpaQueryFactory
				.select(qProduct.name)
				.from(qProduct)
				.where(qProduct.name.eq("펜"))
				.orderBy(qProduct.price.asc())
				.fetch();
		
		for(String product : productList) {
			System.out.println("---------------------");
			System.out.println("Name : "+product);
			System.out.println("---------------------");
		}
		
	}
	
}
