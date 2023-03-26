package com.ssafy.tink.db.repository;

import com.ssafy.tink.db.entity.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatternRepository {
    public Pattern searchDetail(int patternId);

}
