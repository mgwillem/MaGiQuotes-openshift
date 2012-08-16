package org.magi.quotes.presentation.admin;

import com.vaadin.terminal.WrappedRequest;
import com.vaadin.ui.*;
import org.magi.quotes.service.boundary.AuditService;
import org.magi.quotes.service.boundary.ProductService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

/**
 * @author <a href="mailto:mgw@mmx.lu">Marc Gabriel-Willem</a>
 */
@SessionScoped
public class MyApplicationRoot extends Root {

    @EJB
    private AuditService auditService;

    @EJB
    private ProductService productService;

    private Table table;

    @Override
    protected void init(WrappedRequest request) {
        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setSpacing(true);
        mainLayout.setMargin(true);
        mainLayout.addComponent(new Label("Quotes - Administration module"));
        mainLayout.addComponent(createTabSheet());
        setContent(mainLayout);
    }

    private TabSheet createTabSheet() {
        TabSheet tabsheet = new TabSheet();
        tabsheet.addTab(new AuditComponent(auditService).buildPanel()).setCaption("Audit viewer");
        tabsheet.addTab(new PriceComponent(productService).buildPanel()).setCaption("Price manager");
        return tabsheet;
    }
}
