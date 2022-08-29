package study.springboot.test.data.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProductDTO {

	private String name;
	private int price;
	private int stock;
}
