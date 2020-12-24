package com.wenance.challange;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
class ChallangeApplicationTests {

	@Test
	void callTest() {
		WebTestClient webTestClient = WebTestClient.bindToServer().baseUrl("https://cex.io/api").build();
		webTestClient.get()
				.uri("/last_price/BTC/USD")
				.accept(MediaType.ALL)
				.exchange()
				.expectStatus().is2xxSuccessful()
				//.expectHeader().valueEquals("Content-Type", "text/json")
				.expectBody(String.class);
	}
}
