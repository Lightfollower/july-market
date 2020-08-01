package com.geekbrains.july.market.beans;

import com.geekbrains.july.market.entities.OrderItem;
import com.geekbrains.july.market.entities.Product;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Data
public class Cart {
    private List<OrderItem> items;
    private int price;

    @PostConstruct
    public void init() {
        items = new ArrayList<>();
    }

    public void clear() {
        items.clear();
        recalculate();
    }

    private void recalculate() {
        for (OrderItem i :
                items) {
            price += i.getPrice();
        }
    }

    public void add(Product product) {
        for (OrderItem i :
                items) {
            if (i.getProduct().getId().equals(product.getId())) {
                i.setQuantity(i.getQuantity() + 1);
                i.setPrice((i.getProduct().getPrice() * i.getQuantity()));
                recalculate();
                return;
            }
        }
    }

    public void removeByProductId(int productId) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getProduct().getId().equals(productId)) {
                items.remove(i);
                recalculate();
                return;
            }
        }
    }

    public void addProductToCart(Product product, int quantity) {
        for (OrderItem item :
                items) {
            if (product.getId() == (item.getProduct().getId())) {
                item.setQuantity(item.getQuantity() + quantity);
                item.setPrice(product.getPrice() * item.getQuantity());
                return;
            }
        }
        items.add(new OrderItem(product, quantity, product.getPrice() * quantity));
    }

    public List<OrderItem> getCart() {
        return items;
    }
}
