package org.magi.quotes.service.boundary;

import org.magi.quotes.service.control.CustomProductConfigurationManager;
import org.magi.quotes.service.entity.Product;

import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import java.math.BigDecimal;
import java.util.Map;

import static javax.ejb.LockType.READ;
import static javax.ejb.LockType.WRITE;

/**
 * @author <a href="mailto:mgw@mmx.lu">Marc Gabriel-Willem</a>
 */
@Startup
@Singleton
@Lock(READ)
public class ProductConfiguration {

    @Inject
    private CustomProductConfigurationManager customProductConfigurationManager;

    @PostConstruct
    @Lock(WRITE)
    protected void mergeWithCustomConfigurationProvider() {

        System.out.println(":::mergeWithCustomConfigurationProvider");
        Map<Product, BigDecimal> customConfigurationMap = customProductConfigurationManager.getConfiguration();
        for (Product product : customConfigurationMap.keySet()) {

            BigDecimal newPrice = customConfigurationMap.get(product);
            System.out.println(":::mergeWithCustomConfigurationProvider::: " + product.name() + " old price: " + product.getPrice() + " new price: " + newPrice);

            product.setPrice(newPrice);
        }

    }
}
