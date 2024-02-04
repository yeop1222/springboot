package study.springboot.test.controller;

import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import study.springboot.test.data.dto.ProductDTO;
import study.springboot.test.data.dto.ProductResponseDTO;
import study.springboot.test.service.impl.ProductServiceImpl;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	ProductServiceImpl productService;

	@Test
	@DisplayName("MockMvc Test : Product 데이터 가져오기")
	void getProductTest() throws Exception {

		// given
		given(productService.getProduct(123L)).willReturn(
				new ProductResponseDTO(123L, "pen", 5000, 2000));
		String productId = "123";

		// when
		ResultActions resultActions = mockMvc.perform(get("/product?number=" + productId));

		// then
		resultActions.andExpect(status().isOk())
				.andExpect(jsonPath("$.number").exists())
				.andExpect(jsonPath("$.name").exists())
				.andExpect(jsonPath("$.price").exists())
				.andExpect(jsonPath("$.stock").exists())
				.andDo(print());

		verify(productService).getProduct(123L);
	}

	@Test
	@DisplayName("MockMvc Test : Product 데이터 생성")
	void createProductTest() throws Exception {

		// given
		given(productService.saveProduct(new ProductDTO("pen", 5000, 2000)))
				.willReturn(new ProductResponseDTO(12315L, "pen", 5000, 2000));

		ProductDTO productDTO = ProductDTO.builder()
				.name("pen")
				.price(5000)
				.stock(2000)
				.build();

		Gson gson = new Gson();
		String content = gson.toJson(productDTO);

		// when
		ResultActions resultActions = mockMvc.perform(post("/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content(content));

		// then
		resultActions.andExpect(status().isOk())
				.andExpect(jsonPath("$.number").exists())
				.andExpect(jsonPath("$.name").exists())
				.andExpect(jsonPath("$.price").exists())
				.andExpect(jsonPath("$.stock").exists())
				.andDo(print());

		verify(productService).saveProduct(new ProductDTO("pen", 5000, 2000));
	}
}
