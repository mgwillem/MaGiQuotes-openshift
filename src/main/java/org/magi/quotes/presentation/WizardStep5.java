package org.magi.quotes.presentation;

import org.magi.quotes.Query;
import org.magi.quotes.QueryModelFactory;
import org.magi.quotes.presentation.producer.WizardIntegerRange;
import org.magi.quotes.presentation.producer.WizardProductName;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
