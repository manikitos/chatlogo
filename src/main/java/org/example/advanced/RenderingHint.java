package org.example.advanced;

import java.awt.*;
import java.util.Arrays;

public enum RenderingHint {
    ON(RenderingHints.VALUE_TEXT_ANTIALIAS_ON),
    GASP(RenderingHints.VALUE_TEXT_ANTIALIAS_GASP),
    LCD_HRGB(RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

    private final Object hint;

    RenderingHint(Object hint) {
        this.hint = hint;
    }

    Object getHint() {
        return this.hint;
    }

    public RenderingHint fromString(final String hintString) {
        return Arrays.stream(values())
                .filter(h -> h.name().equalsIgnoreCase(hintString))
                .findFirst()
                .orElse(RenderingHint.LCD_HRGB);
    }
}
