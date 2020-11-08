package com.jiangwensi.msscbeerservice.services;

import com.jiangwensi.msscbeerservice.domain.Beer;
import com.jiangwensi.msscbeerservice.event.BrewBeerEvent;
import com.jiangwensi.msscbeerservice.repositories.BeerRepository;
import com.jiangwensi.msscbeerservice.services.inventory.BeerInventoryService;
import com.jiangwensi.msscbeerservice.web.mappers.BeerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.jiangwensi.msscbeerservice.config.JMSConfig.BREWING_REQUEST_QUEUE;

/**
 * Created by Jiang Wensi on 8/11/2020
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BrewingService {
    private final BeerRepository beerRepository;
    private final BeerInventoryService beerInventoryService;
    private final JmsTemplate jmsTemplate;
    private final BeerMapper beerMapper;

    @Scheduled(fixedRate = 5000)//every 5 seconds
    public void checkForLowInventory() {
        List<Beer> beers = beerRepository.findAll();
        beers.forEach(beer -> {
            Integer invQOH = beerInventoryService.getOnhandInventory(beer.getId());
            log.debug("Min Onhand is " + beer.getMinOnHand());
            log.debug("Inventory is " + invQOH);
            if (beer.getMinOnHand() >= invQOH) {
                jmsTemplate.convertAndSend(BREWING_REQUEST_QUEUE,
                        new BrewBeerEvent(beerMapper.beerToBeerDtoHideInventory(beer)));
            }
        });
    }
}
