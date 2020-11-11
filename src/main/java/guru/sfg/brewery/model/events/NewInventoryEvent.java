package guru.sfg.brewery.model.events;

import guru.sfg.brewery.model.BeerDto;
import lombok.NoArgsConstructor;

/**
 * Created by Jiang Wensi on 8/11/2020
 */
@NoArgsConstructor
public class NewInventoryEvent extends BeerEvent {
    public NewInventoryEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
