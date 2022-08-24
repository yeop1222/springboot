package study.springboot.jpa.service;

import study.springboot.jpa.data.dto.ProductDTO;
import study.springboot.jpa.data.dto.ProductResponseDTO;

public interface ProductService {

	ProductResponseDTO getProduct(Long number);
	ProductResponseDTO saveProduct(ProductDTO dto);
	ProductResponseDTO changeProductName(Long number, String name) throws Exception;
	void deleteProduct(Long number) throws Exception;
}
