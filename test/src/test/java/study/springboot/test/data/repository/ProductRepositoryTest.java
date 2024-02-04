package study.springboot.test.data.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import study.springboot.test.data.entity.Product;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 테스트 DB mariaDB 그대로 사용
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Test
	void save() {
		// given
		Product product = Product.builder()
				.name("pen")
				.price(1000)
				.stock(1234)
				.build();

		// when
		Product savedProduct = productRepository.save(product);

		// then
		assertEquals(product.getName(), savedProduct.getName());
		assertEquals(product.getPrice(), savedProduct.getPrice());
		assertEquals(product.getStock(), savedProduct.getStock());
	}
}
