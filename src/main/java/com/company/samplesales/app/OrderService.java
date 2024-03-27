package com.company.samplesales.app;

import com.company.samplesales.entity.Order;
import io.jmix.core.DataManager;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("sales_OrderService")
public class OrderService {

    private final DataManager dataManager;

    public OrderService(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public Double calculatePrice() {
        return dataManager.loadValue("select sum(o.amount) from Order_ o ", Double.class)
                .one();
    }

    public OrderValidationResult validateOrder(Order order, Date validationDate) {
        OrderValidationResult result = new OrderValidationResult();
        result.setSuccess(false);
        result.setErrorMessage("Validation of order " + order.getNumberOfSpecialProducts()
                + " failed. validationDate parameter is: " + validationDate);
        return result;
    }
}