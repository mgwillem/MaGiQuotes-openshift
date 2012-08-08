package org.magi.quotes.presentation;

import org.magi.quotes.Query;
import org.magi.quotes.QueryElementModelFactory;
import org.magi.quotes.presentation.util.WizardIntegerRange;
import org.magi.quotes.presentation.util.WizardProductName;

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
    private QueryElementModelFactory queryElementModelFactory;

    private Query materialType;
    private Query numberOfPart;
    private Query numberOfM2;
    private Query chantPoliBiseaute;
    private Query chantPoliArrondiQuartLune;

    @PostConstruct
    protected void init() {
        materialType = (Query)queryElementModelFactory.getModel().getQueryElement("Q1_CATEG_1");
        numberOfPart = (Query)queryElementModelFactory.getModel().getQueryElement("Q2_CATEG_1");
        numberOfM2 = (Query)queryElementModelFactory.getModel().getQueryElement("Q3_CATEG_1");
        chantPoliBiseaute = (Query)queryElementModelFactory.getModel().getQueryElement("Q1_CATEG1_1");
        chantPoliArrondiQuartLune = (Query)queryElementModelFactory.getModel().getQueryElement("Q2_CATEG1_1");
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

    public Query getChantPoliBiseaute() {
        return chantPoliBiseaute;
    }

    public Query getChantPoliArrondiQuartLune() {
        return chantPoliArrondiQuartLune;
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
