package org.magi.quotes.service.entity;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.magi.quotes.Deployments;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

/**
 * @author <a href="mailto:mgw@mmx.lu">Marc Gabriel-Willem</a>
 */
@RunWith(Arquillian.class)
public class AuditIT {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    @Deployment
    public static Archive<?> createDeployment() {
        WebArchive archive = Deployments.createBusinessArchive();
        archive.addClass(Audit.class);

        return archive;
    }

    @Test
    public void shouldBeInjected() {
        Assert.assertNotNull(em);
        Assert.assertNotNull(utx);

        System.out.println(">>>>> Instances properly injected !");
    }

}
