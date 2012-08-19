package org.magi.quotes.service.boundary;

import org.apache.log4j.Logger;
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

    private static final Logger logger = Logger.getLogger(ProductConfiguration.class);

    @Inject
    private CustomProductConfigurationManager customProductConfigurationManager;

    @PostConstruct
    @Lock(WRITE)
    protected void mergeWithCustomConfigurationProvider() {

        logger.info(":::mergeWithCustomConfigurationProvider");
        Map<Product, BigDecimal> customConfigurationMap = customProductConfigurationManager.getConfiguration();
        for (Product product : customConfigurationMap.keySet()) {

            BigDecimal newPrice = customConfigurationMap.get(product);
            logger.info(":::mergeWithCustomConfigurationProvider::: " + product.name() + " old price: " + product.getPrice() + " new price: " + newPrice);

            product.setPrice(newPrice);
        }

    }
}
