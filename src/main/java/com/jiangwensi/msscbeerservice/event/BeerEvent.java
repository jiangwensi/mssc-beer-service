package com.jiangwensi.msscbeerservice.event;

import com.jiangwensi.msscbeerservice.web.model.BeerDto;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Created by Jiang Wensi on 8/11/2020
 */
@Data
@RequiredArgsConstructor
@Builder
public class BeerEvent implements Serializable {

    private static final long serialVersionUID = -3562057938789843845L;
    private final BeerDto beerDto;

}
