package com.ssafy.tink.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.tink.dto.BaseResponse;
import com.ssafy.tink.dto.MemberInfoDto;
import com.ssafy.tink.dto.dsl.members.BoardAndPatternDsl;
import com.ssafy.tink.dto.dsl.MemberInfoDsl;
import com.ssafy.tink.service.MemberService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

	@Autowired
	private MemberService memberService;
	

	@GetMapping("/mypage/info")
	@ApiOperation(value = "마이페이지(자신) 프로필 정보 조회하는 API")
	public BaseResponse<Object> getProfileByAuthorization() {
		log.info("마이페이지 조회 시작하기");
		Optional<MemberInfoDto> member = memberService.getProfileByAuthentication();

		if( !member.isPresent() || member.get() == null) {
			BaseResponse.builder()
				.result("FAILED")
				.resultCode(HttpStatus.NOT_FOUND.value())
				.resultMsg("자신의 정보를 가져오는데 실패하였습니다.")
				.build();
		}

		return BaseResponse.builder()
			.result(member)
			.resultCode(HttpStatus.OK.value())
			.resultMsg("정상적으로 조회되었습니다.")
			.build();
	}

	@GetMapping("/mypage")
	@ApiOperation(value = "마이페이지(자신) 도안,소모임,자랑글,질문글 조회하는 API")
	public BaseResponse<Object> getBoardAndPatternByAuthorization() {
		Optional<List<BoardAndPatternDsl>>list = memberService.getBoardAndPatternByAuthentication();
		return BaseResponse.builder()
			.result(list)
			.resultCode(HttpStatus.OK.value())
			.resultMsg("도안, 소모임, 자랑글, 질문글 조회하는 API")
			.build();
	}

	@GetMapping("/mypage/pattern")
	@ApiOperation(value = "마이페이지(자신) 도안 전체 조회하는 API")
	public BaseResponse<Object> getAllPatternByMember() {

		return null;
	}

	@GetMapping("/mypage/community")
	@ApiOperation(value = "마이페이지(자신) 자랑글 전체 조회하는 API")
	public BaseResponse<Object> getAllCommunityByMember() {

		return null;
	}

	@GetMapping("/mypage/board")
	@ApiOperation(value = "마이페이지(자신) 소모임글, 질문글  조회하는 API")
	public BaseResponse<Object> getAllBoardByMember() {

		return null;
	}

	@GetMapping("/info/{id}")
	@ApiOperation(value = "회원 도안,소모임,자랑글,질문글 조회하는 API")
	public BaseResponse<Object> getProfileByMember(@PathVariable(name = "id") String memberId) {
		log.info("회원 조회 시작하기");
		Optional<MemberInfoDto> member = memberService.getProfileByMemberId(Long.parseLong(memberId));

		if( !member.isPresent() || member.get() == null) {
			BaseResponse.builder()
				.result("FAILED")
				.resultCode(HttpStatus.NOT_FOUND.value())
				.resultMsg("회원 정보를 가져오는데 실패하였습니다.")
				.build();
		}

		return BaseResponse.builder()
			.result(member)
			.resultCode(HttpStatus.OK.value())
			.resultMsg("정상적으로 조회되었습니다.")
			.build();
	}

	@PutMapping("/mypage")
	@ApiOperation(value = "마이페이지(자신) 프로필 수정하는 API")
	public BaseResponse<Object> updateToMemberInfo() {

		return null;
	}

	@PostMapping("/follow")
	@ApiOperation(value = "회원 팔로우 추가/취소 API")
	public BaseResponse<Object> updateFollowToMember() {

		return null;
	}

	@GetMapping("/Test")
	public BaseResponse<Object> TestQueryDslMebmer() {
		log.debug("QueryDsl Test 시작합니다.");
		Optional<List<MemberInfoDsl>> result = memberService.getMemberInfoByQueryDsl();
		return BaseResponse.builder()
			.result(result)
			.resultCode(HttpStatus.OK.value())
			.resultMsg("QueryDsl 연결 성공")
			.build();
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "회원 정보보기 API")
	public BaseResponse<Object> getBoardAndPatternByMember(@PathVariable(name = "id") long memberId) {
		log.info("회원 조회 시작하기");
		Optional<List<BoardAndPatternDsl>>list = memberService.getBoardAndPatternByMemberId(memberId);
		return BaseResponse.builder()
			.result(list)
			.resultCode(HttpStatus.OK.value())
			.resultMsg("도안, 소모임, 자랑글, 질문글 조회하는 API")
			.build();
	}
}

