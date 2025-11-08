package com.estore.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Cart {
    private final List<CartItem> items = new ArrayList<>();

    public void addItem(Product product, int qty) {
        for (CartItem item : items) {
            if (item.getProduct().getId() == product.getId()) {
                item.setQuantity(item.getQuantity() + qty);
                return;
            }
        }
        items.add(new CartItem(product, qty));
    }

    public void updateQuantity(long productId, int qty) {
        for (CartItem item : items) {
            if (item.getProduct().getId() == productId) {
                if (qty <= 0) {
                    items.remove(item);
                } else {
                    item.setQuantity(qty);
                }
                return;
            }
        }
    }

    public void removeItem(long productId) {
        Iterator<CartItem> iterator = items.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getProduct().getId() == productId) {
                iterator.remove();
                return;
            }
        }
    }

    public void clear() {
        items.clear();
    }

    public List<CartItem> getItems() {
        return items;
    }

    public BigDecimal getTotal() {
        return items.stream()
                .map(CartItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public int getTotalCount() {
        return items.stream()
                .mapToInt(CartItem::getQuantity)
                .sum();
    }

}
