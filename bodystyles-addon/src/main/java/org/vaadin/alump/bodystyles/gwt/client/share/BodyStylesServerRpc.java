package org.vaadin.alump.bodystyles.gwt.client.share;

import com.vaadin.shared.communication.ServerRpc;

import java.util.List;

/**
 * Server RPC interface of BodyStyles
 */
public interface BodyStylesServerRpc extends ServerRpc {

    void bodyClassNames(List<String> styleNames);
}
