package org.magi.quotes.service.boundary;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.math.BigDecimal;

/**
 * @author <a href="mailto:mgw@mmx.lu">Marc Gabriel-Willem</a>
 */
public class PriceProcessorInterceptor {

    @Inject
    private AuditService auditService;

    @AroundInvoke
    public Object interceptService(InvocationContext ctx) throws Exception {
        System.out.println(":::Intercepting-db");

        auditService.create("(PRICE) request...");
        BigDecimal price = (BigDecimal)ctx.proceed();
        auditService.create("(PRICE) provided: " + price);

        return price;
    }
}
