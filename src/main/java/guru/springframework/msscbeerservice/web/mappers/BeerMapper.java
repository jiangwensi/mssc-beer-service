package guru.springframework.msscbeerservice.web.mappers;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.common.events.BeerDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
@DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper {
    BeerDto beerToBeerDtoShowInventory(Beer beer);
    BeerDto beerToBeerDtoHideInventory(Beer e);
    Beer beerDtoToBeer(BeerDto dto);
}
