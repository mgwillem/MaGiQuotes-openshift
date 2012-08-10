package org.magi.quotes.service.boundary;

import org.magi.quotes.service.control.CrudService;
import org.magi.quotes.service.entity.Audit;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 * @author <a href="mailto:mgw@mmx.lu">Marc Gabriel-Willem</a>
 */
public class AuditInterceptor {

    @Resource
    private EJBContext ejbContext;

    @EJB
    private CrudService<Audit> crudServiceAudit;

    @AroundInvoke
    public Object interceptService(InvocationContext ctx) throws Exception {
        System.out.println(":::Intercepting-db:::" + ejbContext.getCallerPrincipal().getName());

        crudServiceAudit.create(Audit.build(ejbContext.getCallerPrincipal().getName()));

        return ctx.proceed();
    }
}
