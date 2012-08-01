package org.magi.quotes.presentation;

import org.magi.quotes.QueryModelFactory;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * @author <a href="mailto:mgw@mmx.lu">Marc Gabriel-Willem</a>
 */
@Named
@SessionScoped
public class WizardStep5 implements Serializable {

    @Inject
    private QueryModelFactory queryModelFactory;

    public String dump() {
        queryModelFactory.getModel().dump();

        return null;
    }

}
