package com.VaadinEmployees.common;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;

public class Utils {

    public static Button createButton(Runnable runnable, String text, ButtonVariant variant) {
        Button button = new Button();
        button.setText(text);
        button.addThemeVariants(variant);
        button.addClickListener(click -> runnable.run());
        return button;
    }

    public static Button createButton(Runnable runnable, String text, ButtonVariant variant, Key key) {
        Button button = createButton(runnable, text, variant);
        button.addClickShortcut(key);
        return button;
    }
}
