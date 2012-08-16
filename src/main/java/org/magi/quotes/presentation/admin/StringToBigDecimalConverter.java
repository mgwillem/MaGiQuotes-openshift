package org.magi.quotes.presentation.admin;

import com.vaadin.data.util.converter.Converter;

import java.math.BigDecimal;
import java.util.Locale;

/**
 * @author <a href="mailto:mgw@mmx.lu">Marc Gabriel-Willem</a>
 */
public class StringToBigDecimalConverter implements Converter<String, BigDecimal> {
    @Override
    public BigDecimal convertToModel(String s, Locale locale) throws ConversionException {
        return new BigDecimal(s);
    }

    @Override
    public String convertToPresentation(BigDecimal bigDecimal, Locale locale) throws ConversionException {
        return bigDecimal.toPlainString();
    }

    @Override
    public Class<BigDecimal> getModelType() {
        return BigDecimal.class;
    }

    @Override
    public Class<String> getPresentationType() {
        return String.class;
    }
}
