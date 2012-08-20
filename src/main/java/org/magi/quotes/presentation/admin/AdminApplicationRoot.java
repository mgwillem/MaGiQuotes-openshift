package org.magi.quotes.presentation.admin;

import com.vaadin.terminal.ExternalResource;
import com.vaadin.terminal.WrappedRequest;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.BaseTheme;
import org.magi.quotes.service.boundary.AuditService;
import org.magi.quotes.service.boundary.ProductService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

/**
 * @author <a href="mailto:mgw@mmx.lu">Marc Gabriel-Willem</a>
 */
@SessionScoped
public class AdminApplicationRoot extends Root {

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
        mainLayout.addComponent(createHeader());
        mainLayout.addComponent(createTabSheet());
        setContent(mainLayout);
    }

    private Component createHeader() {

        Button closeButton = new Button("(Logout)");
        closeButton.setStyleName(BaseTheme.BUTTON_LINK);
        closeButton.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                getApplication().close();
            }
        });

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setSpacing(true);
        horizontalLayout.addComponent(new Label("Quotes - Administration module"));
        horizontalLayout.addComponent(closeButton);

        return horizontalLayout;
    }

    private TabSheet createTabSheet() {
        TabSheet tabsheet = new TabSheet();
        tabsheet.addTab(new AuditComponent(auditService).buildPanel()).setCaption("Audit viewer");
        tabsheet.addTab(new PriceComponent(productService).buildPanel()).setCaption("Price manager");
        return tabsheet;
    }
}
