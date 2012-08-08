package org.magi.quotes.service.boundary;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.SessionContext;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 * @author <a href="mailto:mgw@mmx.lu">Marc Gabriel-Willem</a>
 */
public class AuditInterceptor {

    @Resource
    private EJBContext ejbContext;

    @AroundInvoke
    public Object interceptService(InvocationContext ctx) throws Exception {
        System.out.println(":::Intercepting:::" + ejbContext.getCallerPrincipal().getName());
        return ctx.proceed();
    }

}
