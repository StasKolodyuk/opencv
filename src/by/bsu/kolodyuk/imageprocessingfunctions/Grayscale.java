package by.bsu.kolodyuk.imageprocessingfunctions;

import java.awt.Color;

public class Grayscale {
    public static Color convertGrayscale(Color color){
        int rgb = (int) (((double) color.getRed() * 0.3) + 
                ((double) color.getGreen() * 0.59) + 
                ((double) color.getBlue() * 0.11));
        if (rgb > 255) {
            rgb = 255;
        }
        return new Color(rgb, rgb, rgb, color.getAlpha());
    }
    
}
