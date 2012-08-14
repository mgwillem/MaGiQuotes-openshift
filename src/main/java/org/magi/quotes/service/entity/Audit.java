package org.magi.quotes.service.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author <a href="mailto:mgw@mmx.lu">Marc Gabriel-Willem</a>
 */
@Entity
@NamedQueries({
        @NamedQuery(name = Audit.findAll, query = "SELECT a FROM Audit a")
})
public class Audit {

    public static final String PREFIX = "org.magi.quotes.service.entity.Audit.";
    public static final String findAll = PREFIX + "findAll";


    @Id @GeneratedValue
    private long id;

    private String principalName;

    private Timestamp creationDate;

    private String description;

    public static Audit build(String principalName, String description) {
        Audit audit = new Audit();
        audit.principalName = principalName;
        audit.creationDate = new Timestamp(new Date().getTime());
        audit.description = description;
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

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Audit{" +
                "id=" + id +
                ", principalName='" + principalName + '\'' +
                ", creationDate=" + creationDate +
                ", description='" + description + '\'' +
                '}';
    }
}
