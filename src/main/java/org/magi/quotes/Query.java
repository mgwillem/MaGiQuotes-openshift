package org.magi.quotes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:mgw@mmx.lu">Marc Gabriel-Willem</a>
 */
public class Query implements Serializable {
    private String id;
    private Query parent;
    private Product product;
    private Object value;
    private QueryType queryType;
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

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public List<Query> getQueries() {
        return queries;
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
