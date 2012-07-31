package org.magi.quotes.presentation;

import org.magi.quotes.Query;
import org.magi.quotes.QueryModelFactory;

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
    private QueryModelFactory queryModelFactory;

    @Inject
    private QueryWizardComponentFactory queryWizardComponentFactory;

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

        createSummaryItems(queryModelFactory.getModel().getModel());

    }

    private void createSummaryItems(List<Query> queries) {
        for (Query query : queries) {

            if (query.getParent() == null) {
                summary.getChildren().add(queryWizardComponentFactory.createHtmlOutputText(query.getProduct().getDescription(), "lbl-cat-" + query.getId(), null));
                createSummaryItems(query.getQueries());
            }
            else {
                HtmlPanelGrid grid = queryWizardComponentFactory.createHtmlPanelGrid("lbl-cat-grid-" + query.getId(), 2, null);

                grid.getChildren().add(queryWizardComponentFactory.createHtmlOutputText(query.getProduct().getDescription() + " :", "lbl-sub-cat-" + query.getId(), "margin-left: 10px"));
                grid.getChildren().add(queryWizardComponentFactory.createHtmlOutputText("test", "lbl-sub-cat-val-" + query.getId(), "font-weight: bold"));

                summary.getChildren().add(grid);
            }
        }
    }
}
