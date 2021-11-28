package ru.gb.yukjdev.entities;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Product {
    private Integer id;
    private String title;
    private BigDecimal cost;


}
