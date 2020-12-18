package com.wenance.challange.tasks;

import com.wenance.challange.dto.PriceDto;
import com.wenance.challange.services.BitcoinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
public class LastPriceTask {
    private static final Logger logger = LoggerFactory.getLogger(LastPriceTask.class);

    @Value("${external-api}")
    private static String EXTERNAL_API;

    private static final WebClient webClient = WebClient.builder().baseUrl(EXTERNAL_API).build();

    @Autowired
    private BitcoinService bitcoinService;

    @Scheduled(fixedRate = 10000)
    public void getBtcUsdPricesFromExternalAPI() {

        Flux<PriceDto> priceDtoFlux = webClient.get().uri("/last_price/BTC/USD").retrieve().bodyToFlux(PriceDto.class);
        priceDtoFlux.map(priceDto -> {
            bitcoinService.savePrice(priceDto);
            return null;
        }).subscribe();

    }
}
