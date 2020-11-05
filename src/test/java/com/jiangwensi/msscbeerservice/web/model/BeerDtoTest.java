package com.jiangwensi.msscbeerservice.web.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@JsonTest
class BeerDtoTest extends BaseTest {

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testSerializeDto() throws JsonProcessingException {
        BeerDto beerDto = getDto();
        String jsonString = objectMapper.writeValueAsString(beerDto);
        System.out.println(jsonString);
    }


    @Test
    void testDeserialize() throws IOException {
        String json = "{\"id\":\"90e69474-161d-4bdc-9fea-fe1394b990cd\",\"version\":null,\"createdDate\":\"2020-11-05T13:32:33.3978634+08:00\",\"lastModifiedDate\":\"2020-11-05T13:32:33.3978634+08:00\",\"beerName\":\"BeerName\",\"beerStyle\":\"ALE\",\"upc\":12312341234123,\"price\":12.99,\"quantityOnHand\":null}";
        BeerDto dto = objectMapper.readValue(json,BeerDto.class);
        System.out.println(dto);
    }
}