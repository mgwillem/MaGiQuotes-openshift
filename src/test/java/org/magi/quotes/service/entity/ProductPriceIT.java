package org.magi.quotes.service.entity;

import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.magi.quotes.Deployments;
import org.magi.quotes.service.boundary.QueryElement;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author <a href="mailto:mgw@mmx.lu">Marc Gabriel-Willem</a>
 */
@RunWith(Arquillian.class)
public class ProductPriceIT {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private static ProductPrice[] SAMPLE_DATA = {
            new ProductPrice(Product.CAT1_1_Q1, BigDecimal.ONE),
            new ProductPrice(Product.CAT1_1_Q2, BigDecimal.TEN)
    };

    @Deployment
    public static Archive<?> createDeployment() {
        WebArchive archive = Deployments.createBusinessArchive();
        archive.addClass(QueryElement.class);
        archive.addPackage(ProductPrice.class.getPackage());

        return archive;
    }

    @Before
    public void preparePersistenceTest() throws Exception {
        clearData();
        insertData();
        startTransaction();
    }

    @After
    public void commitTransaction() throws Exception {
        utx.commit();
    }

    private void clearData() throws Exception {
        utx.begin();
        em.joinTransaction();
        System.out.println("Dumping old records...");
        em.createQuery("delete from ProductPrice ").executeUpdate();
        utx.commit();
    }

    private void insertData() throws Exception {
        utx.begin();
        em.joinTransaction();
        System.out.println("Inserting records...");
        for (ProductPrice productPrice : SAMPLE_DATA) {
            em.persist(productPrice);
        }
        utx.commit();
        // clear the persistence context (first-level cache)
        em.clear();
    }

    private void startTransaction() throws Exception {
        utx.begin();
        em.joinTransaction();
    }

    @Test
    public void shouldFindAllUsingJpqlQuery() throws Exception {
        String fetchingAllInJpql = "select p from ProductPrice p order by p.product";

        System.out.println("Selecting (using JPQL)...");
        List<ProductPrice> productPrices = em.createQuery(fetchingAllInJpql, ProductPrice.class).getResultList();

        System.out.println(">>>>> Found " + productPrices.size() + " product price (using JPQL)");
        Assert.assertEquals(SAMPLE_DATA.length, productPrices.size());
    }
}
