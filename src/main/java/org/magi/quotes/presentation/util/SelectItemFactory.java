package org.magi.quotes.presentation.util;

import org.magi.quotes.Product;

import javax.enterprise.inject.Produces;
import javax.faces.model.SelectItem;
import java.util.ArrayList;

/**
 * @author <a href="mailto:mgw@mmx.lu">Marc Gabriel-Willem</a>
 */
public class SelectItemFactory {

    @Produces
    @WizardIntegerRange(range=WizardIntegerRange.Range.LARGE_NOT_ZERO)
    protected ArrayList<SelectItem> createLargeNotZeroList() {
        return createRange(1, 10);
    }

    @Produces
    @WizardIntegerRange(range=WizardIntegerRange.Range.LARGE)
    protected ArrayList<SelectItem> createLargeList() {
        return createRange(0, 10);
    }

    @Produces
    @WizardIntegerRange(range=WizardIntegerRange.Range.SMALL)
    protected ArrayList<SelectItem> createSmallList() {
        return createRange(0, 2);
    }

    private ArrayList<SelectItem> createRange(int start, int to) {
        ArrayList<SelectItem> range = new ArrayList<SelectItem>();

        for (int i = start; i <= to; i ++) {
            range.add(new SelectItem(i, i + " pc"));
        }

        return range;
    }

    @Produces
    @WizardProductName
    protected ArrayList<SelectItem> createProductNameList() {

        ArrayList<SelectItem> productList = new ArrayList<SelectItem>();

        for (Product product : Product.CAT1_Q1.getProducts()) {
            productList.add(new SelectItem(product, product.getDescription()));
        }

        return productList;
    }
}
