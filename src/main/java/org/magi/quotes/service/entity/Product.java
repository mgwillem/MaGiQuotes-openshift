package org.magi.quotes.service.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author <a href="mailto:mgw@mmx.lu">Marc Gabriel-Willem</a>
 */
public enum Product implements Serializable {

    CAT1("Calcul de la matière avec les chants vus"),

    CAT1_Q1_1("Matiere-1", BigDecimal.TEN, PriceType.M2),
    CAT1_Q1_2("Matiere-2", BigDecimal.TEN, PriceType.M2),
    CAT1_Q1_3("Matiere-3", BigDecimal.TEN, PriceType.M2),

    CAT1_Q1("Nom de la matière", BigDecimal.TEN, PriceType.PC, new Product[]{CAT1_Q1_1, CAT1_Q1_2, CAT1_Q1_3}),
    CAT1_Q2("Nombre de plaques à produire", BigDecimal.TEN, PriceType.PC),
    CAT1_Q3("Surface en m2", BigDecimal.TEN, PriceType.M2),

    CAT1_1("Totalité des chants à vu en mètre courant"),
    CAT1_1_Q1("Chant poli et biseauté", BigDecimal.TEN, PriceType.ML),
    CAT1_1_Q2("Chant poli et arrondi en 1/4 de lune", BigDecimal.TEN, PriceType.ML),

    CAT2("Calcul des découpes"),
    CAT2_Q1("Découpe évier rectangulaire non façonnée", BigDecimal.ONE, PriceType.PC),
    CAT2_Q2("Découpe évier rectangulaire façonnée", BigDecimal.ONE, PriceType.PC),
    CAT2_Q3("Découpe plaque de cuisson rectangulaire non façonnée", BigDecimal.ONE, PriceType.PC),
    CAT2_Q4("Découpe plaque de cuisson rectangulaire façonnée", BigDecimal.ONE, PriceType.PC);

    private String description;
    private BigDecimal price;
    private PriceType priceType;
    private Product[] products;

    private Product(String description) {
        this(description, null, null);
    }

    private Product(String description, BigDecimal price, PriceType priceType) {
        this(description, price, priceType, null);
    }

    private Product(String description, BigDecimal price, PriceType priceType, Product[] products) {
        this.description = description;
        this.price = price;
        this.priceType = priceType;
        this.products = products;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public PriceType getPriceType() {
        return priceType;
    }

    public Product[] getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "Product{" +
                "description='" + description + '\'' +
                ", price=" + price +
                ", priceType=" + priceType +
                '}';
    }
}
