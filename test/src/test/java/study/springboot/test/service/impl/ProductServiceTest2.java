package study.springboot.test.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import study.springboot.test.data.dto.ProductDTO;
import study.springboot.test.data.dto.ProductResponseDTO;
import study.springboot.test.data.entity.Product;
import study.springboot.test.data.repository.ProductRepository;
import study.springboot.test.service.ProductService;

import java.util.Optional;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@Import({ProductServiceImpl.class})
public class ProductServiceTest2 {

    @MockBean
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @BeforeEach
    public void setUpTest() {
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    void getProductTest() {
        //given
        Product givenProduct = Product.builder()
                .number(123L)
                .name("pen")
                .price(1000)
                .stock(1234)
                .build();

        when(productRepository.findById(123L))
                .thenReturn(Optional.of(givenProduct));

        // when
        ProductResponseDTO productResponseDTO = productService.getProduct(123L);

        // then
        Assertions.assertEquals(productResponseDTO.getNumber(), givenProduct.getNumber());
        Assertions.assertEquals(productResponseDTO.getName(), givenProduct.getName());
        Assertions.assertEquals(productResponseDTO.getPrice(), givenProduct.getPrice());
        Assertions.assertEquals(productResponseDTO.getStock(), givenProduct.getStock());

        verify(productRepository).findById(123L);
    }

    @Test
    void saveProductTEst() {
        // given
        when(productRepository.save(any(Product.class)))
                .then(returnsFirstArg());

        // when
        ProductResponseDTO productResponseDTO = productService.saveProduct(new ProductDTO("pen", 1000, 1234));

        // then
        Assertions.assertEquals(productResponseDTO.getName(), "pen");
        Assertions.assertEquals(productResponseDTO.getPrice(), 1000);
        Assertions.assertEquals(productResponseDTO.getStock(), 1234);

        verify(productRepository).save(any());
    }
}
