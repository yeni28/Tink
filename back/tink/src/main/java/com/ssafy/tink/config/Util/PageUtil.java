package com.ssafy.tink.config.Util;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageUtil implements Pageable {


	private int pageNumber;
	private int pageSize;
	private long offset;
	private Sort sort;
	private boolean paged;
	private boolean unpaged;

	@Override
	public boolean isPaged() {
		return Pageable.super.isPaged();
	}

	@Override
	public boolean isUnpaged() {
		return Pageable.super.isUnpaged();
	}

	@Override
	public Sort getSortOr(Sort sort) {
		return Pageable.super.getSortOr(sort);
	}

	@Override
	public Optional<Pageable> toOptional() {
		return Pageable.super.toOptional();
	}

	@Override
	public int getPageNumber() {
		return 0;
	}

	@Override
	public int getPageSize() {
		return 0;
	}

	@Override
	public long getOffset() {
		return 0;
	}

	@Override
	public Sort getSort() {
		return null;
	}

	@Override
	public Pageable next() {
		return null;
	}

	@Override
	public Pageable previousOrFirst() {
		return null;
	}

	@Override
	public Pageable first() {
		return null;
	}

	@Override
	public Pageable withPage(int pageNumber) {
		return null;
	}

	@Override
	public boolean hasPrevious() {
		return false;
	}
}
