/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsu.kolodyuk.imageprocessingfunctions;

/**
 *
 * @author Pavel
 */
public class Contour {
    public int x;
    public int y;
    public int value;

    public Contour(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    
    
    
}
