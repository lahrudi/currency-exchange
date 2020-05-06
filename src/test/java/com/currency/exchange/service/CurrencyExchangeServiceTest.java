package com.currency.exchange.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import com.currency.exchange.quotes.FxSymbols;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.currency.exchange.dao.RateRepository;
import com.currency.exchange.model.CurrencyRate;

import yahoofinance.YahooFinance;
import yahoofinance.quotes.fx.FxQuote;

@RunWith(PowerMockRunner.class)
@PrepareForTest(YahooFinance.class)
@SpringBootTest
public class CurrencyExchangeServiceTest {

    @InjectMocks
    private CurrencyExchangeService currencyExchangeService = new CurrencyExchangeService();
    
    @Mock
    private RateRepository rateRepositoryMock;
    
    @Test
    public void testCheckCurrenyExchange() throws IOException {
        
        FxQuote usdeur = new FxQuote(FxSymbols.BTCUSD, new BigDecimal(1.17));
        
        mockStatic(YahooFinance.class);
        when(YahooFinance.getFx(FxSymbols.BTCUSD)).thenReturn(usdeur);
        
        CurrencyRate rate = new CurrencyRate();
        rate.setDate(new Date());
        rate.setRate(new BigDecimal(1.17));
        
        when(this.rateRepositoryMock.save(any(CurrencyRate.class))).thenReturn(rate);
        currencyExchangeService.getCurrencyExchange();

        assertEquals(usdeur.getPrice(), rate.getRate());  

    }

}
