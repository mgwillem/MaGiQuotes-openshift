package org.magi.quotes.service.boundary;

import org.magi.quotes.service.entity.Query;
import org.magi.quotes.service.entity.QueryCategory;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Alternative;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author <a href="mailto:mgw@mmx.lu">Marc Gabriel-Willem</a>
 */
@Alternative
public class QueryElementModel implements Serializable {

    private List<QueryElement> model;

    protected QueryElementModel() {}

    @PostConstruct
    protected void init() {
        model = new ArrayList<QueryElement>();
    }

    void add(Query query) {
        if (query == null) throw new IllegalArgumentException("Argument cannot be null");
        if (query.getParent() == null) throw new IllegalArgumentException("Argument's parent cannot be null");

        query.getParent().getQueries().add(query);
    }

    void add(QueryCategory queryCategory) {
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

        for (QueryElement queryElement : queryElements) {
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
