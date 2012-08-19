package org.magi.quotes.service.control;

import org.magi.quotes.service.entity.Product;
import org.magi.quotes.service.entity.ProductPrice;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:mgw@mmx.lu">Marc Gabriel-Willem</a>
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class CustomProductConfigurationManager {

    @EJB
    private CrudService<ProductPrice> crudService;

    public Map<Product, BigDecimal> getConfiguration() {
        List<ProductPrice> productPriceList = crudService.findWithNamedQuery(ProductPrice.findAll);

        Map<Product, BigDecimal> configurationMap = new HashMap<Product, BigDecimal>();
        for (ProductPrice productPrice : productPriceList) {
            configurationMap.put(productPrice.getProduct(), productPrice.getDefaultPrice());
        }

        return configurationMap;
    }

}
