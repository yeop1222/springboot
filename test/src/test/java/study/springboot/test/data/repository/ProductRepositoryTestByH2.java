package study.springboot.test.data.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import study.springboot.test.data.entity.Product;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
public class ProductRepositoryTestByH2 {

	@Autowired
	private ProductRepository productRepository;

	@Test
	void saveTest() {
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

	@Test
	void selectTest() {
		// given
		Product product = Product.builder()
				.name("pen")
				.price(1000)
				.stock(1234)
				.build();

		Product savedProduct = productRepository.saveAndFlush(product);

		// when
		Product foundProduct = productRepository.findById(savedProduct.getNumber()).get();

		// then
		assertEquals(product.getName(), foundProduct.getName());
		assertEquals(product.getPrice(), foundProduct.getPrice());
		assertEquals(product.getStock(), foundProduct.getStock());
	}
}
