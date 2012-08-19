package org.magi.quotes.service.boundary;

import org.apache.log4j.Logger;
import org.magi.quotes.service.control.PriceProcessorInterceptor;
import org.magi.quotes.service.entity.Product;
import org.magi.quotes.service.entity.Query;
import org.magi.quotes.service.entity.QueryCategory;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author <a href="mailto:mgw@mmx.lu">Marc Gabriel-Willem</a>
 */
@Stateless
public class PriceProcessor {

    private static final Logger logger = Logger.getLogger(PriceProcessor.class);

    @Resource
    private EJBContext context;

    @RolesAllowed({"USER"})
    @Interceptors({PriceProcessorInterceptor.class})
    public BigDecimal process(QueryElementModel model) {
        logger.info(":::process called:::" + context.isCallerInRole("USER"));

        logger.info("selected product price: " + findQuery(model, "Q1_CATEG_1").getSelectedProduct().getPrice());
        logger.info("selected product m2: " + findQuery(model, "Q3_CATEG_1").getSelectedDecimal());
        BigDecimal price = findQuery(model, "Q1_CATEG_1").getSelectedProduct().getPrice().multiply(findQuery(model, "Q3_CATEG_1").getSelectedDecimal());

        logger.info(":::price:::" + price);

        return price;
    }

    private Query findQuery(QueryElementModel model, String id) {
        return (Query)model.getQueryElement(id);
    }

    private void process(List<QueryElement> queryElementList, BigDecimal price) {

        for (QueryElement queryElement : queryElementList) {
            if (queryElement instanceof QueryCategory) {

                QueryCategory category = (QueryCategory)queryElement;
                if (category.getProduct() == Product.CAT1) {

                }

                process(((QueryCategory) queryElement).getQueries(), price);
                return;
            }

            switch (((Query)queryElement).getProduct()) {
            }
        }
    }

}
