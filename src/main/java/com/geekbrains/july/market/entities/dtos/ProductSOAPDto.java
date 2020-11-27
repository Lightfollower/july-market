package com.geekbrains.july.market.entities.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@Data
public class ProductSOAPDto {
    @XmlElement(required = true)
    private Long id;

    @XmlElement(required = true)
    private String title;

    @XmlElement(required = true)
    private BigDecimal price;
}
