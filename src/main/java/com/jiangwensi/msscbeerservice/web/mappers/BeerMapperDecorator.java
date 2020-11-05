package com.jiangwensi.msscbeerservice.web.mappers;

import com.jiangwensi.msscbeerservice.domain.Beer;
import com.jiangwensi.msscbeerservice.services.inventory.BeerInventoryService;
import com.jiangwensi.msscbeerservice.web.model.BeerDto;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Jiang Wensi on 5/11/2020
 */
public abstract class BeerMapperDecorator implements BeerMapper {
    private BeerInventoryService beerInventoryService;
    private BeerMapper mapper;

    @Autowired
    public void setBeerInventoryService(BeerInventoryService beerInventoryService) {
        this.beerInventoryService = beerInventoryService;
    }

    @Autowired
    public void setMapper(BeerMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public BeerDto beerToBeerDtoShowInventory(Beer beer) {
        BeerDto dto = mapper.beerToBeerDtoShowInventory(beer);
        dto.setQuantityOnHand(beerInventoryService.getOnhandInventory(beer.getId()));
        return dto;
    }

    @Override
    public Beer beerDtoToBeer(BeerDto beerDto) {
        return mapper.beerDtoToBeer(beerDto);
    }
}
