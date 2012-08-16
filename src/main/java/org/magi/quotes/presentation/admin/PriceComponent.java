package org.magi.quotes.presentation.admin;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.*;
import org.magi.quotes.service.boundary.ProductService;
import org.magi.quotes.service.entity.Product;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author <a href="mailto:mgw@mmx.lu">Marc Gabriel-Willem</a>
 */
public class PriceComponent extends AbstractComponent {

    private ProductService productService;

    private FieldGroup fieldGroup = new FieldGroup();
    private Table table;
    private GridLayout form;
    private HorizontalLayout formControls;

    public PriceComponent(ProductService productService) {
        this.productService = productService;
    }

    public Component buildPanel() {
        VerticalLayout layout = new VerticalLayout();
        layout.setSpacing(true);
        layout.setMargin(true);

        layout.addComponent(buildTable());
        layout.addComponent(buildForm());
        layout.addComponent(buildFormControls());

        return layout;
    }

    private void updateTableData() {
        List<Product> products = productService.findAll();

        BeanItemContainer<Product> container = new BeanItemContainer<Product>(Product.class, products);
        table.setContainerDataSource(container);

        table.setVisibleColumns(new String[] { "description", "price" });
        table.setColumnHeaders(new String[] { "Description", "Price" });

        table.sort(new Object[] { "description" }, new boolean[] { true });
    }

    private Component buildTable() {
        table = new Table("All products:");
        table.setWidth("500px");
        table.setSelectable(true);
        table.setImmediate(true);

        table.addListener(new Property.ValueChangeListener() {
            public void valueChange(Property.ValueChangeEvent event) {
                if (table.getValue() == null) return;

                BeanItem<Product> item = new BeanItem<Product>((Product)table.getValue());
                fieldGroup.setItemDataSource(item);
            }
        });
        updateTableData();

        return table;
    }

    private Component buildForm() {
        form = new GridLayout(1, 2);
        form.setSpacing(true);

        TextField description = new TextField("Description:");
        description.setWidth(400, Unit.PIXELS);

        TextField price = new TextField("Price:");
        price.setConverter(new StringToBigDecimalConverter());
        price.setConvertedValue(BigDecimal.TEN);

        fieldGroup.bind(description, "description");
        fieldGroup.bind(price, "price");

        form.addComponent(description);
        form.addComponent(price);

        return form;
    }

    private Component buildFormControls() {
        Button save = new Button("Save");
        Button discard = new Button("Discard");

        save.addListener(new Button.ClickListener() {
            @Override
            @SuppressWarnings("unchecked")
            public void buttonClick(Button.ClickEvent clickEvent) {
                try {
                    fieldGroup.commit();
                    System.out.println("save:" + ((BeanItem<Product>) fieldGroup.getItemDataSource()).getBean().getPrice());
                    updateTableData();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        discard.addListener(new Button.ClickListener() {
            @Override
            @SuppressWarnings("unchecked")
            public void buttonClick(Button.ClickEvent clickEvent) {
                try {
                    fieldGroup.discard();
                    System.out.println("discard:" + ((BeanItem<Product>) fieldGroup.getItemDataSource()).getBean().getPrice());
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        formControls = new HorizontalLayout();
        formControls.addComponent(save);
        formControls.addComponent(discard);

        return formControls;
    }
}
