package org.magi.quotes.presentation.producer;

import org.magi.quotes.Product;
import org.magi.quotes.QueryModelFactory;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:mgw@mmx.lu">Marc Gabriel-Willem</a>
 */
public class SelectItemFactory {

    @Produces
    @WizardIntegerRange(range=WizardIntegerRange.Range.LARGE_NOT_ZERO)
    public ArrayList<SelectItem> createLargeNotZeroList() {

        ArrayList<SelectItem> range = new ArrayList<SelectItem>();

        for (int i = 1; i <= 10; i ++) {
            range.add(new SelectItem(i, i + " pc"));
        }

        return range;
    }

    @Produces
    @WizardProductName
    public ArrayList<SelectItem> createProductNameList() {

        ArrayList<SelectItem> productList = new ArrayList<SelectItem>();

        for (Product product : Product.CAT1_Q1.getProducts()) {
            productList.add(new SelectItem(product, product.getDescription()));
        }

        return productList;
    }
}
