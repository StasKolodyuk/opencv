package by.bsu.kolodyuk.imagefunctions;

import java.awt.Image;

/**
 *
 * @author Natalya Klimenkova
 */
public class Pattern implements Cloneable{
    
    private String symbol;
    private ExtendedImage image;
    private double matchPercentage;
    
    
    public Pattern(String symbol, ExtendedImage image) {
        this.symbol = symbol;
        this.image = image;
    }
    
    public Pattern(String symbol, ExtendedImage image, double matchPercentage) {
        this.symbol = symbol;
        this.image = image;
        this.matchPercentage = matchPercentage;
    }
    
    public Pattern(String symbol, Image image) {
        this.symbol = symbol;
        this.image = new ExtendedImage(image);
    }

    public ExtendedImage getImage() {
        return image;
    }

    public void setImage(ExtendedImage image) {
        this.image = image;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getMatchPercentage() {
        return matchPercentage;
    }

    public void setMatchPercentage(double matchPercentage) {
        this.matchPercentage = matchPercentage;
    }

    @Override
    public Pattern clone() {
         return new Pattern(this.getSymbol(), this.getImage(), this.getMatchPercentage());
    }
    
    
    
}
