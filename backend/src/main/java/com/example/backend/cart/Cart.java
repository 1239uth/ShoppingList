package com.example.backend.cart;

import com.example.backend.item_in_cart.ItemInCart;
import com.example.backend.items.Item;
import com.example.backend.user.User;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Cart")
@Table(name = "carts")
public class Cart {
    private static final float TAX_RATE = 1.13f;

    @Id
    @SequenceGenerator(name = "cart_sequence", sequenceName = "cart_sequence", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "cart_sequence")
    @Column(name = "cart_id")
    private Long id;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id", referencedColumnName = "cart_id")
    private List<ItemInCart> itemsInCart = new ArrayList<>();
    @Transient
    private Integer length;

    @Transient
    private Float subCost;

    @Transient
    private Float totalCost;

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    @Transient
    private Integer discount;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Cart(List<ItemInCart> itemsInCart, String userName) {
        this.itemsInCart = itemsInCart;
        this.user = null;
    }

    public Cart() {
    }

    public Float getSubCost() {
        float cost = 0;
        for (ItemInCart item : itemsInCart) {
            cost += item.getPrice();
        }
        return cost;
    }

    public void setSubCost(Float subCost) {
        this.subCost = subCost;
    }

    public Float getTotalCost() {
        float totalCost = getSubCost() * TAX_RATE;
        return discount == null ? totalCost : totalCost * (100-discount)/100f;
    }

    public void setTotalCost(Float totalCost) {
        this.totalCost = totalCost;
    }

    public void addItem(Item item) {
        for (ItemInCart itemInCart : this.itemsInCart) {
            if (itemInCart.getItem().getId().equals(item.getId())) {
                itemInCart.incrementQuantity();
                return;
            }
        }
        this.itemsInCart.add(new ItemInCart(1, item));
    }

    public void removeItem(ItemInCart itemInCart) {
        this.itemsInCart.remove(itemInCart);
    }

    public boolean updateItemQuantity(ItemInCart item, int newQuantity) {
        int itemInCart = itemsInCart.indexOf(item);
        if (itemInCart != -1) {
            this.itemsInCart.get(itemInCart).setQuantity(newQuantity);
            return true;
        }
        return false;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", itemsInCart=" + itemsInCart +
                ", length=" + length +
                ", subCost=" + subCost +
                ", totalCost=" + totalCost +
                ", user=" + user +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ItemInCart> getItemsInCart() {
        return itemsInCart;
    }

    public void setItemsInCart(List<ItemInCart> itemsInCart) {
        this.itemsInCart = itemsInCart;
    }

    public Integer getLength() {
        int count = 0;
        for (ItemInCart itemInCart : itemsInCart) {
            count += itemInCart.getQuantity();
        }
        return count;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cart cart)) return false;
        return Objects.equals(id, cart.id) && Objects.equals(itemsInCart, cart.itemsInCart) && Objects.equals(length, cart.length) && Objects.equals(subCost, cart.subCost) && Objects.equals(totalCost, cart.totalCost) && Objects.equals(discount, cart.discount) && Objects.equals(user, cart.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, itemsInCart, length, subCost, totalCost, discount, user);
    }
}