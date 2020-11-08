package guru.springframework.msscbeerservice.services;

import guru.springframework.common.events.BeerDto;
import guru.springframework.common.events.BeerPagedList;
import guru.springframework.common.events.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface BeerService {
    BeerDto getById(UUID beerId, Boolean showInventoryOnHand);

    BeerDto saveNewBeer(BeerDto beerDto,Boolean showInventoryOnHand);

    BeerDto updateBeer(UUID beerId, BeerDto beerDto,Boolean showInventoryOnHand);

    BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest of, Boolean showInventory);

    BeerDto getByUpc(String upc);
}
