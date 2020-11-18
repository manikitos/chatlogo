package org.example.advanced;

import java.awt.*;
import java.util.Arrays;

public class PropertiesReader {

    static Properties readProperties(String[] args) {
        Properties.PropertiesBuilder builder = Properties.builder();

        PropType prevType = null;
        for (String arg : args) {
            PropType curType = PropType.fromString(arg);
            if (curType != null) {
                prevType = curType;
                continue;
            }
            if (prevType == null) {
                continue;
            }
            switch (prevType) {
                case SPATH:
                    builder.sourcePath(arg);
                    break;
                case RPATH:
                    builder.resultPath(arg);
                    break;
                case TEXT:
                    builder.text(arg);
                    break;
                case XPOS:
                    try {
                        int xPosition = Integer.parseInt(arg);
                        builder.xPosition(xPosition);
                    } catch (NumberFormatException e) {
                        System.out.println("Incorrect xpos value: " + arg);
                    }
                    break;
                case YPOS:
                    try {
                        int yPosition = Integer.parseInt(arg);
                        builder.yPosition(yPosition);
                    } catch (NumberFormatException e) {
                        System.out.println("Incorrect ypos value: " + arg);
                    }
                    break;
                case SIZE:
                    try {
                        int size = Integer.parseInt(arg);
                        builder.fontSize(size);
                    } catch (NumberFormatException e) {
                        System.out.println("Incorrect font size value: " + arg);
                    }
                    break;
                case FONT:
                    String realFont = Arrays.stream(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames())
                            .filter(name -> name.equalsIgnoreCase(arg))
                            .findFirst()
                            .orElse(null);
                    if (realFont != null) {
                        builder.fontFamily(realFont);
                    } else {
                        System.out.println("Cannot find font family with name: " + arg);
                    }
                    break;
                case COLOR:
                    String[] rgb = arg.split(",");
                    if (rgb.length == 3) {
                        try {
                            int red = Integer.parseInt(rgb[0]);
                            int green = Integer.parseInt(rgb[1]);
                            int blue = Integer.parseInt(rgb[2]);
                            builder.fontColor(new Color(red, green, blue));
                        } catch (NumberFormatException e) {
                            System.out.println("Incorrect color declaration: " + arg);
                        }
                    } else {
                        System.out.println("Incorrect color declaration: " + arg);
                    }
                    break;
                case HINT:
                    builder.renderingHint(RenderingHint.valueOf(arg));
                    break;
            }
            prevType = null;
        }

        return builder.build();
    }

    private enum PropType {
        SPATH("-spath"),
        RPATH("-rpath"),
        TEXT("-text"),
        XPOS("-xpos"),
        YPOS("-ypos"),
        SIZE("-size"),
        FONT("-font"),
        COLOR("-color"),
        HINT("-hint");

        private final String argStr;

        PropType(String argStr) {
            this.argStr = argStr;
        }

        public static PropType fromString(String arg) {
            return Arrays.stream(values())
                    .filter(type -> type.argStr.equalsIgnoreCase(arg))
                    .findFirst()
                    .orElse(null);
        }
    }
}
