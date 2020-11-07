package com.jiangwensi.msscbeerservice.services.inventory;

import com.jiangwensi.msscbeerservice.bootstrap.BeerLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Created by Jiang Wensi on 5/11/2020
 */
@Disabled
@SpringBootTest
public class BeerInventoryServiceRestTemplateImplTest {
    @Autowired
    BeerInventoryService beerInventoryService;

    @BeforeEach
    void setUp(){

    }

    @Test
    void getOnhandInventory() {
        //todo evolve to use UPC
//        Integer qoh = beerInventoryService.getOnhandInventory(BeerLoader.BEER_1_UUID);
//        System.out.println(qoh);
    }
}
