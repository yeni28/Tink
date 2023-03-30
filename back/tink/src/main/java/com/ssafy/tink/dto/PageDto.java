package com.ssafy.tink.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;

@ApiModel("PageDto")
@Getter
public class PageDto {

	//현재 페이지
	private int nowPageNum;

	//검색어 키워드
	private String keyword;

	//정렬 방식
	private SortTypeEnum sortTypeEnum;

	@Builder
	public PageDto(int nowPageNum, String keyword, SortTypeEnum sortTypeEnum) {
		this.nowPageNum = nowPageNum;
		this.keyword = keyword;
		this.sortTypeEnum = sortTypeEnum;
	}

}

enum SortTypeEnum{
	LATELY,
	NAME,
	BEST
}