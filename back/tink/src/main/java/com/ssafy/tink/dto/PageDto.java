package com.ssafy.tink.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ApiModel("PageDto")
@Getter
@NoArgsConstructor
@ToString
public class PageDto {

	//현재 페이지
	private int nowPageNum;

	//검색어 키워드
	private String keyword;

	//정렬 방식
	private String sortType;

	@Builder
	public PageDto(int nowPageNum, String keyword, String sortType) {
		this.nowPageNum = nowPageNum;
		this.keyword = keyword;
		this.sortType = sortType;
	}

}
