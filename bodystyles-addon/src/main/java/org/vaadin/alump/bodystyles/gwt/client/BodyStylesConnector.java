package org.vaadin.alump.bodystyles.gwt.client;

import com.google.gwt.user.client.ui.RootPanel;
import com.vaadin.client.ServerConnector;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.shared.ui.Connect;
import org.vaadin.alump.bodystyles.gwt.client.share.BodyStylesClientRpc;
import org.vaadin.alump.bodystyles.gwt.client.share.BodyStylesServerRpc;

import java.util.*;

/**
 * Connector handling client side of body styling
 */
@Connect(org.vaadin.alump.bodystyles.BodyStyles.class)
public class BodyStylesConnector extends AbstractExtensionConnector {

    private final BodyStylesClientRpc clientRpc = new BodyStylesClientRpc() {
        @Override
        public void addBodyClassName(String className) {
            RootPanel.getBodyElement().addClassName(className);
        }

        @Override
        public void removeBodyClassName(String className) {
            RootPanel.getBodyElement().removeClassName(className);
        }

        @Override
        public void getBodyClassNames() {
            String classNames = RootPanel.getBodyElement().getClassName().trim();
            getRpcProxy(BodyStylesServerRpc.class).bodyClassNames(Arrays.asList(classNames.split("\\s+")));
        }
    };

    protected void init() {
        super.init();
        registerRpc(BodyStylesClientRpc.class, clientRpc);
    }

    @Override
    protected void extend(ServerConnector serverConnector) {
        // ignore
    }
}
