package by.bsu.kolodyuk.imagefunctions;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Natalya Klimenkova
 */
public class Font {
    
    public static final String NAME = "Lucida Console";
    public static final int SIZE = 16;
    

    //http://habrahabr.ru/post/121964/
    public static final int SYMBOL_WIDTH = 16;
    public static final int X_HEIGHT = 14;
    public static final int CAP_HEIGHT = 17;
    public static final int BODY_HEIGHT = 26;
    public static final int ASCENT = 21;
    public static final int DESCENT = 5;
    public static final int COMMA_DESCENT = 4;
    public static final int NUMBER_HEIGHT = 20;
    public static final int QUOTE_HEIGHT = 25;
    public static final int TOPLINE = BODY_HEIGHT - CAP_HEIGHT - DESCENT;
    public static final int LEADING = 1;

    public static List<Line> normalizeLineHeight(Line line) {
        //////
        List<Line> duplicates = new ArrayList<Line>();
        int height = line.getEndY() - line.getStartY() + 1;
        if (height > BODY_HEIGHT) {
            throw new IllegalArgumentException("Incorrect height of simbols");
        }
        if (isBody(height)) {
            return duplicates;
        }
        if (isLowercase(height)) {
            line.decreaseStartY(ASCENT - X_HEIGHT);
            line.increaseEndY(DESCENT);
        } else if (isLowercaseWithDescend(height)) {
            line.decreaseStartY(ASCENT - X_HEIGHT);
        } else if (isLowercaseWithComma(height)) {
            line.decreaseStartY(ASCENT - X_HEIGHT);
            line.increaseEndY(DESCENT - COMMA_DESCENT);
        } else if (isUppercase(height)) {
            line.decreaseStartY(ASCENT - CAP_HEIGHT);
            line.increaseEndY(DESCENT);
        } else if (isUppercaseWithDescend(height)) {
            line.decreaseStartY(ASCENT - CAP_HEIGHT);
            line.increaseEndY(DESCENT);
        } else if (isAcsend(height)) {
            //O Q || O ,
            Line l = new Line(line.getStartX(), line.getEndX(), line.getStartY(), line.getEndY());
            l.decreaseStartY(TOPLINE);
            l.increaseEndY(ASCENT - NUMBER_HEIGHT);
            duplicates.add(l);
                        
            line.increaseEndY(DESCENT);
        } else if (isNumber(height)) {
            line.decreaseStartY(ASCENT - NUMBER_HEIGHT);
            line.increaseEndY(DESCENT);
        } else if (isNumberWithComma(height)) {
            //0123,;
            line.decreaseStartY(ASCENT - NUMBER_HEIGHT);
            line.increaseEndY(DESCENT - COMMA_DESCENT);
        } else if (isNumberWithDescend(height)) {
            
            //[]{}
            Line l = new Line(line.getStartX(), line.getEndX(), line.getStartY(), line.getEndY());
            l.increaseEndY(ASCENT - NUMBER_HEIGHT);
            duplicates.add(l);
            
            //123g
            line.decreaseStartY(ASCENT - NUMBER_HEIGHT);
        }
        return duplicates;
    }

    public static List<Line> normalizeLineWidth(Line line, int imageWidth) {

        List<Line> duplicates = new ArrayList<Line>();
        int width = line.getEndX() - line.getStartX() + 1;
        if (width % SYMBOL_WIDTH != 0) {
            int delta = (int) (Math.ceil((double) width / SYMBOL_WIDTH) * SYMBOL_WIDTH - width);
            line.increaseEndX(delta);
            for (int i = 1; i <= delta; ++i) {
                Line l = new Line(line.getStartX(), line.getEndX(), line.getStartY(), line.getEndY());
                l.decreaseStartX(i);
                l.increaseEndX(delta - i);
                if (l.getStartX() >= 0 && l.getEndX() <= imageWidth) {
                    duplicates.add(l);
                }
            }
        }
        return duplicates;

    }

    private static boolean isLowercase(int height) {
        return height == X_HEIGHT;
    }

    private static boolean isLowercaseWithDescend(int height) {
        return height == (X_HEIGHT + DESCENT);
    }
    
    private static boolean isLowercaseWithComma(int height) {
        return height == (X_HEIGHT + COMMA_DESCENT);
    }

    private static boolean isUppercase(int height) {
        return height == CAP_HEIGHT;
    }

    private static boolean isUppercaseWithDescend(int height) {
        return height == CAP_HEIGHT + DESCENT;
    }

    private static boolean isAcsend(int height) {
        return height == ASCENT;
    }

    private static boolean isNumber(int height) {
        return height == NUMBER_HEIGHT;
    }
    
    private static boolean isNumberWithDescend(int height) {
        return height == NUMBER_HEIGHT + DESCENT;
    }
    
    private static boolean isNumberWithComma(int height) {
        return height == NUMBER_HEIGHT + COMMA_DESCENT;
    }

    private static boolean isBody(int height) {
        return height == BODY_HEIGHT;
    }
}
