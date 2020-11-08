package com.jiangwensi.msscbeerservice.services.brewing;

import com.jiangwensi.msscbeerservice.config.JMSConfig;
import com.jiangwensi.msscbeerservice.domain.Beer;
import com.jiangwensi.msscbeerservice.event.BrewBeerEvent;
import com.jiangwensi.msscbeerservice.event.NewInventoryEvent;
import com.jiangwensi.msscbeerservice.repositories.BeerRepository;
import com.jiangwensi.msscbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by Jiang Wensi on 8/11/2020
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class BrewBeerListener {

    private final BeerRepository beerRepository;
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JMSConfig.BREWING_REQUEST_QUEUE)
    public void listen(BrewBeerEvent event) {
        BeerDto beerDto = event.getBeerDto();
        Beer beer = beerRepository.getOne(beerDto.getId());
        beerDto.setQuantityOnHand(beer.getQuantityToBrew());
        NewInventoryEvent newInventoryEvent = new NewInventoryEvent(beerDto);
        log.debug("Brewed beer " + beer.getMinOnHand() + ": QOH: " + beerDto.getQuantityOnHand());
        jmsTemplate.convertAndSend(JMSConfig.NEW_INVENTORY_QUEUE, newInventoryEvent);
    }
}
