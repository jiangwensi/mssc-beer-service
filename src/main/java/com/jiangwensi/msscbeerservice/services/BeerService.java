package com.jiangwensi.msscbeerservice.services;

import com.jiangwensi.msscbeerservice.web.model.BeerDto;
import com.jiangwensi.msscbeerservice.web.model.BeerPagedList;
import com.jiangwensi.msscbeerservice.web.model.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface BeerService {
    BeerDto getById(UUID beerId,Boolean showInventoryOnHand);

    BeerDto saveNewBeer(BeerDto beerDto,Boolean showInventoryOnHand);

    BeerDto updateBeer(UUID beerId, BeerDto beerDto,Boolean showInventoryOnHand);

    BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest of, Boolean showInventory);
}
