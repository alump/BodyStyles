package org.vaadin.alump.bodystyles.gwt.client.share;

import com.vaadin.shared.communication.ClientRpc;

/**
 * Created by alump on 12/03/14.
 */
public interface BodyStylesClientRpc extends ClientRpc {

    void addBodyClassName(String className);

    void removeBodyClassName(String className);

    void getBodyClassNames();
}
