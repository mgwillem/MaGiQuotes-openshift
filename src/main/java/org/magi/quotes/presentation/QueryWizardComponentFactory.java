package org.magi.quotes.presentation;

import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import java.io.Serializable;

/**
 * @author <a href="mailto:mgw@mmx.lu">Marc Gabriel-Willem</a>
 */
public class QueryWizardComponentFactory implements Serializable {

    public HtmlOutputText createHtmlOutputText(Object name, String compId, String cssStyle) {
        HtmlOutputText label = new HtmlOutputText();
        label.setValue(name);
        label.setTransient(true);
        label.setId(compId);
        label.setStyle(cssStyle);

        return label;
    }

    public HtmlPanelGrid createHtmlPanelGrid(String compId, int columns, String cssColumnsClasses) {
        HtmlPanelGrid grid = new HtmlPanelGrid();
        grid.setColumns(columns);
        grid.setTransient(true);
        grid.setId(compId);
        grid.setColumnClasses(cssColumnsClasses);

        return grid;
    }

    public HtmlInputText createHtmlInputText(String value, String compId, int size) {
        HtmlInputText inputItem = new HtmlInputText();
        inputItem.setSize(size);
        inputItem.setTransient(true);
        inputItem.setValue(value);
        inputItem.setId(compId);

        return inputItem;
    }
}
