package com.wenance.challange.controllers;

import com.wenance.challange.models.Price;
import com.wenance.challange.repositories.result.AverageAndMaxPrice;
import com.wenance.challange.services.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

@RestController
@RequestMapping("/bitcoin")
public class PriceController {

    @Autowired
    private PriceService priceService;

    @GetMapping("/all")
    public Flux<Price> getAll() {
        return priceService.getAll();
    }

    @GetMapping
    public Mono<Price> getPriceByTimestamp(@RequestParam("timestamp") Instant timestamp,
                                           @RequestParam("currency1") String currency1,
                                           @RequestParam("currency2") String currency2) {
        return priceService.getPriceByTimestamp(timestamp, currency1, currency2);
    }

    @GetMapping("/between")
    public Flux<Price> getPricesBetweenTimestamps(@RequestParam("startTimestamp") Instant startTimestamp,
                                                  @RequestParam("finishTimestamp") Instant finishTimestamp,
                                                  @RequestParam("currency1") String currency1,
                                                  @RequestParam("currency2") String currency2) {
        return priceService.getPricesBetweenTimestamps(startTimestamp, finishTimestamp, currency1, currency2);
    }

    @GetMapping("/average-and-max-between")
    public Mono<AverageAndMaxPrice> getAverageAndMaxPriceBetweenTimestamps(@RequestParam("startTimestamp") Instant startTimestamp,
                                                                           @RequestParam("finishTimestamp") Instant finishTimestamp,
                                                                           @RequestParam("currency1") String currency1,
                                                                           @RequestParam("currency2") String currency2) {
        return priceService.getAverageAndMaxPricesBetweenTimestamps(startTimestamp, finishTimestamp, currency1, currency2);
    }
}
