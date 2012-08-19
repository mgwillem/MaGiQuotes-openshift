package org.magi.quotes.service.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author <a href="mailto:mgw@mmx.lu">Marc Gabriel-Willem</a>
 */
@Entity
@NamedQueries({
        @NamedQuery(name = ProductPrice.findAll, query = "SELECT p FROM ProductPrice p")
})
public class ProductPrice {

    public static final String PREFIX = "org.magi.quotes.service.entity.ProductPrice.";
    public static final String findAll = PREFIX + "findAll";

    @Id
    @Enumerated(value = EnumType.STRING)
    private Product product;

    private BigDecimal defaultPrice;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(BigDecimal defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    @Override
    public String toString() {
        return "ProductPrice{" +
                "product=" + product +
                ", defaultPrice=" + defaultPrice +
                '}';
    }
}
