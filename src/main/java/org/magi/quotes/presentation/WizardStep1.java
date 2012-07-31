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
public class WizardStep1 implements Serializable {

    @Inject @WizardProductName
    private ArrayList<SelectItem> productNameList;

    @Inject @WizardIntegerRange(range = WizardIntegerRange.Range.LARGE_NOT_ZERO)
    private ArrayList<SelectItem> range;

    @Inject
    private QueryModelFactory queryModelFactory;

    private Query materialType;
    private Query numberOfPart;
    private Query numberOfM2;

    @PostConstruct
    protected void init() {
        materialType = queryModelFactory.getModel().getQuery("Q1_CATEG_1");
        numberOfPart = queryModelFactory.getModel().getQuery("Q2_CATEG_1");
        numberOfM2 = queryModelFactory.getModel().getQuery("Q3_CATEG_1");
    }

    public Query getMaterialType() {
        return materialType;
    }

    public Query getNumberOfM2() {
        return numberOfM2;
    }

    public Query getNumberOfPart() {
        return numberOfPart;
    }

    public List<SelectItem> getProductNameList() {
        return productNameList;
    }

    public List<SelectItem> getRange() {
        return range;
    }

    public String nextPage() {
        return "wizard-step2";
    }
}
