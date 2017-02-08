package ua.rozborsky;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.*;
import com.vaadin.server.Page;
import com.vaadin.ui.*;

import java.io.File;
import java.util.Locale;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        UI.getCurrent().setLocale(new Locale("ua"));
        Page.getCurrent().setTitle("tollRoad");
        VerticalLayout page = new VerticalLayout();
        page.setSizeFull();
        page.addComponent(content());
        page.setMargin(true);
        page.setSpacing(true);
        
        setContent(page);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }

    private Layout content() {
        VerticalLayout content = new VerticalLayout();
        content.setWidth("80%");
        content.addStyleName("red");
        final TextField name = new TextField();
        name.setCaption("Type your name here:");

        Button button = new Button("Click Me");
        button.addClickListener( e -> {
            content.addComponent(new Label("Thanks " + name.getValue()
                    + ", it works!"));
        });
        content.addComponents(image(), name, button);

        return content;
    }

    private Image image() {
        String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();

        FileResource resource = new FileResource(new File(basepath + "/WEB-INF/images/5.png"));
        Image image = new Image("Image from file", resource);
        image.setHeight("150px");
        return image;
    }
}
