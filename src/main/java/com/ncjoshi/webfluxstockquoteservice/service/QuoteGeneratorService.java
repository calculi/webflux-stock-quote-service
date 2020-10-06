package com.ncjoshi.webfluxstockquoteservice.service;

import com.ncjoshi.webfluxstockquoteservice.model.Quote;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * Created by ncj on 04 Oct, 2020.
 */
public interface QuoteGeneratorService {

    Flux<Quote> fetchQuoteStream(Duration period);
}
