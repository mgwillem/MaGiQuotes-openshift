package org.magi.quotes.presentation;

import org.magi.quotes.Query;
import org.magi.quotes.QueryElementModelFactory;
import org.magi.quotes.presentation.util.WizardIntegerRange;

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
public class WizardStep2 implements Serializable {

    @Inject @WizardIntegerRange(range = WizardIntegerRange.Range.SMALL)
    private ArrayList<SelectItem> range;

    @Inject
    private QueryElementModelFactory queryElementModelFactory;

    private Query decoupeEvierNonFaconnee;
    private Query decoupeEvierFaconnee;
    private Query decoupePlaqueCuissonNonFaconnee;
    private Query decoupePlaqueCuissonFaconnee;

    @PostConstruct
    protected void init() {
        decoupeEvierNonFaconnee = (Query)queryElementModelFactory.getModel().getQueryElement("Q1_CATEG_2");
        decoupeEvierFaconnee = (Query)queryElementModelFactory.getModel().getQueryElement("Q2_CATEG_2");
        decoupePlaqueCuissonNonFaconnee = (Query)queryElementModelFactory.getModel().getQueryElement("Q3_CATEG_2");
        decoupePlaqueCuissonFaconnee = (Query)queryElementModelFactory.getModel().getQueryElement("Q4_CATEG_2");
    }

    public Query getDecoupeEvierNonFaconnee() {
        return decoupeEvierNonFaconnee;
    }

    public Query getDecoupeEvierFaconnee() {
        return decoupeEvierFaconnee;
    }

    public Query getDecoupePlaqueCuissonNonFaconnee() {
        return decoupePlaqueCuissonNonFaconnee;
    }

    public Query getDecoupePlaqueCuissonFaconnee() {
        return decoupePlaqueCuissonFaconnee;
    }

    public List<SelectItem> getRange() {
        return range;
    }

    public String nextPage() {
        return "wizard-step3";
    }
}
