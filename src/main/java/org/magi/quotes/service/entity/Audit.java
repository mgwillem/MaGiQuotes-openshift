package org.magi.quotes.service.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author <a href="mailto:mgw@mmx.lu">Marc Gabriel-Willem</a>
 */
@Entity
public class Audit {

    @Id @GeneratedValue
    private long id;

    private String principalName;

    private Timestamp creationDate;

    public static Audit build(String principalName) {
        Audit audit = new Audit();
        audit.principalName = principalName;
        audit.creationDate = new Timestamp(new Date().getTime());
        return audit;
    }

    public long getId() {
        return id;
    }

    public String getPrincipalName() {
        return principalName;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }
}
