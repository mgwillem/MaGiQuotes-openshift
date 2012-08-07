package org.magi.quotes;

import javax.ejb.Stateless;
import java.math.BigDecimal;

/**
 * @author <a href="mailto:mgw@mmx.lu">Marc Gabriel-Willem</a>
 */
@Stateless
public class PriceProcessor {

    public BigDecimal process(QueryElementModel model) {
        System.out.println("process called!");
        return null;
    }

}
