package com.wenance.challange.repositories;

import com.wenance.challange.models.Price;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.Instant;

@Repository
public interface PriceRepository extends ReactiveMongoRepository<Price, Integer> {
    Flux<Price> findByTimestamp(Instant timestamp);
}
