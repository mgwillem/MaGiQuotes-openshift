package org.magi.quotes.service.control;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @author <a href="mailto:mgw@mmx.lu">Marc Gabriel-Willem</a>
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class CrudService<T> {

    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    public T create(T t) {
        this.em.persist(t);
        this.em.flush();
        this.em.refresh(t);
        return t;
    }

    @SuppressWarnings("unchecked")
    public T find(Class type, Object id) {
        return (T) this.em.find(type, id);
    }

    public void delete(Class type, Object id) {
        Object ref = this.em.getReference(type, id);
        this.em.remove(ref);
    }

    public T update(T t) {
        return (T) this.em.merge(t);
    }

    public List findWithNamedQuery(String namedQueryName) {
        return this.em.createNamedQuery(namedQueryName).getResultList();
    }

    public T findSingleWithNamedQuery(String namedQueryName) {
        return (T) this.em.createNamedQuery(namedQueryName).getSingleResult();
    }

    public List findWithNamedQuery(String namedQueryName, Map parameters) {
        return findWithNamedQuery(namedQueryName, parameters, 0);
    }

    public List findWithNamedQuery(String queryName, int resultLimit) {
        return this.em.createNamedQuery(queryName).setMaxResults(resultLimit).getResultList();
    }

    public List findByNativeQuery(String sql, Class type) {
        return this.em.createNativeQuery(sql, type).getResultList();
    }

    public List findWithNamedQuery(String namedQueryName, Map parameters, int resultLimit) {
        return findWithNamedQuery(namedQueryName, parameters, 0, resultLimit);
    }

    public List findWithNamedQuery(String namedQueryName, Map parameters, int firstResult, int maxResult) {
        Query query = this.em.createNamedQuery(namedQueryName);

        if (firstResult > 0) query.setFirstResult(firstResult);
        if (maxResult > 0) query.setMaxResults(maxResult);

        Set<Entry> rawParameters = parameters.entrySet();
        for (Entry entry : rawParameters) {
            query.setParameter((String) entry.getKey(), entry.getValue());
        }
        return query.getResultList();
    }

    public int executeWithNamedQuery(String namedQueryName) {
        return this.em.createNamedQuery(namedQueryName).executeUpdate();
    }

}
