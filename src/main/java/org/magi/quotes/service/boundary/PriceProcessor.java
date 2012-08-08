package org.magi.quotes.service.boundary;

import javax.ejb.Stateless;
import javax.interceptor.Interceptor;
import javax.interceptor.Interceptors;
import java.math.BigDecimal;

/**
 * @author <a href="mailto:mgw@mmx.lu">Marc Gabriel-Willem</a>
 */
@Stateless
@Interceptors({AuditInterceptor.class})
public class PriceProcessor {

    public BigDecimal process(QueryElementModel model) {
        System.out.println("process called!");
        return null;
    }

}
