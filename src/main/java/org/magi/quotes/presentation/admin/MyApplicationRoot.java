package org.magi.quotes.presentation.admin;

import com.vaadin.terminal.WrappedRequest;
import com.vaadin.ui.*;
import org.magi.quotes.service.boundary.AuditService;
import org.magi.quotes.service.boundary.PriceProcessor;
import org.magi.quotes.service.entity.Audit;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import java.util.Date;

/**
 * @author <a href="mailto:mgw@mmx.lu">Marc Gabriel-Willem</a>
 */
@SessionScoped
public class MyApplicationRoot extends Root {

    @EJB
    private AuditService auditService;

    private Table table;

    @Override
    protected void init(WrappedRequest request) {
        setContent(createTabSheet());
    }

    private TabSheet createTabSheet() {
        TabSheet tabsheet = new TabSheet();
        tabsheet.addTab(createAuditTab()).setCaption("Audit viewer");
        tabsheet.addTab(new Label("test")).setCaption("Price manager");
        return tabsheet;
    }

    private Component createAuditTab() {
        Button refreshBtn = new Button("Refresh");
        refreshBtn.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                System.out.println("refresh button clicked!!!");
                addAuditItems();
            }
        });

        table = new Table("All audit items");

        table.addContainerProperty("ID", Long.class,  null);
        table.addContainerProperty("Principal", String.class,  null);
        table.addContainerProperty("Date/Time", Date.class, null);
        table.addContainerProperty("Description", String.class, null);

        addAuditItems();

        VerticalLayout auditTab = new VerticalLayout();
        auditTab.addComponent(refreshBtn);
        auditTab.addComponent(table);
        return auditTab;
    }

    private void addAuditItems() {
        int index = 0;
        for (Audit audit : auditService.findAll()) {
            table.addItem(new Object[]{audit.getId(), audit.getPrincipalName(), audit.getCreationDate(), audit.getDescription()}, Integer.valueOf(++index));
        }
    }
}
