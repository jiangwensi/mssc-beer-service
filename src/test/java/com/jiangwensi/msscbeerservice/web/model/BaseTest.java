package com.jiangwensi.msscbeerservice.web.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Created by Jiang Wensi on 5/11/2020
 */
public class BaseTest {
    BeerDto getDto(){
        return BeerDto.builder()
                .beerName("Beer Name")
                .beerStyle(BeerStyleEnum.ALE)
                .id(UUID.randomUUID())
                .createdDate(OffsetDateTime.now())
                .lastModifiedDate(OffsetDateTime.now())
                .price(new BigDecimal("12.99"))
                .upc(12312341234123l)
                .build();
    }
}
