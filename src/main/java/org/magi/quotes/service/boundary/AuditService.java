package org.magi.quotes.service.boundary;

import org.magi.quotes.service.control.CrudService;
import org.magi.quotes.service.entity.Audit;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
        //return findDummies();
    }

    private List<Audit> findDummies() {
        ArrayList<Audit> audits = new ArrayList<Audit>();
        for (int i=0; i < 1000; i++) {
            Audit audit = new Audit() {
                public String getPrincipalName() {
                    return "principal-" + hashCode();
                }
                public String getDescription() {
                    return "description-" + hashCode();
                }
                public Timestamp getCreationDate() {
                    return new Timestamp(new Date().getTime());
                }
            };
            audits.add(audit);
        }

        return audits;
    }

}
