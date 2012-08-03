package org.magi.quotes.presentation;

import org.magi.quotes.*;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * @author <a href="mailto:mgw@mmx.lu">Marc Gabriel-Willem</a>
 */
@Named
@SessionScoped
public class WizardSummary implements Serializable {

    @Inject
    private QueryElementModelFactory queryElementModelFactory;

    @Inject
    private QueryWizardComponentFactory queryWizardComponentFactory;

    @EJB
    private PriceProcessor priceProcessor;

    private transient HtmlPanelGrid summary;

    public HtmlPanelGrid getSummary() {
        if (FacesContext.getCurrentInstance().getCurrentPhaseId() == PhaseId.RENDER_RESPONSE && summary != null) {
            loadSummary();
        }
        return summary;
    }

    public void setSummary(HtmlPanelGrid summary) {
        this.summary = summary;
        if (FacesContext.getCurrentInstance().getCurrentPhaseId() == PhaseId.RENDER_RESPONSE && summary != null) {
            loadSummary();
        }
    }

    private void loadSummary() {
        summary.getChildren().clear();
        summary.setColumns(1);

        createSummaryItems(queryElementModelFactory.getModel().getModel());
        priceProcessor.process(queryElementModelFactory.getModel());
    }

    private void createSummaryItems(List<QueryElement> queryElements) {
        for (QueryElement queryElement : queryElements) {

            if (queryElement instanceof QueryCategory) {
                summary.getChildren().add(queryWizardComponentFactory.createHtmlOutputText(queryElement.getProduct().getDescription(), "lbl-cat-" + queryElement.getId(), null));
                createSummaryItems(((QueryCategory)queryElement).getQueries());
            }
            else {
                HtmlPanelGrid grid = queryWizardComponentFactory.createHtmlPanelGrid("lbl-cat-grid-" + queryElement.getId(), 2, "summary-col1, summary-col2");

                grid.getChildren().add(queryWizardComponentFactory.createHtmlOutputText(queryElement.getProduct().getDescription() + " :", "lbl-sub-cat-" + queryElement.getId(), "margin-left: 10px"));
                grid.getChildren().add(queryWizardComponentFactory.createHtmlOutputText(getQueryValue((Query)queryElement), "lbl-sub-cat-val-" + queryElement.getId(), "font-weight: bold"));

                summary.getChildren().add(grid);
            }
        }
    }

    private Object getQueryValue(Query query) {
        if (query.getValueType() == Product.class) return query.getSelectedProduct().getDescription();
        else if (query.getValueType() == Integer.class) return query.getSelectedInteger();
        else return query.getSelectedDecimal();
    }
}
