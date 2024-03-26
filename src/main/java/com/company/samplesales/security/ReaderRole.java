package com.company.samplesales.security;

import com.company.samplesales.entity.Customer;
import com.company.samplesales.entity.Order;
import com.company.samplesales.entity.OrderLine;
import com.company.samplesales.entity.Product;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.securityflowui.role.annotation.MenuPolicy;
import io.jmix.securityflowui.role.annotation.ViewPolicy;

@ResourceRole(name = "Reader", code = ReaderRole.CODE, scope = "UI")
public interface ReaderRole extends UiMinimalRole {

    String CODE = "reader";

    @EntityAttributePolicy(entityClass = Customer.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Customer.class, actions = EntityPolicyAction.READ)
    void customer();

    @EntityAttributePolicy(entityClass = Order.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Order.class, actions = EntityPolicyAction.READ)
    void order();

    @EntityAttributePolicy(entityClass = OrderLine.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = OrderLine.class, actions = EntityPolicyAction.READ)
    void orderLine();

    @EntityAttributePolicy(entityClass = Product.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Product.class, actions = EntityPolicyAction.READ)
    void product();

    @MenuPolicy(menuIds = {"Customer.list", "Product.list", "Order_.list"})
    @ViewPolicy(viewIds = {"Customer.list", "Product.list", "Order_.list"})
    void screens();
}