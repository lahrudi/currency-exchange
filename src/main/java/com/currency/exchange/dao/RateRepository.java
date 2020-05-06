package com.currency.exchange.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.currency.exchange.model.CurrencyRate;

public interface RateRepository extends CrudRepository<CurrencyRate, Long> {

    CurrencyRate findTopByOrderByDateDesc();
    
    List<CurrencyRate> findByDateBetween(Date startDate, Date endDate);
}
