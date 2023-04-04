package com.ssafy.tink.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.tink.db.entity.YarnSale;

@Repository
public interface YarnSaleRepository extends JpaRepository<YarnSale, Integer> {


}
