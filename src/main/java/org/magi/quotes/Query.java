package org.magi.quotes;

import com.sun.jdi.IntegerType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:mgw@mmx.lu">Marc Gabriel-Willem</a>
 */
public class Query implements Serializable {
    private String id;
    private Query parent;
    private QueryType queryType;
    private Product product;

    private Integer selectedInteger;
    private BigDecimal selectedDecimal = BigDecimal.ZERO;
    private Product selectedProduct;

    private List<Query> queries;

    public Query(String id, Product product, QueryType queryType) {
        this(id, null, product, queryType);
    }

    public Query(String id, Query parent, Product product, QueryType queryType) {
        this.id = id;
        this.parent = parent;
        this.product = product;
        this.queryType = queryType;
        this.queries = new ArrayList<Query>();
    }

    public String getId() {
        return id;
    }

    public Query getParent() {
        return parent;
    }

    public Product getProduct() {
        return product;
    }

    public QueryType getQueryType() {
        return queryType;
    }

    public List<Query> getQueries() {
        return queries;
    }


    public Integer getSelectedInteger() {
        return selectedInteger;
    }

    public void setSelectedInteger(Integer selectedInteger) {
        if (getValueType() != Integer.class) throw new IllegalStateException("Cannot use this method");
        this.selectedInteger = selectedInteger;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        if (getValueType() != Product.class) throw new IllegalStateException("Cannot use this method");
        this.selectedProduct = selectedProduct;
    }

    public BigDecimal getSelectedDecimal() {
        return selectedDecimal;
    }

    public void setSelectedDecimal(BigDecimal selectedDecimal) {
        if (getValueType() != BigDecimal.class) throw new IllegalStateException("Cannot use this method");
        this.selectedDecimal = selectedDecimal;
    }

    public Class getValueType() {
        if (product.getProducts() != null) return Product.class;
        if (product.getPriceType() == PriceType.ML || product.getPriceType() == PriceType.FF || product.getPriceType() == PriceType.PC) return Integer.class;
        return BigDecimal.class;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Query that = (Query) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Query{" +
                "id=" + id +
                ", parent=" + parent +
                ", product=" + product +
                ", queryType=" + queryType +
                ", queries=" + queries +
                '}';
    }
}
