package com.ssafy.tink.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.SliceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.tink.config.Util.SecurityUtil;
import com.ssafy.tink.db.entity.Member;
import com.ssafy.tink.dto.BaseResponse;
import com.ssafy.tink.dto.BoardAndPatternDto;
import com.ssafy.tink.dto.MemberInfoDto;
import com.ssafy.tink.dto.PatternLikeDto;
import com.ssafy.tink.dto.dsl.members.BoardInfoDsl;
import com.ssafy.tink.dto.dsl.members.CommunityBoardInfoDsl;
import com.ssafy.tink.dto.TokenDto;
import com.ssafy.tink.dto.dsl.members.MemberInfoDsl;
import com.ssafy.tink.dto.dsl.members.PatternInfoDsl;
import com.ssafy.tink.service.MemberServiceImpl;
import com.ssafy.tink.service.RedisServiceImpl;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;

@Slf4j
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

	@Autowired
	private MemberServiceImpl memberService;
	@Autowired
	private RedisServiceImpl redisServiceImpl;

	@GetMapping("/mypage/info")
	@ApiOperation(value = "마이페이지(자신) 프로필 정보 조회하는 API")
	public BaseResponse<Object> getProfileByAuthorization() {
		log.info("마이페이지 조회 시작하기");
		try {
			MemberInfoDsl memberInfo = memberService.getMemberInfoByQueryDsl(null);
			return BaseResponse.builder()
				.result(memberInfo)
				.resultCode(HttpStatus.OK.value())
				.resultMsg("정상적으로 회원 정보 조회 성공")
				.build();
		} catch (NoSuchElementException e) {
			log.debug("정보가 없습니다.");
			return BaseResponse.builder()
				.result(null)
				.resultCode(HttpStatus.NO_CONTENT.value())
				.resultMsg("잘못된 정보를 입력하였습니다.")
				.build();
		}
	}

	@GetMapping("/mypage")
	@ApiOperation(value = "마이페이지(자신) 도안,소모임,자랑글,질문글 조회하는 API")
	public BaseResponse<Object> getBoardAndPatternByAuthorization() {
		Optional<BoardAndPatternDto> data = memberService.getBoardAndPatternByAuthentication();
		return BaseResponse.builder()
			.result(data)
			.resultCode(HttpStatus.OK.value())
			.resultMsg("도안, 소모임, 자랑글, 질문글 조회 성공")
			.build();
	}

	@GetMapping("/mypage/pattern")
	@ApiOperation(value = "마이페이지(자신) 도안 전체 조회하는 API")
	public BaseResponse<Object> getAllPatternByMember() {
		List<PatternInfoDsl> patterns = memberService.getPatternAllByMypage();
		return BaseResponse.builder()
			.result(patterns)
			.resultCode(HttpStatus.OK.value())
			.resultMsg("마이페이지(자신) 도안 전체 조회")
			.build();
	}

	@GetMapping("/mypage/community")
	@ApiOperation(value = "마이페이지(자신) 자랑글 전체 조회하는 API")
	public BaseResponse<Object> getAllCommunityByMember() {
		List<CommunityBoardInfoDsl> patterns = memberService.getCommnutityBoardAllByMypage();
		return BaseResponse.builder()
			.result(patterns)
			.resultCode(HttpStatus.OK.value())
			.resultMsg("마이페이지(자신) 자랑글 전체 조회 조회")
			.build();
	}

	@GetMapping("/mypage/board")
	@ApiOperation(value = "마이페이지(자신) 소모임글, 질문글  조회하는 API")
	public BaseResponse<Object> getAllBoardByMember(@RequestParam(name = "category") String category) {
		List<BoardInfoDsl> boardList = memberService.getBoardAllByMypage(category);
		return BaseResponse.builder()
			.result(boardList)
			.resultCode(HttpStatus.OK.value())
			.resultMsg("마이페이지(자신) 소모임글, 질문글  조회")
			.build();
	}

	@GetMapping("/info/{id}")
	@ApiOperation(value = "회원 프로필 정보 조회 API")
	public BaseResponse<Object> getProfileByMember(@PathVariable(name = "id") String memberId) {
		log.info("회원 조회 시작하기");
		try {
			MemberInfoDsl memberInfo = memberService.getMemberInfoByQueryDsl(memberId);
			return BaseResponse.builder()
				.result(memberInfo)
				.resultCode(HttpStatus.OK.value())
				.resultMsg("정상적으로 회원 정보 조회 성공")
				.build();
		} catch (NoSuchElementException e) {
			log.debug("정보가 없습니다.");
			return BaseResponse.builder()
				.result(null)
				.resultCode(HttpStatus.NO_CONTENT.value())
				.resultMsg("잘못된 정보를 입력하였습니다.")
				.build();
		}
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

	@GetMapping("/{id}")
	@ApiOperation(value = "회원 도안,소모임,자랑글,질문글 조회하는 API")
	public BaseResponse<Object> getBoardAndPatternByMember(@PathVariable(name = "id") long memberId) {
		log.info("회원 API [getBoardAndPatternByMember] 시작하기");
		Optional<BoardAndPatternDto> data = memberService.getBoardAndPatternByMemberId(memberId);
		return BaseResponse.builder()
			.result(data)
			.resultCode(HttpStatus.OK.value())
			.resultMsg("도안, 소모임, 자랑글, 질문글 조회 성공")
			.build();
	}

	@GetMapping("/favorite/pattern")
	@ApiOperation(value = "회원가입 선호도 도안 조회 API")
	public BaseResponse<Object> getPatternWithRandom(@RequestParam(name = "difficulty") String difficulty) {
		log.info("회원 API [getPatternWithRandom] 시작하기");
		List<PatternInfoDsl> list = memberService.getFavoriteFromPattern(difficulty);
		return BaseResponse.builder()
			.result(list)
			.resultCode(HttpStatus.OK.value())
			.resultMsg("선호도 조사 도안 조회 성공")
			.build();
	}

	@PostMapping("/favorite/patterns")
	@ApiOperation(value = "도안 선호도 대입하는 부분")
	public BaseResponse<Object> enrolledFavorite(@RequestBody List<PatternLikeDto> pattern) {
		log.info("회원 API [enrolledFavorite] 시작하기");
		String save = memberService.likedPatternToMember(pattern);
		return BaseResponse.builder()
			.result(save)
			.resultCode(HttpStatus.OK.value())
			.resultMsg("선택하신 도안 선호도가 대입되었습니다.")
			.build();
	}

	@GetMapping("/refresh")
	@ApiOperation(value = "로그인한 회원의 리플래쉬 토큰 가져오기")
	public BaseResponse<Object> getRefreshTokenByAuthentication(@ApiIgnore HttpSession session) {
		log.info("회원 API [getRefreshTokenByAuthentication] 시작하기");
		String member = SecurityUtil.getCurrentAuthentication().get();
		String token = redisServiceImpl.getRefreshTokenByAuthentication(Long.parseLong(member));
		return BaseResponse.builder()
			.result(TokenDto.builder().refreshToken(token).build())
			.resultCode(HttpStatus.OK.value())
			.resultMsg("리플래쉬 토큰 발급이 성공하였습니다.")
			.build();
	}

	// @GetMapping("/memberinfo")
	// @ApiOperation(value = "회원 프로필 정보 조회 API")
	// public BaseResponse<Object> getMemberInfoFromDsl(@RequestParam(name = "id", required = false) String id) {
	// 	try {
	// 		MemberInfoDsl memberInfo = memberService.getMemberInfoByQueryDsl(id);
	// 		return BaseResponse.builder()
	// 			.result(memberInfo)
	// 			.resultCode(HttpStatus.OK.value())
	// 			.resultMsg("정상적으로 회원 정보 조회 성공")
	// 			.build();
	// 	} catch (NoSuchElementException e) {
	// 		log.debug("정보가 없습니다.");
	// 		return BaseResponse.builder()
	// 			.result(null)
	// 			.resultCode(HttpStatus.NO_CONTENT.value())
	// 			.resultMsg("잘못된 정보를 입력하였습니다.")
	// 			.build();
	// 	}
	// }

}

