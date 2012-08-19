package org.magi.quotes.presentation.admin;

import com.vaadin.Application;
import com.vaadin.RootRequiresMoreInformationException;
import com.vaadin.terminal.WrappedRequest;
import com.vaadin.terminal.gwt.server.AbstractApplicationServlet;
import com.vaadin.ui.Root;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * @author <a href="mailto:mgw@mmx.lu">Marc Gabriel-Willem</a>
 */
@WebServlet(urlPatterns = "/admin/*")
public class VaadinAppServlet extends AbstractApplicationServlet {
    @Inject
    AdminApplicationRoot root;

    @Override
    protected Class<? extends Application> getApplicationClass()
            throws ClassNotFoundException {
        return Application.class;
    }

    @Override
    protected Application getNewApplication(HttpServletRequest request)
            throws ServletException {
        final Application application = new Application() {
            protected Root getRoot(WrappedRequest request)
                    throws RootRequiresMoreInformationException {
                return root;
            }

            protected String getRootClassName(WrappedRequest request) {
                return root.getClass().getSimpleName();
            }
        };
        root.setApplication(application);
        return application;
    }
}

