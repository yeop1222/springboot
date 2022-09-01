package study.springboot.advanced_jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import study.springboot.advanced_jpa.data.dto.ChangeProductNameDTO;
import study.springboot.advanced_jpa.data.dto.ProductDTO;
import study.springboot.advanced_jpa.data.dto.ProductResponseDTO;
import study.springboot.advanced_jpa.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	private final ProductService productService;
	
	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping()
	public ResponseEntity<ProductResponseDTO> getProduct(Long number){
		ProductResponseDTO productResponseDTO = productService.getProduct(number);
		
		return ResponseEntity.status(HttpStatus.OK).body(productResponseDTO);
	}
	
	@PostMapping()
	public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductDTO dto){
		ProductResponseDTO productResponseDTO = productService.saveProduct(dto);
		
		return ResponseEntity.status(HttpStatus.OK).body(productResponseDTO);
	}
	
	@PutMapping()
	public ResponseEntity<ProductResponseDTO> changeProductName(
			@RequestBody ChangeProductNameDTO dto) throws Exception{
		ProductResponseDTO productResponseDTO = productService.changeProductName(dto.getNumber(), dto.getName());
		
		return ResponseEntity.status(HttpStatus.OK).body(productResponseDTO);
	}
	
	@DeleteMapping
	public ResponseEntity<String> deleteProduct(Long number) throws Exception{
		productService.deleteProduct(number);
		
		return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
	}
}
