package com.wenance.challange.services.impl;

import com.wenance.challange.dto.PairPrice;
import com.wenance.challange.models.Price;
import com.wenance.challange.repositories.PriceRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.Instant;


@SpringBootTest
class PriceServiceImplTest {

    @MockBean
    PriceRepository priceRepository;

    @Test
    void savePriceTest() {
        PairPrice pairPrice = new PairPrice("25639.1", "BTC", "USD");
        Price price = new Price();
        BeanUtils.copyProperties(pairPrice, price);
        price.setLprice(new BigDecimal(pairPrice.getLprice()));
        price.setTimestamp(Instant.now());

        Mockito.when(
                priceRepository.save(price)
        ).thenReturn(Mono.just(price).map(price1 -> {
            price1.setId("1");
            return price1;
        }));

        StepVerifier
                .create(priceRepository.save(price))
                .expectSubscription()
                .expectNextMatches(price1 -> ! (price1.getId() == null))
                .verifyComplete();
    }

    @Test
    void getAll() {
    }

    @Test
    void getPriceByTimestampTest() {
        Instant timestamp = Instant.now();
        String currency1 = "BTC";
        String currency2 = "USD";
        Price price = new Price(new BigDecimal("25639.1"), currency1, currency2, timestamp);

        Mockito.when(
                priceRepository.findByTimestampAndCurr1AndCurr2(timestamp, currency1, currency2)
        ).thenReturn(Mono.just(price));

        StepVerifier
                .create(priceRepository.findByTimestampAndCurr1AndCurr2(timestamp, currency1, currency2))
                .expectSubscription()
                .expectNextMatches(price1 -> price1.getTimestamp().equals(price.getTimestamp()))
                .verifyComplete();
    }

    @Test
    void getPriceWithWrongTimestampMustBeEmptyTest() {
        Instant timestamp = Instant.now();
        String currency1 = "BTC";
        String currency2 = "USD";
        Price price = new Price(new BigDecimal("25639.1"), currency1, currency2, timestamp);
        Instant wrongTimestamp = Instant.now();

        Mockito.when(
                priceRepository.findByTimestampAndCurr1AndCurr2(wrongTimestamp, currency1, currency2)
        ).thenReturn(Mono.empty());

        StepVerifier
                .create(priceRepository.findByTimestampAndCurr1AndCurr2(wrongTimestamp, currency1, currency2))
                .expectSubscription()
                .expectNextCount(0)
                .verifyComplete();
    }

    @Test
    void getPricesBetweenTimestampsTest() {
    }

    @Test
    void getAverageAndMaxPricesBetweenTimestampsTest() {
    }
}