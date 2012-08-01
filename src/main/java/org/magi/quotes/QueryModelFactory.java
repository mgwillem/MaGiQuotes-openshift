package org.magi.quotes;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * @author <a href="mailto:mgw@mmx.lu">Marc Gabriel-Willem</a>
 */
@Named
@SessionScoped
public class QueryModelFactory implements Serializable {

    private QueryModel queryModel;

    @PostConstruct
    private QueryModel createQueryModel() {

        queryModel = new QueryModel();
        queryModel.init();

        Query qCateg1 = null;
        queryModel.add(qCateg1 = new Query("Q_CATEG_1", Product.CAT1, QueryType.MULTIPLE));
        queryModel.add(new Query("Q1_CATEG_1", qCateg1, Product.CAT1_Q1, QueryType.ONE));
        queryModel.add(new Query("Q2_CATEG_1", qCateg1, Product.CAT1_Q2, QueryType.ONE));
        queryModel.add(new Query("Q3_CATEG_1", qCateg1, Product.CAT1_Q3, QueryType.ONE));

        Query qCateg2 = null;
        queryModel.add(qCateg2 = new Query("Q_CATEG_2", Product.CAT2, QueryType.MULTIPLE));
        queryModel.add(new Query("Q1_CATEG_2", qCateg2, Product.CAT2_Q1, QueryType.ONE));
        queryModel.add(new Query("Q2_CATEG_2", qCateg2, Product.CAT2_Q2, QueryType.ONE));
        queryModel.add(new Query("Q3_CATEG_2", qCateg2, Product.CAT2_Q3, QueryType.ONE));
        queryModel.add(new Query("Q4_CATEG_2", qCateg2, Product.CAT2_Q4, QueryType.ONE));

        return queryModel;
    }

    public QueryModel getModel() {
        return queryModel;
    }
}
