package org.example.advanced;

import lombok.Builder;
import lombok.Getter;

import java.awt.*;

@Getter
@Builder
public class Properties {
    @Builder.Default
    private String sourcePath = "src/main/resources/source.jpg";
    @Builder.Default
    private String resultPath = "src/main/resources/result.jpg";
    @Builder.Default
    private String text = "";
    @Builder.Default
    private int xPosition = 0;
    @Builder.Default
    private int yPosition = 0;
    @Builder.Default
    private int fontSize = 14;
    @Builder.Default
    private String fontFamily = Font.MONOSPACED;
    @Builder.Default
    private Color fontColor = Color.BLACK;
    @Builder.Default
    private RenderingHint renderingHint = RenderingHint.LCD_HRGB;

    private Properties() {
    }

    private Properties(String sourcePath,
                       String resultPath,
                       String text,
                       int xPosition,
                       int yPosition,
                       int fontSize,
                       String fontFamily,
                       Color fontColor,
                       RenderingHint renderingHint) {
        this.sourcePath = sourcePath;
        this.resultPath = resultPath;
        this.text = text;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.fontSize = fontSize;
        this.fontFamily = fontFamily;
        this.fontColor = fontColor;
        this.renderingHint = renderingHint;
    }
}
