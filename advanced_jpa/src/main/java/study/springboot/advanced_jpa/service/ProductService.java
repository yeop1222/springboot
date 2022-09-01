package study.springboot.advanced_jpa.service;

import study.springboot.advanced_jpa.data.dto.ProductDTO;
import study.springboot.advanced_jpa.data.dto.ProductResponseDTO;

public interface ProductService {

	ProductResponseDTO getProduct(Long number);
	ProductResponseDTO saveProduct(ProductDTO dto);
	ProductResponseDTO changeProductName(Long number, String name) throws Exception;
	void deleteProduct(Long number) throws Exception;
}
