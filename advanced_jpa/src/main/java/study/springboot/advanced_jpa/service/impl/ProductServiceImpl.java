package study.springboot.advanced_jpa.service.impl;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import study.springboot.advanced_jpa.data.dto.ProductDTO;
import study.springboot.advanced_jpa.data.dto.ProductResponseDTO;
import study.springboot.advanced_jpa.data.entity.Product;
import study.springboot.advanced_jpa.data.repository.ProductRepository;
import study.springboot.advanced_jpa.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	private final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
	private final ProductRepository productRepository;
	
	@Autowired
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	@Override
	public ProductResponseDTO getProduct(Long number) {
		LOGGER.info("[getProduct] input number : {}", number);
		Product product = productRepository.findById(number).get();
		
		LOGGER.info("[getProduct] product number : {}, name : {}", product.getNumber(), product.getName());
		ProductResponseDTO productResponseDTO = new ProductResponseDTO();
		productResponseDTO.setNumber(product.getNumber());
		productResponseDTO.setName(product.getName());
		productResponseDTO.setPrice(product.getPrice());
		productResponseDTO.setStock(product.getStock());
		
		return productResponseDTO;
	}
	
	@Override
	public ProductResponseDTO saveProduct(ProductDTO dto) {
		LOGGER.info("[saveProduct] productDTO : {}",dto.toString());
		Product product = new Product();
		product.setName(dto.getName());
		product.setPrice(dto.getPrice());
		product.setStock(dto.getStock());
		
		Product savedProduct = productRepository.save(product);
		LOGGER.info("[saveProduct] savedProduct : {}",savedProduct);
		
		ProductResponseDTO productResponseDTO = new ProductResponseDTO();
		productResponseDTO.setNumber(savedProduct.getNumber());
		productResponseDTO.setName(savedProduct.getName());
		productResponseDTO.setPrice(savedProduct.getPrice());
		productResponseDTO.setStock(savedProduct.getStock());
		
		return productResponseDTO;
	}
	
	@Override
	public ProductResponseDTO changeProductName(Long number, String name) throws Exception {
		Product foundProduct = productRepository.findById(number).get();
		foundProduct.setName(name);
		Product changedProduct = productRepository.save(foundProduct);
		
		ProductResponseDTO productResponseDTO = new ProductResponseDTO();
		productResponseDTO.setNumber(changedProduct.getNumber());
		productResponseDTO.setName(changedProduct.getName());
		productResponseDTO.setPrice(changedProduct.getPrice());
		productResponseDTO.setStock(changedProduct.getStock());
		
		return productResponseDTO;
	}
	
	@Override
	public void deleteProduct(Long number) throws Exception {
		productRepository.deleteById(number);
	}
}
