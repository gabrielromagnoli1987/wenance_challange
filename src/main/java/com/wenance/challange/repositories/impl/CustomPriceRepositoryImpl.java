package com.wenance.challange.repositories.impl;

import com.wenance.challange.models.Price;
import com.wenance.challange.repositories.CustomPriceRepository;
import com.wenance.challange.repositories.result.AverageAndMaxPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Repository
public class CustomPriceRepositoryImpl implements CustomPriceRepository {

    @Autowired
    private ReactiveMongoTemplate reactiveMongoTemplate;

    @Override
    public Mono<AverageAndMaxPrice> getAverageAndMaxPricesBetweenTimestamps(Instant startTimestamp,
                                                                                 Instant finishTimestamp,
                                                                                 String currency1,
                                                                                 String currency2) {
        MatchOperation matchOperation = Aggregation.match(
                new Criteria("timestamp")
                        .gte(startTimestamp)
                        .lte(finishTimestamp)
                        .and("curr1").is(currency1)
                        .and("curr2").is(currency2)
        );
        GroupOperation avgAndMaxOperation = Aggregation.group("curr1", "curr2")
                .avg("lprice").as("averagePrice")
                .max("lprice").as("maxPrice");

        ProjectionOperation projectionOperation = Aggregation.project("averagePrice", "maxPrice");

        Aggregation aggregation = Aggregation.newAggregation(matchOperation, avgAndMaxOperation, projectionOperation);

        Flux<AverageAndMaxPrice> result = reactiveMongoTemplate.aggregate(
                aggregation,
                Price.class,
                AverageAndMaxPrice.class
        );

        return result.single();

    }
}
