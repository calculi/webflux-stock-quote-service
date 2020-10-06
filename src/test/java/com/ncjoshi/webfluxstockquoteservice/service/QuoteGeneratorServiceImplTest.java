package com.ncjoshi.webfluxstockquoteservice.service;

import com.ncjoshi.webfluxstockquoteservice.model.Quote;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;

/**
 * Created by ncj on 04 Oct, 2020.
 */
class QuoteGeneratorServiceImplTest {

    QuoteGeneratorServiceImpl quoteGeneratorService = new QuoteGeneratorServiceImpl();

    @BeforeEach
    void setUp() {
    }

    @Test
    void fetchQuoteStream() {

        // get quoteFlux of Quotes
        Flux<Quote> quoteFlux = quoteGeneratorService.fetchQuoteStream(Duration.ofMillis(1L));

        quoteFlux.take(10)
                .subscribe(System.out::println);

        // So quick that it doesn't show any results, but try the next test
    }

    @Test
    void fetchQuoteStreamCountDown() throws InterruptedException {

        // get quoteFlux of quotes
        Flux<Quote> quoteFlux = quoteGeneratorService.fetchQuoteStream(Duration.ofMillis(100L));

        // subscribe lambda
        Consumer<Quote> println = System.out::println;

        // error handler
        Consumer<Throwable> errorHandler = e -> System.out.println("Some error occurred");

        // set Countdown latch to 1
        CountDownLatch countDownLatch = new CountDownLatch(1);

        // runnable called upon complete, count down latch
        Runnable allDone = () -> countDownLatch.countDown();

        quoteFlux.take(10)
                .subscribe(println, errorHandler, allDone);
        countDownLatch.await();
    }


}