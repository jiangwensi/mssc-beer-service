package guru.springframework.common.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import java.io.IOException;

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

    @Disabled
    @Test
    void testDeserialize() throws IOException {
        String json = "{\"id\":\"f676747e-3d2a-402e-977b-0e42e08f3dd8\",\"version\":null,\"createdDate\":\"2020-11-05T15:37:49.5510145+08:00\",\"lastModifiedDate\":\"2020-11-05T15:37:49.5510145+08:00\",\"beerName\":\"Beer Name\",\"beerStyle\":\"ALE\",\"upc\":12312341234123,\"price\":\"12.99\",\"quantityOnHand\":null,\"myLocalDate\":\"20201105\"}";
        BeerDto dto = objectMapper.readValue(json,BeerDto.class);
        System.out.println(dto);
    }
}