package study.springboot.test.service;

import study.springboot.test.data.dto.ProductDTO;
import study.springboot.test.data.dto.ProductResponseDTO;

public interface ProductService {

	ProductResponseDTO getProduct(Long number);
	ProductResponseDTO saveProduct(ProductDTO dto);
	ProductResponseDTO changeProductName(Long number, String name) throws Exception;
	void deleteProduct(Long number) throws Exception;
}
