package org.magi.quotes.service.boundary;

import org.magi.quotes.service.entity.Product;
import org.magi.quotes.service.entity.Query;
import org.magi.quotes.service.entity.QueryCategory;
import org.magi.quotes.service.entity.QueryCategoryType;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import java.io.Serializable;

/**
 * @author <a href="mailto:mgw@mmx.lu">Marc Gabriel-Willem</a>
 */
public class QueryElementModelFactory implements Serializable {

    private QueryElementModel queryElementModel;

    @PostConstruct
    private void createQueryModel() {

        queryElementModel = new QueryElementModel();
        queryElementModel.init();

        QueryCategory queryCategory1 = null;
        queryElementModel.add(queryCategory1 = new QueryCategory("CATEG1", Product.CAT1, QueryCategoryType.MULTIPLE_VALUE));
        queryElementModel.add(new Query("Q1_CATEG_1", queryCategory1, Product.CAT1_Q1));
        queryElementModel.add(new Query("Q2_CATEG_1", queryCategory1, Product.CAT1_Q2));
        queryElementModel.add(new Query("Q3_CATEG_1", queryCategory1, Product.CAT1_Q3));

        QueryCategory queryCategory1_1 = null;
        queryElementModel.add(queryCategory1_1 = new QueryCategory("CATEG1_1", Product.CAT1_1, QueryCategoryType.ONE_VALUE));
        queryElementModel.add(new Query("Q1_CATEG1_1", queryCategory1_1, Product.CAT1_1_Q1));
        queryElementModel.add(new Query("Q2_CATEG1_1", queryCategory1_1, Product.CAT1_1_Q2));

        QueryCategory queryCategory2 = null;
        queryElementModel.add(queryCategory2 = new QueryCategory("CATEG2", Product.CAT2, QueryCategoryType.MULTIPLE_VALUE));
        queryElementModel.add(new Query("Q1_CATEG_2", queryCategory2, Product.CAT2_Q1));
        queryElementModel.add(new Query("Q2_CATEG_2", queryCategory2, Product.CAT2_Q2));
        queryElementModel.add(new Query("Q3_CATEG_2", queryCategory2, Product.CAT2_Q3));
        queryElementModel.add(new Query("Q4_CATEG_2", queryCategory2, Product.CAT2_Q4));
    }

    @Produces
    @SessionScoped
    private QueryElementModel createModel() {
        return queryElementModel;
    }
}
