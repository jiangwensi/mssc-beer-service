package guru.springframework.msscbeerservice.services.order;

import guru.sfg.brewery.model.BeerOrderDto;
import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Jiang Wensi on 10/11/2020
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class BeerOrderValidator {
    private final BeerRepository beerRepository;
    public Boolean validateOrder(BeerOrderDto beerOrder){
        AtomicInteger beersNotFound = new AtomicInteger();
        beerOrder.getBeerOrderLines().forEach(orderline -> {
            if (beerRepository.findByUpc(orderline.getUpc())==null){
                beersNotFound.incrementAndGet();
            }
        });
        return beersNotFound.get()==0;
    }
}
