package org.example.simple;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class SimpleImageModifier {

    public static void main(String[] args) {
        try (InputStream sourceImage = new FileInputStream(new File("src/main/resources/source.jpg"))) {
            BufferedImage read = ImageIO.read(sourceImage);
            Graphics2D g = (Graphics2D) read.getGraphics();
            g.setFont(new Font("JetBrains Mono", Font.PLAIN, 80));
            g.setColor(new Color(242, 96, 117));
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
            g.drawString("16", 250, 220);
            g.dispose();
            ImageIO.write(read, "jpg", new File("src/main/resources/image.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
