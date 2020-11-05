package com.jiangwensi.msscbeerservice.services;

import com.jiangwensi.msscbeerservice.domain.Beer;
import com.jiangwensi.msscbeerservice.repositories.BeerRepository;
import com.jiangwensi.msscbeerservice.web.controller.NotFoundException;
import com.jiangwensi.msscbeerservice.web.mappers.BeerMapper;
import com.jiangwensi.msscbeerservice.web.model.BeerDto;
import com.jiangwensi.msscbeerservice.web.model.BeerPagedList;
import com.jiangwensi.msscbeerservice.web.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by Jiang Wensi on 5/11/2020
 */
@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public BeerDto getById(UUID beerId) {
        return beerMapper.beerToBeerDto(
                beerRepository.findById(beerId).orElseThrow(()->new NotFoundException())
        );
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        return beerMapper.beerToBeerDto(
                beerRepository.save(beerMapper.beerDtoToBeer(beerDto)));
    }

    @Override
    public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
        Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);
        beer.setBeerName(beerDto.getBeerName());
        beer.setBeerStyle(beerDto.getBeerStyle().name());
        beer.setPrice(beerDto.getPrice());
        beer.setUpc(beerDto.getUpc());
        return beerMapper.beerToBeerDto(beerRepository.save(beer));
    }

    @Override
    public BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest) {
        BeerPagedList beerPagedList;
        Page<Beer> beerPage;
        if(!StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)){
            beerPage = beerRepository.findAllByBeerNameAndBeerStyle(beerName,beerStyle, pageRequest);
        } else if (!StringUtils.isEmpty(beerName) && StringUtils.isEmpty(beerStyle)) {
            beerPage = beerRepository.findAllByBeerName(beerName, pageRequest);
        } else if(StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)){
            beerPage = beerRepository.findAllByBeerStyle(beerStyle, pageRequest);
        } else {
            beerPage = beerRepository.findAll(pageRequest);
        }

        beerPagedList = new BeerPagedList(
                beerPage.getContent()
                        .stream()
                        .map(beerMapper::beerToBeerDto)
                        .collect(Collectors.toList()),
                        PageRequest
                                .of(beerPage.getPageable().getPageNumber(),
                        beerPage.getPageable().getPageSize()),
                        beerPage.getTotalElements());

        return beerPagedList;
    }
}
