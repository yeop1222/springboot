package study.springboot.test.data.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import study.springboot.test.data.entity.Product;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ProductRepositoryTest {

	@Autowired
	private ProductRepository productRepository;
	
	@Test
	void save() {

		//given
		Product product = new Product();
		product.setName("펜");
		product.setPrice(1000);
		product.setStock(1000);
		
		//when
		Product savedProduct = productRepository.save(product);
		
		//then
		assertEquals(product.getName(), savedProduct.getName());
		assertEquals(product.getPrice(), savedProduct.getPrice());
		assertEquals(product.getStock(), savedProduct.getStock());
	}
}
