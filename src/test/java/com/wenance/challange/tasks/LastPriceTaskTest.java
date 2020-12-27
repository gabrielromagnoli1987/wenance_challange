package com.wenance.challange.tasks;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

class LastPriceTaskTest {

    @Test
    void getBtcUsdPricesFromExternalAPITest() {
        WebTestClient webTestClient = WebTestClient.bindToServer().baseUrl("https://cex.io/api").build();
        webTestClient.get()
                .uri("/last_price/BTC/USD")
                .accept(MediaType.ALL)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectHeader().valueEquals("Content-Type", "text/json")
                .expectBody()
                .jsonPath("$.lprice").exists()
                .jsonPath("$.curr1").isEqualTo("BTC")
                .jsonPath("$.curr2").isEqualTo("USD");
    }
}