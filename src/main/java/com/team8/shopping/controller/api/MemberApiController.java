package com.team8.shopping.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team8.shopping.service.MemberService;
import com.team8.shopping.vo.MemberVO;

@RestController
@RequestMapping("/api/member")
public class MemberApiController {

	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value = "/checkId", method = RequestMethod.POST)
	public ResponseEntity<String> checkIdAvailability(@RequestParam("id") String id) {
		boolean isAvailable = memberService.checkIdAvailability(id);
		if (isAvailable) {
			return ResponseEntity.ok("available");
		} else {
			return ResponseEntity.ok("unavailable");
		}
	}
	
	@RequestMapping(value = "/find_member_id", method = RequestMethod.POST)
	public ResponseEntity<String> findMemberId(@RequestBody MemberVO memberVO) {
		String foundId = memberService.findMemberId(memberVO);
		if (foundId != null) {
			return ResponseEntity.ok(foundId);
		} else {
			return ResponseEntity.ok("");
		}
	}
	
	@RequestMapping(value = "/find_member_password", method = RequestMethod.POST)
	public ResponseEntity<String> findMemberPassword(@RequestBody MemberVO memberVO) {
		String foundPassword = memberService.findMemberPassword(memberVO);
		if (foundPassword != null) {
			return ResponseEntity.ok(foundPassword);
		} else {
			return ResponseEntity.ok("");
		}
	}
}
