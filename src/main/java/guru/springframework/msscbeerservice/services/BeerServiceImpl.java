package guru.springframework.msscbeerservice.services;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.repositories.BeerRepository;
import guru.springframework.msscbeerservice.web.controller.NotFoundException;
import guru.springframework.msscbeerservice.web.mappers.BeerMapper;
import guru.sfg.brewery.model.BeerDto;
import guru.sfg.brewery.model.BeerPagedList;
import guru.sfg.brewery.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by Jiang Wensi on 5/11/2020
 */
@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Cacheable(cacheNames = "beerCache", key = "#beerId",
            condition = "#showInventoryOnHand == null || #showInventoryOnHand == false")
    @Override
    public BeerDto getById(UUID beerId, Boolean showInventoryOnHand) {
        if (showInventoryOnHand != null && showInventoryOnHand) {
            return beerMapper.beerToBeerDtoShowInventory(
                    beerRepository.findById(beerId).orElseThrow(() -> new NotFoundException()));
        } else {
            return beerMapper.beerToBeerDtoHideInventory(
                    beerRepository.findById(beerId).orElseThrow(() -> new NotFoundException()));
        }
    }


    @Cacheable(cacheNames = "beerUpcCache")
    @Override
    public BeerDto getByUpc(String upc) {
        System.out.println("getByUpc is called");

        return beerMapper.beerToBeerDtoShowInventory(beerRepository.findByUpc(upc));
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto, Boolean showInventoryOnHand) {
        if (showInventoryOnHand != null && showInventoryOnHand) {
            return beerMapper.beerToBeerDtoShowInventory(
                    beerRepository.save(beerMapper.beerDtoToBeer(beerDto)));
        } else {
            return beerMapper.beerToBeerDtoHideInventory(
                    beerRepository.save(beerMapper.beerDtoToBeer(beerDto)));
        }
    }

    @Override
    public BeerDto updateBeer(UUID beerId, BeerDto beerDto, Boolean showInventoryOnHand) {
        Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);
        beer.setBeerName(beerDto.getBeerName());
        beer.setBeerStyle(beerDto.getBeerStyle().name());
        beer.setPrice(beerDto.getPrice());
        beer.setUpc(beerDto.getUpc());

        if (showInventoryOnHand != null && showInventoryOnHand) {
            return beerMapper.beerToBeerDtoShowInventory(beerRepository.save(beer));
        } else {
            return beerMapper.beerToBeerDtoHideInventory(beerRepository.save(beer));
        }
    }

    @Cacheable(cacheNames = "beerListCache", condition = "#showInventoryOnHand == null || #showInventoryOnHand == false ")
    @Override
    public BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest,
                                   Boolean showInventoryOnHand) {
        BeerPagedList beerPagedList;
        Page<Beer> beerPage;
        if (!StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
            beerPage = beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyle, pageRequest);
        } else if (!StringUtils.isEmpty(beerName) && StringUtils.isEmpty(beerStyle)) {
            beerPage = beerRepository.findAllByBeerName(beerName, pageRequest);
        } else if (StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
            beerPage = beerRepository.findAllByBeerStyle(beerStyle, pageRequest);
        } else {
            beerPage = beerRepository.findAll(pageRequest);
        }

        Function<Beer, BeerDto> mapBeerToBeerDto;

        if (showInventoryOnHand != null && showInventoryOnHand) {
            mapBeerToBeerDto = (e) -> beerMapper.beerToBeerDtoShowInventory(e);
        } else {
            mapBeerToBeerDto = (e) -> beerMapper.beerToBeerDtoHideInventory(e);
        }

        beerPagedList = new BeerPagedList(
                beerPage.getContent()
                        .stream()
                        .map(mapBeerToBeerDto)
                        .collect(Collectors.toList()),
                PageRequest
                        .of(beerPage.getPageable().getPageNumber(),
                                beerPage.getPageable().getPageSize()),
                beerPage.getTotalElements());

        return beerPagedList;
    }

}
