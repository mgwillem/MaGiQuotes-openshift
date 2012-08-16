package org.magi.quotes.presentation.admin;

import com.vaadin.ui.*;
import org.magi.quotes.service.boundary.AuditService;
import org.magi.quotes.service.entity.Audit;

import java.util.Date;

/**
 * @author <a href="mailto:mgw@mmx.lu">Marc Gabriel-Willem</a>
 */
public class AuditComponent extends AbstractComponent {

    private AuditService auditService;
    private Table table;

    public AuditComponent(AuditService auditService) {
        this.auditService = auditService;

    }

    public Component buildPanel() {
        table = new Table("All audit items:");
        table.setWidth("750px");

        table.addContainerProperty("ID", Long.class,  null);
        table.addContainerProperty("Principal", String.class,  null);
        table.addContainerProperty("Date/Time", Date.class, null);
        table.addContainerProperty("Description", String.class, null);

        addAuditItems();

        Button refreshBtn = new Button("Refresh");
        refreshBtn.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                System.out.println("refresh button clicked!!!");
                addAuditItems();
            }
        });

        VerticalLayout auditTab = new VerticalLayout();
        auditTab.setSpacing(true);
        auditTab.setMargin(true);

        auditTab.addComponent(table);
        auditTab.addComponent(refreshBtn);
        return auditTab;
    }

    private void addAuditItems() {
        int index = 0;
        for (Audit audit : auditService.findAll()) {
            table.addItem(new Object[]{audit.getId(), audit.getPrincipalName(), audit.getCreationDate(), audit.getDescription()}, Integer.valueOf(++index));
        }
    }

}
