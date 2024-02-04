package study.springboot.test.data.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.springboot.test.data.entity.Product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class ProductRepositoryTest2 {

	@Autowired
	ProductRepository productRepository;

	@Test
	public void basicCRUDTest() {
		/* CREATE */
		// given
		Product givenProduct = Product.builder()
				.name("pen")
				.price(1000)
				.stock(1234)
				.build();

		// when
		Product savedProduct = productRepository.save(givenProduct);

		// then
		Assertions.assertThat(savedProduct.getNumber()).isEqualTo(givenProduct.getNumber());
		Assertions.assertThat(savedProduct.getName()).isEqualTo(givenProduct.getName());
		Assertions.assertThat(savedProduct.getPrice()).isEqualTo(givenProduct.getPrice());
		Assertions.assertThat(savedProduct.getStock()).isEqualTo(givenProduct.getStock());

		/* READ */
		// when
		Product selectedProduct = productRepository.findById(savedProduct.getNumber())
				.orElseThrow(RuntimeException::new);

		// then
		Assertions.assertThat(selectedProduct.getNumber()).isEqualTo(givenProduct.getNumber());
		Assertions.assertThat(selectedProduct.getName()).isEqualTo(givenProduct.getName());
		Assertions.assertThat(selectedProduct.getPrice()).isEqualTo(givenProduct.getPrice());
		Assertions.assertThat(selectedProduct.getStock()).isEqualTo(givenProduct.getStock());

		/* UPDATE */
		// when
		Product foundProduct = productRepository.findById(selectedProduct.getNumber())
				.orElseThrow(RuntimeException::new);

		foundProduct.setName("notebook");
		Product updatedProduct = productRepository.save(foundProduct);

		// then
		assertEquals(updatedProduct.getName(), "notebook");

		/* DELETE */
		// when
		productRepository.delete(updatedProduct);

		// then
		assertFalse(productRepository.findById(selectedProduct.getNumber()).isPresent());
	}

}
