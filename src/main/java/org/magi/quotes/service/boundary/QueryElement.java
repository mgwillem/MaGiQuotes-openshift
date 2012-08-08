package org.magi.quotes.service.boundary;

import org.magi.quotes.service.entity.Product;

import java.io.Serializable;

/**
 * @author <a href="mailto:mgw@mmx.lu">Marc Gabriel-Willem</a>
 */
public interface QueryElement extends Serializable {

    public String getId();
    public Product getProduct();

}
