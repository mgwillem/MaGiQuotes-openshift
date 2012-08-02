package org.magi.quotes;

import java.io.Serializable;

/**
 * @author <a href="mailto:mgw@mmx.lu">Marc Gabriel-Willem</a>
 */
public interface QueryElement extends Serializable {

    public String getId();
    public Product getProduct();

}
