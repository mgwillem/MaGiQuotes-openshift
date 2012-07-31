package org.magi.quotes;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author <a href="mailto:mgw@mmx.lu">Marc Gabriel-Willem</a>
 */
public class QueryModel implements Serializable {

    private List<Query> model;

    @PostConstruct
    protected void init() {
        model = new ArrayList<Query>();
    }

    public void add(Query query) {
        if (query == null) throw new IllegalArgumentException("Argument cannot be null");
        if (query.getId() == null && query.getParent() == null) throw new IllegalArgumentException("Id and parent cannot be null");

        if (query.getParent() == null) {
            if (model.contains(query)) throw new IllegalArgumentException("Argument has an already existing 'id'");
            
            model.add(query);
        }
        else {
            query.getParent().getQueries().add(query);
        }
    }
    
    public List<Query> getModel() {
        return Collections.unmodifiableList(model);
    }

    public Query getQuery(String id) {
        return findQuery(model, id);
    }

    private Query findQuery(List<Query> queries, String id) {

        for (Query query : queries)
        {
            if (query.getId().equals(id)) return query;

            Query child = findQuery(query.getQueries(), id);
            if (child != null) return child;
        }

        return null;
    }


    @Override
    public String toString() {
        return "QueryModel{" +
                "model=" + model +
                '}';
    }
}
