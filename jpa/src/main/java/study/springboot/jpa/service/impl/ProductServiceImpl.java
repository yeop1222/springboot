package study.springboot.jpa.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import study.springboot.jpa.data.dao.ProductDAO;
import study.springboot.jpa.data.dto.ProductDTO;
import study.springboot.jpa.data.dto.ProductResponseDTO;
import study.springboot.jpa.data.entity.Product;
import study.springboot.jpa.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	private final ProductDAO productDAO;
	
	@Autowired
	public ProductServiceImpl(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}
	
	@Override
	public ProductResponseDTO getProduct(Long number) {
		
		Product product = productDAO.selectProduct(number);
		
		ProductResponseDTO productResponseDTO = new ProductResponseDTO();
		productResponseDTO.setNumber(product.getNumber());
		productResponseDTO.setName(product.getName());
		productResponseDTO.setPrice(product.getPrice());
		productResponseDTO.setStock(product.getStock());
		
		return productResponseDTO;
	}
	
	@Override
	public ProductResponseDTO saveProduct(ProductDTO dto) {
		Product product = new Product();
		product.setName(dto.getName());
		product.setPrice(dto.getPrice());
		product.setStock(dto.getStock());
		product.setCreatedAt(LocalDateTime.now());
		product.setUpdatedAt(LocalDateTime.now());
		
		Product savedProduct = productDAO.insertProduct(product);
		
		ProductResponseDTO productResponseDTO = new ProductResponseDTO();
		productResponseDTO.setNumber(savedProduct.getNumber());
		productResponseDTO.setName(savedProduct.getName());
		productResponseDTO.setPrice(savedProduct.getPrice());
		productResponseDTO.setStock(savedProduct.getStock());
		
		return productResponseDTO;
	}
	
	@Override
	public ProductResponseDTO changeProductName(Long number, String name) throws Exception {
		Product changedProduct = productDAO.updateProductName(number, name);
		
		ProductResponseDTO productResponseDTO = new ProductResponseDTO();
		productResponseDTO.setNumber(changedProduct.getNumber());
		productResponseDTO.setName(changedProduct.getName());
		productResponseDTO.setPrice(changedProduct.getPrice());
		productResponseDTO.setStock(changedProduct.getStock());
		
		return productResponseDTO;
	}
	
	@Override
	public void deleteProduct(Long number) throws Exception {
		productDAO.deleteProduct(number);
	}
}
