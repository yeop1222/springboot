package study.springboot.test.data.dto;

public class ChangeProductNameDTO {
	
	private Long number;
	private String name;
	
	public ChangeProductNameDTO(Long number, String name) {
		this.number = number;
		this.name = name;
	}
	
	public ChangeProductNameDTO() {
		
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
