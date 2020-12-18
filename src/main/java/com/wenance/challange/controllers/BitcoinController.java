package com.wenance.challange.controllers;

import com.wenance.challange.models.Price;
import com.wenance.challange.services.BitcoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/bitcoin")
public class BitcoinController {

    @Autowired
    private BitcoinService bitcoinService;

    @GetMapping("/all")
    public Flux<Price> getAll() {
        return bitcoinService.getAll();
    }

    @GetMapping
    public Flux<Price> getPrice(@RequestParam("timestamp") Instant timestamp) {
        return bitcoinService.getPrice(timestamp);
    }
}
