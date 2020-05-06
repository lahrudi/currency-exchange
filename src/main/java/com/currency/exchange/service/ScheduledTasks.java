package com.currency.exchange.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    @Autowired
    private CurrencyExchangeService currencyExchangeService;
    
    @Scheduled(fixedDelayString = "${fixed.delay:3000}")
    public void getCurrencyExchange() {
        currencyExchangeService.getCurrencyExchange();
    }
}
