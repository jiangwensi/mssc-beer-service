package com.jiangwensi.msscbeerservice.services;

import com.jiangwensi.msscbeerservice.web.model.BeerDto;
import com.jiangwensi.msscbeerservice.web.model.BeerPagedList;
import com.jiangwensi.msscbeerservice.web.model.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface BeerService {
    BeerDto getById(UUID beerId);

    BeerDto saveNewBeer(BeerDto beerDto);

    BeerDto updateBeer(UUID beerId, BeerDto beerDto);

    BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest of);
}
