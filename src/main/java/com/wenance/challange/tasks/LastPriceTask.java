package com.wenance.challange.tasks;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wenance.challange.dto.PairPrice;
import com.wenance.challange.services.PriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class LastPriceTask {
    private static final Logger logger = LoggerFactory.getLogger(LastPriceTask.class);

    @Autowired
    private WebClient webClient;

    @Autowired
    private PriceService priceService;

    @Scheduled(fixedRate = 10000)
    public void getBtcUsdPricesFromExternalAPI() {

        webClient
                .get()
                .uri("/last_price/BTC/USD")
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(body -> {
                    PairPrice pairPrice = null;
                    try {
                        pairPrice = new ObjectMapper().readValue(body, PairPrice.class);
                        priceService.savePrice(pairPrice);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                });
    }
}
