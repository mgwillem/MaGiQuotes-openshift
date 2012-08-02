package org.magi.quotes;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:mgw@mmx.lu">Marc Gabriel-Willem</a>
 */
public class QueryCategory implements QueryElement {

    private String id;
    private Product product;
    private QueryCategoryType type;

    private QueryCategory parent;
    private List<QueryElement> queries;

    public QueryCategory(String id, Product product, QueryCategoryType type) {
        this(id, null, product, type);
    }

    public QueryCategory(String id, QueryCategory parent, Product product, QueryCategoryType type) {
        this.id = id;
        this.parent = parent;
        this.product = product;
        this.type = type;
        this.queries = new ArrayList<QueryElement>();
    }

    public String getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public QueryCategoryType getType() {
        return type;
    }

    public QueryCategory getParent() {
        return parent;
    }

    public List<QueryElement> getQueries() {
        return queries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QueryCategory that = (QueryCategory) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
