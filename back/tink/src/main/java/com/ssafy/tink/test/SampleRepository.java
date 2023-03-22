package com.ssafy.tink.test;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleRepository extends JpaRepository<Sample, Integer> {

	Optional<Sample> findByEmail(String email);

}
