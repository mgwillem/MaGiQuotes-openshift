package org.magi.quotes.service.boundary;

import org.magi.quotes.service.control.CrudService;
import org.magi.quotes.service.entity.Audit;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import java.util.List;

/**
 * @author <a href="mailto:mgw@mmx.lu">Marc Gabriel-Willem</a>
 */
@Stateless
public class AuditService {

    @Resource
    private EJBContext ejbContext;

    @EJB
    private CrudService<Audit> crudServiceAudit;

    public void create(String description) {
        crudServiceAudit.create(Audit.build(ejbContext.getCallerPrincipal().getName(), description));
    }

    public List<Audit> findAll() {
        return crudServiceAudit.findWithNamedQuery(Audit.findAll);
    }

}
