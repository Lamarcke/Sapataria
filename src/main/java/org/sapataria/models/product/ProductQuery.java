package org.sapataria.models.product;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductQuery{
    private final String name;
    private final String brand;
    private final String model;

}
