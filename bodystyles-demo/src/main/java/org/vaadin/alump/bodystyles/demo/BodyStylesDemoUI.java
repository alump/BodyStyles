package org.vaadin.alump.bodystyles.demo;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import org.vaadin.alump.bodystyles.BodyStyles;

import javax.servlet.annotation.WebServlet;
import java.util.Iterator;
import java.util.List;

/**
 * Demo application of BodyStyles
 */
@Title("BodyStyles Demo")
@Theme("demo")
public class BodyStylesDemoUI extends UI {

    private TextField classNamesTextField;

    @WebServlet(value = "/*")
    @VaadinServletConfiguration(productionMode = false, ui = BodyStylesDemoUI.class, widgetset = "org.vaadin.alump.bodystyles.demo.gwt.BodyStylesDemoWidgetSet")
    public static class BeforeUnloadDemoServlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        setContent(layout);

        // This label changes it style when foobar is defined
        Label guideLabel = new Label("This is simple demo of BodyStyles Vaadin extension.");
        guideLabel.addStyleName("guide-label");
        layout.addComponent(guideLabel);

        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setSpacing(true);
        layout.addComponent(buttonLayout);

        Button addFoobar = new Button("Add foobar", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                // Add foobar class name
                BodyStyles.addBodyClassName("foobar");
                // Ask update list of class names
                BodyStyles.getBodyClassNames(callback);
            }
        });
        buttonLayout.addComponent(addFoobar);

        Button removeFoobar = new Button("Remove foobar", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                // Remove foobar class name
                BodyStyles.removeBodyClassName("foobar");
                // Ask updated list of class names
                BodyStyles.getBodyClassNames(callback);
            }
        });
        buttonLayout.addComponent(removeFoobar);

        Button refresh = new Button("Refresh class names", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                // Ask updated list of class names
                BodyStyles.getBodyClassNames(callback);
            }
        });
        buttonLayout.addComponent(refresh);

        classNamesTextField = new TextField("Current class names of body");
        classNamesTextField.setWidth("100%");
        classNamesTextField.setReadOnly(true);
        layout.addComponent(classNamesTextField);

        // Call update on current set of classname
        BodyStyles.getBodyClassNames(callback);
    }

    private final BodyStyles.BodyClassNamesCallback callback = new BodyStyles.BodyClassNamesCallback() {

        @Override
        public void onBodyClassNamesReceived(List<String> classNames) {
            StringBuilder sb = new StringBuilder();
            Iterator<String> iter = classNames.iterator();
            while(iter.hasNext()) {
                sb.append(iter.next());
                if(iter.hasNext()) {
                    sb.append(", ");
                }
            }

            classNamesTextField.setReadOnly(false);
            classNamesTextField.setValue(sb.toString());
            classNamesTextField.setReadOnly(true);
        }
    };

}
