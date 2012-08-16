package org.magi.quotes.service.boundary;

import org.magi.quotes.service.entity.Product;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:mgw@mmx.lu">Marc Gabriel-Willem</a>
 */
@Stateless
public class ProductService {

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

}
