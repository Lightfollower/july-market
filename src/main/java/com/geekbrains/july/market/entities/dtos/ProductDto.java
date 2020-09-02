package com.geekbrains.july.market.entities.dtos;

import lombok.Data;
import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "product", propOrder = {
        "id",
        "title",
        "price",
})
@Data
public class ProductDto {
    @XmlElement(required = true)
    Long id;
    @XmlElement(required = true)
    String title;
    @XmlElement(required = true)
    BigDecimal price;
}
