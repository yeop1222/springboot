package study.springboot.api.controller;

import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import study.springboot.api.dto.MemberDTO;

@RestController
@RequestMapping("/api/v1/get-api")
public class GetController {

	private final Logger LOGGER = LoggerFactory.getLogger(GetController.class);
	
	@RequestMapping(value="/hello", method=RequestMethod.GET)
	public String getHello() {
		LOGGER.info("getHello 메서드가 호출됨");
		return "Hello World";
	}
	
	@GetMapping(value="/name")
	public String getName() {
		LOGGER.info("getName 메서드가 호출됨");
		return "Flature";
	}
	
	@GetMapping(value="/variable1/{variable}")
	public String getVariable1(@PathVariable String variable) {
		LOGGER.info("@PathVariable을 통해 들어온 값 : {}", variable);
		return variable;
	}
	
	@GetMapping(value="/variable2/{variable}")
	public String getVariable2(@PathVariable("variable") String var) {
		return var;
	}
	
	@ApiOperation(value="GET 메서드 예제", notes="@RequestParam을 활용한 GET Method")
	@GetMapping(value="/request1")
	public String getRequestParam1(
			@ApiParam(value="이름", required=true) @RequestParam String name,
			@ApiParam(value="이메일", required=true) @RequestParam String email,
			@ApiParam(value="회사", required=true) @RequestParam String organization) {
		return name + " " + email + " " + organization;
	}
	
	@GetMapping(value="/request2")
	public String getRequestParam2(@RequestParam Map<String, String> param) {
		StringBuilder sb = new StringBuilder();
		
		param.entrySet().forEach(map->{
			sb.append(map.getKey() + " : " + map.getValue() + "\n");
		});
		
		return sb.toString();
	}
	
	@GetMapping(value="/request3")
	public String getRequestParam3(MemberDTO dto) {
		return dto.toString();
	}
}
