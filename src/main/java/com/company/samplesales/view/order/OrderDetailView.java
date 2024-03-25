package com.company.samplesales.view.order;

import com.company.samplesales.app.SpecialProductCount;
import com.company.samplesales.entity.Order;

import com.company.samplesales.entity.OrderLine;
import com.company.samplesales.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.model.CollectionChangeType;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.model.CollectionPropertyContainer;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

@Route(value = "orders/:id", layout = MainView.class)
@ViewController("Order_.detail")
@ViewDescriptor("order-detail-view.xml")
@EditedEntityContainer("orderDc")
public class OrderDetailView extends StandardDetailView<Order> {
    @Autowired
    private SpecialProductCount specialProductCount;
    @ViewComponent
    private CollectionPropertyContainer<OrderLine> linesDc;

    @Subscribe(id = "linesDc", target = Target.DATA_CONTAINER)
    public void onLinesDcCollectionChange(final CollectionContainer.CollectionChangeEvent<OrderLine> event) {
        if (event.getChangeType() != CollectionChangeType.REFRESH) {
            calculateAmount();
            calculateSpecialProductsNumber();
        }
    }

    private void calculateSpecialProductsNumber() {
        getEditedEntity().setNumberOfSpecialProducts(specialProductCount.getSpecialProductsNumber(getEditedEntity()));
    }

    protected void calculateAmount() {
        BigDecimal amount = BigDecimal.ZERO;

        for (OrderLine line :  linesDc.getItems()) {
            amount = amount.add(line.getProduct().getPrice().multiply(line.getQuantity()));
        }
        getEditedEntity().setAmount(amount);
    }
}