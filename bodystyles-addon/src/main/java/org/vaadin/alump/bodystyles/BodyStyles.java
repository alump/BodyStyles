package org.vaadin.alump.bodystyles;

import com.vaadin.server.AbstractExtension;
import com.vaadin.server.Extension;
import com.vaadin.ui.UI;
import org.vaadin.alump.bodystyles.gwt.client.share.BodyStylesClientRpc;
import org.vaadin.alump.bodystyles.gwt.client.share.BodyStylesServerRpc;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Extensions allowing clean API to modify body element class names on client side. Useful when need to change style
 * of elements drawn outside the UI element (eq. windows).
 */
public class BodyStyles extends AbstractExtension {

    private final Set<BodyClassNamesCallback> callbacksWaiting = new HashSet<BodyClassNamesCallback>();

    private final BodyStylesServerRpc serverRpc = new BodyStylesServerRpc() {

        @Override
        public void bodyClassNames(List<String> styleNames) {
            for(BodyClassNamesCallback callback : callbacksWaiting) {
                callback.onBodyClassNamesReceived(styleNames);
            }
            callbacksWaiting.clear();
        }
    };

    /**
     * Async callback of getBodyClassNames
     */
    public interface BodyClassNamesCallback {

        /**
         * Called with set of class names of body element
         * @param classNames Class names of body element
         */
        void onBodyClassNamesReceived(List<String> classNames);
    }

    protected BodyStyles() {
        registerRpc(serverRpc, BodyStylesServerRpc.class);
    }

    /**
     * Get instance of BodyStyles
     * @return BodyStyles instance
     */
    protected static BodyStyles get() {
        UI ui = UI.getCurrent();
        if(ui == null) {
            throw new IllegalStateException("Could not resolve UI");
        }
        return get(UI.getCurrent());
    }

    /**
     * Get instance of BodyStyles. Alternative method if UI can not be resolved automatically.
     * @param ui UI extended
     * @return instance
     */
    protected static BodyStyles get(UI ui) {
        if(ui == null) {
            throw new NullPointerException("UI instance required, null given");
        }

        for(Extension extension : ui.getExtensions()) {
            if(extension instanceof BodyStyles) {
                return (BodyStyles)extension;
            }
        }
        BodyStyles beforeUnload = new BodyStyles();
        beforeUnload.extend(ui);
        return beforeUnload;
    }


    /**
     * Send request to provide body class names to client
     * @param callback callback added to response waiters
     */
    protected void resolveBodyClassNames(BodyClassNamesCallback callback) {
        callbacksWaiting.add(callback);
        if(callbacksWaiting.size() == 1) {
            this.getRpcProxy(BodyStylesClientRpc.class).getBodyClassNames();
        }
    }

    /**
     * Resolve current classnames of body element
     * @param callback Callback called when values have been read
     */
    public static void getBodyClassNames(BodyClassNamesCallback callback) {
        get().resolveBodyClassNames(callback);
    }

    /**
     * Resolve current classnames of body element. Alternative method if UI can not be resolved automatically.
     * @param callback Callback called when values have been read
     * @param ui UI extended
     */
    public static void getBodyClassNames(BodyClassNamesCallback callback, UI ui) {
        get(ui).resolveBodyClassNames(callback);
    }

    /**
     * Add class name to body element
     * @param className Class name added
     */
    public static void addBodyClassName(String className) {
        get().getRpcProxy(BodyStylesClientRpc.class).addBodyClassName(className);
    }

    /**
     * Add class name to body element. Alternative method if UI can not be resolved automatically.
     * @param className Class name added
     * @param ui UI extended
     */
    public static void addBodyClassName(String className, UI ui) {
        get(ui).getRpcProxy(BodyStylesClientRpc.class).addBodyClassName(className);
    }

    /**
     * Add class name from body element
     * @param className Class name removed
     */
    public static void removeBodyClassName(String className) {
        get().getRpcProxy(BodyStylesClientRpc.class).removeBodyClassName(className);
    }

    /**
     * Add class name from body element. Alternative method if UI can not be resolved automatically.
     * @param className Class name removed
     * @param ui UI extended
     */
    public static void removeBodyClassName(String className, UI ui) {
        get(ui).getRpcProxy(BodyStylesClientRpc.class).removeBodyClassName(className);
    }
}
