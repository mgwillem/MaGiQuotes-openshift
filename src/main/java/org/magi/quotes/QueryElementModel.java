package org.magi.quotes;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author <a href="mailto:mgw@mmx.lu">Marc Gabriel-Willem</a>
 */
public class QueryElementModel implements Serializable {

    private List<QueryElement> model;

    @PostConstruct
    protected void init() {
        model = new ArrayList<QueryElement>();
    }

    public void add(Query query) {
        if (query == null) throw new IllegalArgumentException("Argument cannot be null");
        if (query.getParent() == null) throw new IllegalArgumentException("Argument's parent cannot be null");

        query.getParent().getQueries().add(query);
    }

    public void add(QueryCategory queryCategory) {
        if (queryCategory == null) throw new IllegalArgumentException("Argument cannot be null");

        if (queryCategory.getParent() != null) queryCategory.getParent().getQueries().add(queryCategory);
        else model.add(queryCategory);
    }

    public List<QueryElement> getModel() {
        return Collections.unmodifiableList(model);
    }

    public QueryElement getQueryElement(String id) {
        return findQueryElement(model, id);
    }

    private QueryElement findQueryElement(List<QueryElement> queryElements, String id) {

        for (QueryElement queryElement : queryElements)
        {
            if (queryElement.getId().equals(id)) return queryElement;

            if (queryElement instanceof QueryCategory) {
                QueryElement child = findQueryElement(((QueryCategory)queryElement).getQueries(), id);
                if (child != null) return child;
            }

        }

        return null;
    }

    @Override
    public String toString() {
        return "QueryElementModel{" +
                "model=" + model +
                '}';
    }
}
