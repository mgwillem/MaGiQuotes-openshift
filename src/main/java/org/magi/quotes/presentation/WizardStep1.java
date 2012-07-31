package org.magi.quotes.presentation;

import org.magi.quotes.presentation.producer.WizardIntegerRange;
import org.magi.quotes.presentation.producer.WizardProductName;

import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * @author <a href="mailto:mgw@mmx.lu">Marc Gabriel-Willem</a>
 */
@Named
public class WizardStep1 {

    @Inject @WizardProductName
    private List<SelectItem> productNameList;

    @Inject @WizardIntegerRange(range = WizardIntegerRange.Range.LARGE_NOT_ZERO)
    private List<SelectItem> range;

    public List<SelectItem> getProductNameList() {
        return productNameList;
    }

    public List<SelectItem> getRange() {
        return range;
    }
}
