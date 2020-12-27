package com.wenance.challange.services.impl;

import com.wenance.challange.dto.PairPrice;
import com.wenance.challange.models.Price;
import com.wenance.challange.repositories.PriceRepository;
import com.wenance.challange.repositories.result.AverageAndMaxPrice;
import com.wenance.challange.services.PriceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.Instant;

@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    @Autowired
    public PriceServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public PriceRepository getPriceRepository() {
        return priceRepository;
    }

    @Override
    public Mono<Price> savePrice(PairPrice pairPrice) {
        Price price = new Price();
        BeanUtils.copyProperties(pairPrice, price);
        price.setLprice(new BigDecimal(pairPrice.getLprice()));
        price.setTimestamp(Instant.now());
        Mono<Price> priceMono = priceRepository.save(price);
        priceMono.subscribe();
        return priceMono;
    }

    @Override
    public Flux<Price> getAll() {
        return priceRepository.findAll();
    }

    @Override
    public Mono<Price> getPriceByTimestamp(Instant timestamp, String currency1, String currency2) {
        return priceRepository.findByTimestampAndCurr1AndCurr2(timestamp, currency1, currency2);
    }

    @Override
    public Flux<Price> getPricesBetweenTimestamps(Instant startTimestamp, Instant finishTimestamp,
                                                  String currency1, String currency2) {
        return priceRepository.findByTimestampBetweenAndCurr1AndCurr2(startTimestamp, finishTimestamp,
                currency1, currency2);
    }

    @Override
    public Mono<AverageAndMaxPrice> getAverageAndMaxPricesBetweenTimestamps(Instant startTimestamp, Instant finishTimestamp,
                                                                            String currency1, String currency2) {
        return priceRepository.getAverageAndMaxPricesBetweenTimestamps(startTimestamp, finishTimestamp, currency1, currency2);
    }

}
