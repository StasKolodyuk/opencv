
package by.bsu.kolodyuk.imagefunctions;

/**
 *
 * @author Natalya Klimenkova
 */
public class Line {
    
    private int startY;
    private int endY;
    private int startX;
    private int endX;

    public Line(int startX, int endX, int startY, int endY) {
        this.startY = startY;
        this.endY = endY;
        this.startX = startX;
        this.endX = endX;
    }

    public int getEndX() {
        return endX;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public int getEndY() {
        return endY;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }
    
    public void increaseEndY(int delta) {
        this.endY += delta;
    }
    
    public void decreaseStartY(int delta) {
        this.startY -= delta;
    }
    
    public void increaseEndX(int delta) {
        this.endX += delta;
    }
    
    public void decreaseStartX(int delta) {
        this.startX -= delta;
    }
    
    public void shiftX(int delta) {
        this.startX += delta;
        this.endX += delta;
    }

}
