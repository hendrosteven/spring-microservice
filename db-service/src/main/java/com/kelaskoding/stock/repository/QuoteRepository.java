package com.kelaskoding.stock.repository;

import com.kelaskoding.stock.entity.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuoteRepository extends JpaRepository<Quote, Long> {

    List<Quote> findByUserName(String userName);
}
