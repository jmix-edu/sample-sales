package com.company.samplesales.view.main;

import com.company.samplesales.security.keycloak.SalesJmixOidcUser;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.app.main.StandardMainView;
import io.jmix.flowui.view.Install;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import org.springframework.security.core.userdetails.UserDetails;

@Route("")
@ViewController("MainView")
@ViewDescriptor("main-view.xml")
public class MainView extends StandardMainView {

    @Install(to = "userIndicator", subject = "formatter")
    private Object userIndicatorFormatter(final UserDetails value) {
        if (value instanceof SalesJmixOidcUser) {
            return ((SalesJmixOidcUser) value).getFormattedName();
        }
        return value.getUsername();
    }
}
