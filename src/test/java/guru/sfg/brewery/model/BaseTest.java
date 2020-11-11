package guru.sfg.brewery.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.sfg.brewery.model.BeerDto;
import guru.sfg.brewery.model.BeerStyleEnum;
import guru.springframework.msscbeerservice.bootstrap.BeerLoader;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Created by Jiang Wensi on 5/11/2020
 */
public class BaseTest {
    @Autowired
    ObjectMapper objectMapper;

    BeerDto getDto(){
        return BeerDto.builder()
                .beerName("Beer Name")
                .beerStyle(BeerStyleEnum.PALE_ALE)
                .id(UUID.randomUUID())
                .createdDate(OffsetDateTime.now())
                .lastModifiedDate(OffsetDateTime.now())
                .price(new BigDecimal("12.99"))
                .upc(BeerLoader.BEER_2_UPC)
//                .myLocalDate(LocalDate.now())
                .build();
    }
}
