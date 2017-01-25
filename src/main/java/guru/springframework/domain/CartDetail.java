package guru.springframework.domain;

import javax.persistence.*;
import static javax.persistence.CascadeType.*;

/**
 * Created by YSkakun on 11/3/2016.
 */
@Entity
public class CartDetail extends AbstractDomainClass {

    @ManyToOne
    private Cart cart;

    @OneToOne(cascade = {PERSIST, MERGE, REMOVE, REFRESH, DETACH})
    private Product product;

    private Integer quantity;

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
