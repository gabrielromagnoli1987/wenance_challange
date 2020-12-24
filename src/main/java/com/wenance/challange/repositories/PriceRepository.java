package com.wenance.challange.repositories;

import com.wenance.challange.models.Price;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Repository
public interface PriceRepository extends ReactiveMongoRepository<Price, String>, CustomPriceRepository {
    Mono<Price> findByTimestampAndCurr1AndCurr2(Instant timestamp, String curr1, String curr2);
    Flux<Price> findByTimestampBetweenAndCurr1AndCurr2(Instant startTimestamp, Instant finishTimestamp,
                                                       String curr1, String curr2);
}
