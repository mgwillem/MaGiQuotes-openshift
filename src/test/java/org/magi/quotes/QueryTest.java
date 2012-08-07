package org.magi.quotes;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.internal.runners.JUnit38ClassRunner;

import java.math.BigDecimal;

/**
 * @author <a href="mailto:mgw@mmx.lu">Marc Gabriel-Willem</a>
 */
public class QueryTest {

    @Test
    public void testGetValueTypeProduct() {
        Query query = new Query("test", null, Product.CAT1_1);
        Assert.assertEquals(BigDecimal.class, query.getValueType());
    }
}
