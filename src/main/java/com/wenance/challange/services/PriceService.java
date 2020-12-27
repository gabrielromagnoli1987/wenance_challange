package com.wenance.challange.services;

import com.wenance.challange.dto.PairPrice;
import com.wenance.challange.models.Price;
import com.wenance.challange.repositories.result.AverageAndMaxPrice;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

public interface PriceService {

    Mono<Price> savePrice(PairPrice pairPrice);

    Flux<Price> getAll();

    Mono<Price> getPriceByTimestamp(Instant timestamp, String currency1, String currency2);

    Flux<Price> getPricesBetweenTimestamps(Instant startTimestamp, Instant finishTimestamp,
                                           String currency1, String currency2);

    Mono<AverageAndMaxPrice> getAverageAndMaxPricesBetweenTimestamps(Instant startTimestamp, Instant finishTimestamp,
                                                                     String currency1, String currency2);
}
