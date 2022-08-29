package study.springboot.test.service.impl;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import study.springboot.test.data.dto.ProductDTO;
import study.springboot.test.data.dto.ProductResponseDTO;
import study.springboot.test.data.entity.Product;
import study.springboot.test.data.repository.ProductRepository;
import study.springboot.test.service.ProductService;

@ExtendWith(SpringExtension.class)
@Import({ProductServiceImpl.class})
public class ProductServiceTest {

//	private ProductRepository productRepository = Mockito.mock(ProductRepository.class);
	@MockBean
	ProductRepository productRepository;
	
//	private ProductServiceImpl productService;
	@Autowired
	ProductService productService;
	
	@BeforeEach
	public void setUpTest() {
		productService = new ProductServiceImpl(productRepository);
	}
	
	@Test
	void getProductTest() {
		Product givenProduct = new Product();
		givenProduct.setNumber(123L);
		givenProduct.setName("펜");
		givenProduct.setPrice(1000);
		givenProduct.setStock(1234);
		
		Mockito.when(productRepository.findById(123L))
			.thenReturn(Optional.of(givenProduct));
		
		ProductResponseDTO dto = productService.getProduct(123L);
		
		Assertions.assertEquals(dto.getNumber(), givenProduct.getNumber());
		Assertions.assertEquals(dto.getName(), givenProduct.getName());
		Assertions.assertEquals(dto.getPrice(), givenProduct.getPrice());
		Assertions.assertEquals(dto.getStock(), givenProduct.getStock());
		
		verify(productRepository).findById(123L);
	}
	
	@Test
	void saveProductTest() {
		Mockito.when(productRepository.save(any(Product.class)))
				.then(returnsFirstArg());
		
		ProductResponseDTO dto = productService.saveProduct(new ProductDTO("펜", 1000, 1234));
		
		Assertions.assertEquals(dto.getName(), "펜");
		Assertions.assertEquals(dto.getPrice(), 1000);
		Assertions.assertEquals(dto.getStock(), 1234);
		
		verify(productRepository).save(any());
	}
}
