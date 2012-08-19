package org.magi.quotes.service.boundary;

import org.magi.quotes.service.control.CrudService;
import org.magi.quotes.service.entity.Product;
import org.magi.quotes.service.entity.ProductPrice;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:mgw@mmx.lu">Marc Gabriel-Willem</a>
 */
@Stateless
public class ProductService {

    @EJB
    private CrudService<ProductPrice> crudProductPriceService;

    @Inject
    private AuditService auditService;

    public List<Product> findAll() {
        ArrayList<Product> products = new ArrayList<Product>();
        products.add(Product.CAT1_Q1_1);
        products.add(Product.CAT1_Q1_2);
        products.add(Product.CAT1_Q1_3);

        products.add(Product.CAT1_1_Q1);
        products.add(Product.CAT1_1_Q2);

        products.add(Product.CAT2_Q1);
        products.add(Product.CAT2_Q2);
        products.add(Product.CAT2_Q3);
        products.add(Product.CAT2_Q4);

        return products;
    }

    public void update(Product product, BigDecimal newPrice) {

        auditService.create("(NEW PRICE) " + product.name() + " " + newPrice);

        ProductPrice pp = new ProductPrice();
        pp.setProduct(product);
        pp.setDefaultPrice(newPrice);

        crudProductPriceService.update(pp);
    }

}
