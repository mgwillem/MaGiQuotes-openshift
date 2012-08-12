package org.magi.quotes.service.boundary;

import org.magi.quotes.service.control.CrudService;
import org.magi.quotes.service.entity.Audit;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.math.BigDecimal;

/**
 * @author <a href="mailto:mgw@mmx.lu">Marc Gabriel-Willem</a>
 */
public class PriceProcessorInterceptor {

    @Resource
    private EJBContext ejbContext;

    @EJB
    private CrudService<Audit> crudServiceAudit;

    @AroundInvoke
    public Object interceptService(InvocationContext ctx) throws Exception {
        System.out.println(":::Intercepting-db:::" + ejbContext.getCallerPrincipal().getName());

        crudServiceAudit.create(Audit.build(ejbContext.getCallerPrincipal().getName(), "(PRICE) request..."));
        BigDecimal price = (BigDecimal)ctx.proceed();
        crudServiceAudit.create(Audit.build(ejbContext.getCallerPrincipal().getName(), "(PRICE) provided: " + price));

        return price;
    }
}
