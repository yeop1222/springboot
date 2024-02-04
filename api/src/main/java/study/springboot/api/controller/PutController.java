package study.springboot.api.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import study.springboot.api.dto.MemberDTO;

@RestController
@RequestMapping("/api/v1/put-api")
public class PutController {

	@PutMapping(value="/member")
	public String postMember(@RequestBody Map<String, Object> putData) {
		StringBuilder sb = new StringBuilder();

		putData.forEach((key, value) ->
				sb.append(key).append(" : ").append(value).append("\n")
		);
		
		return sb.toString();
	}
	
	@PutMapping(value="/member1")
	public String postMemberDTO1(@RequestBody MemberDTO dto) {
		return dto.toString();
	}
	
	@PutMapping(value="/member2")
	public MemberDTO postMemberDTO2(@RequestBody MemberDTO dto) {
		return dto;
	}
	
	@PutMapping(value="/member3")
	public ResponseEntity<MemberDTO> postMemberDTO3(@RequestBody MemberDTO dto){
		return ResponseEntity
				.status(HttpStatus.ACCEPTED)
				.body(dto);
	}
}
