package study.springboot.api.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import study.springboot.api.dto.MemberDTO;

@RestController
@RequestMapping("/api/v1/post-api")
public class PostController {

	@RequestMapping(value="/domain", method=RequestMethod.POST)
	public String postExample() {
		return "Hello Post API";
	}

	@PostMapping(value="/member")
	public String postMember(@RequestBody Map<String, Object> postData) {
		StringBuilder sb = new StringBuilder();
		
		postData.entrySet().forEach(map -> {
			sb.append(map.getKey() + " : " + map.getValue() + "\n");
		});
		
		return sb.toString();
	}
	
	@PostMapping(value="/member2")
	public String postMemberDTO(@RequestBody MemberDTO dto) {
		return dto.toString();
	}
}
