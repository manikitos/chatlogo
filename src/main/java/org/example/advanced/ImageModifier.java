package org.example.advanced;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class ImageModifier {

    /**
     * @param args -text - text of the message
     *             -xpos - X position
     *             -ypos - Y position
     *             -size - font size
     *             -font - text font
     *             -spath - path to source file
     *             -rpath - result file
     *             -color - color in format R,G,B without spaces
     *             -hint - antialiasing method: default, GASP, LCD_HRGB(1)
     *             1 - is used by default
     */
    public static void main(String[] args) {

        Properties properties = PropertiesReader.readProperties(args);

        try (InputStream sourceImage = new FileInputStream(properties.getSourcePath())) {
            BufferedImage read = ImageIO.read(sourceImage);
            Graphics2D graphics2D = (Graphics2D) read.getGraphics();
            graphics2D.setFont(new Font(properties.getFontFamily(), Font.PLAIN, properties.getFontSize()));
            graphics2D.setColor(properties.getFontColor());
            graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                    properties.getRenderingHint().getHint());
            graphics2D.drawString(properties.getText(), properties.getXPosition(), properties.getYPosition());
            graphics2D.dispose();
            ImageIO.write(read, "jpg", new File(properties.getResultPath()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
