package com.wenance.challange.repositories;

import com.wenance.challange.repositories.result.AverageAndMaxPrice;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Repository
public interface CustomPriceRepository {

     Mono<AverageAndMaxPrice> getAverageAndMaxPricesBetweenTimestamps(Instant startTimestamp, Instant finishTimestamp,
                                                                            String currency1, String currency2);
}
