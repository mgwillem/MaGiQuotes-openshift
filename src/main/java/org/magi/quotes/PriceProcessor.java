package org.magi.quotes;

import javax.ejb.Stateless;

/**
 * @author <a href="mailto:mgw@mmx.lu">Marc Gabriel-Willem</a>
 */
@Stateless
public class PriceProcessor {

    public void process(QueryElementModel model) {
        System.out.println("process called!");
    }

}
