package org.magi.quotes.presentation;

import org.apache.log4j.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author <a href="mailto:mgw@mmx.lu">Marc Gabriel-Willem</a>
 */
@WebListener
public class AuditSessionListener implements HttpSessionListener {

    private static final Logger logger = Logger.getLogger(AuditSessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        logger.info(":::sessionCreated:::" + httpSessionEvent.getSession().getServletContext());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        logger.info(":::sessionDestroyed");
    }
}
