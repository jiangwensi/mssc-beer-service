package guru.springframework.common.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * Created by Jiang Wensi on 5/11/2020
 */
@ActiveProfiles("snake")
@JsonTest
public class BeerDtoSnakeTest extends BaseTest{

    @Test
    void testSnake() throws JsonProcessingException {
        BeerDto dto = getDto();
        String json = objectMapper.writeValueAsString(dto);
        System.out.println(json);
    }

}
