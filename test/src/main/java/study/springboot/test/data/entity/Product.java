package study.springboot.test.data.entity;

import java.time.LocalDateTime;
import javax.persistence.*;

import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString(exclude = "name")
@Table(name="product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long number;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private Integer price;
	
	@Column(nullable = false)
	private Integer stock;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
}
