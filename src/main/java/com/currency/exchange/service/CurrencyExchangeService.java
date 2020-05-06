package com.currency.exchange.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.currency.exchange.dao.RateRepository;
import com.currency.exchange.model.CurrencyRate;
import com.currency.exchange.quotes.FxSymbols;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.quotes.fx.FxQuote;
import yahoofinance.quotes.stock.StockQuote;

@Service
public class CurrencyExchangeService {

    @Autowired
    private RateRepository rateRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyExchangeService.class);

    public void getCurrencyExchange() {
        LOGGER.info("Calling getCurrencyExchange()");
        CurrencyRate currencyRate;
        try {
            Stock stock = YahooFinance.get(FxSymbols.BTCUSD);
            StockQuote stockQuote = stock.getQuote();
            currencyRate = new CurrencyRate(stockQuote.getPrice(), stockQuote.getOpen(), stockQuote.getPreviousClose(), stockQuote.getDayLow(), stockQuote.getDayHigh(), stockQuote.getYearLow(), stockQuote.getYearHigh(), stockQuote.getPriceAvg50(), stockQuote.getPriceAvg200(), new Date());

            rateRepository.save(currencyRate);
        } catch (IOException e) {
            LOGGER.error("Error occurred in trying to get currency exchange from yahoo finance service!", e);
        }
        LOGGER.info("Exit of getCurrencyExchange()");
    }

    public CurrencyRate latestCurrencyRate() {
        return rateRepository.findTopByOrderByDateDesc();
    }

    public List<CurrencyRate> findByDateBetween(Date startDate, Date endDate) {
        return rateRepository.findByDateBetween(startDate, endDate);
    }

    public List<CurrencyRate> getAllCurrencyRates() {
        return StreamSupport.stream(rateRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

}
