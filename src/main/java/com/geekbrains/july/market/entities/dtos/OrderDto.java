package com.geekbrains.july.market.entities.dtos;

import com.geekbrains.july.market.entities.OrderItem;
import com.geekbrains.july.market.entities.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@ApiModel("Order dto in the application")
public interface OrderDto {
    @ApiModelProperty(notes = "Unique identifier of the order. No two orders can have the same id.", example = "1", required = true, position = 0)
    Long getId();

    @ApiModelProperty(notes = "Items in order.", required = true, position = 2)
    List<OrderItem> getItems();

    BigDecimal getPrice();

    String getPhone();

    String getAddress();
}
