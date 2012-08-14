package org.magi.quotes.presentation.admin;

import com.vaadin.Application;
import com.vaadin.RootRequiresMoreInformationException;
import com.vaadin.terminal.WrappedRequest;
import com.vaadin.terminal.gwt.server.AbstractApplicationServlet;
import com.vaadin.terminal.gwt.server.WebApplicationContext;
import com.vaadin.ui.Root;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author <a href="mailto:mgw@mmx.lu">Marc Gabriel-Willem</a>
 */
@WebServlet(urlPatterns = "/admin/*")
public class VaadinAppServlet extends AbstractApplicationServlet {
    @Inject
    MyApplicationRoot root;

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

            @Override
            public void close() {
                System.out.println("application:::close");
                super.close();
                WebApplicationContext webCtx = (WebApplicationContext) getContext();
                HttpSession session = webCtx.getHttpSession();
                session.invalidate();
            }

        };
        root.setApplication(application);
        return application;
    }
}

