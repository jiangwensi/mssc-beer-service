package com.jiangwensi.msscbeerservice.event;

import com.jiangwensi.msscbeerservice.web.model.BeerDto;

/**
 * Created by Jiang Wensi on 8/11/2020
 */
public class BrewBeerEvent extends BeerEvent {
    public BrewBeerEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
