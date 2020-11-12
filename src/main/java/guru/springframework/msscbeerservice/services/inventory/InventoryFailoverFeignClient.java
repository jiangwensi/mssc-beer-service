package guru.springframework.msscbeerservice.services.inventory;

import guru.springframework.msscbeerservice.services.inventory.model.BeerInventoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.UUID;

/**
 * Created by Jiang Wensi on 12/11/2020
 */
@FeignClient(name="inventory-failover")
public interface InventoryFailoverFeignClient {
    @RequestMapping(method= RequestMethod.GET, value="/inventory-failover")
    ResponseEntity<List<BeerInventoryDto>> getOnhandInventory();
}
