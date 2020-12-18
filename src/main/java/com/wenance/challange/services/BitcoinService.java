package com.wenance.challange.services;

import com.wenance.challange.dto.PriceDto;
import com.wenance.challange.models.Price;
import com.wenance.challange.repositories.PriceRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Instant;

@Service
public class BitcoinService {

    @Autowired
    private PriceRepository priceRepository;

    public Flux<Price> getPrice(Instant timestamp) {
        return priceRepository.findByTimestamp(timestamp);
    }

    public void savePrice(PriceDto priceDto) {
        Price price = new Price();
        BeanUtils.copyProperties(priceDto, price);
        priceRepository.save(price);
    }

    public Flux<Price> getAll() {
        return priceRepository.findAll();
    }
}
