package org.magi.quotes.presentation;

import org.apache.log4j.Logger;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * @author <a href="mailto:mgw@mmx.lu">Marc Gabriel-Willem</a>
 */
@Named(value="logoutBean")
@RequestScoped
public class LogoutBean {

    private static final Logger logger = Logger.getLogger(LogoutBean.class);

    public String logout()
    {
        logger.info("Logout called");

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) ec.getRequest();
        try
        {
            request.logout();
        }
        catch (ServletException ex)
        {
            logger.error("HttpServletRequest logout failed", ex);
        }

        request.getSession(false).invalidate();

        return "/logout-client.html";
    }

}
