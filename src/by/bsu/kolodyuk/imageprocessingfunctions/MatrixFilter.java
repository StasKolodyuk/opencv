package by.bsu.kolodyuk.imageprocessingfunctions;

import java.awt.image.BufferedImage;
import java.util.Arrays;

import by.bsu.kolodyuk.imagefunctions.ExtendedImage;

public class MatrixFilter {
    
    protected int[][] mask;    
    protected int div;
    protected int offset;
    private static final int DEFAULT_MASK_SIZE = 3;
    private static final int DEFAULT_DIV = 1;
    private static final int DEFAULT_OFFSET = 0;
    
    public MatrixFilter() {
        this(DEFAULT_MASK_SIZE, DEFAULT_DIV, DEFAULT_OFFSET);
    }
    
    public MatrixFilter(int maskSize) {
        this(maskSize, DEFAULT_DIV, DEFAULT_OFFSET);
    }
    
    public MatrixFilter(int[][] mask, int div, int offset) {
        this.mask = mask;
        this.setDiv(div);
        this.setOffset(offset);
    }
    
    public MatrixFilter(int maskSize, int div, int offset) {
        if(maskSize < 0) {
            throw new IllegalArgumentException("maskSize < 0");
        }
        this.mask = new int[maskSize][maskSize];
        for(int i = 0; i< maskSize; ++i) {
            Arrays.fill(this.mask[i], 0);
        }
        this.setDiv(div);
        this.setOffset(offset);
    }
    

    
    public ExtendedImage applyToImage(ExtendedImage image, ExtendedImage resultImage) {
        //BufferedImage result = (BufferedImage) image.clone();
        int width = image.getWidth();
        int height = image.getHeight();
         for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                int newRGB =  (int)getCenterPixelValue(image, i, j, width, height);
                //result.setRGB(i, j, newRGB);               
                resultImage.setRGB(i, j, newRGB);            
            }
         }
         
        return resultImage;
        
    }
    
    public int getCenterPixelValue(BufferedImage image, int x, int y, int maxWidth, int maxHeight) {
        double summRed = 0;
        double summGreen = 0;
        double summBlue = 0;
        
        int half_length = mask.length / 2;
        
        int resAlpha = 0;
        int resRed = 0;
        int resGreen = 0;
        int resBlue = 0;
        
        for(int i = -half_length; i <= half_length; i++){
            for (int j = -half_length; j <= half_length; j++ ){
                int curX = x + i;
                int curY = y + j;
                if(curX < 0 || curY < 0 || curX >= maxWidth || curY >= maxHeight)
                    continue;
                
                int rgb = image.getRGB(curX, curY);
                
                
                int alpha = (rgb >> 24) & 0x000000FF;
                int red = (rgb >> 16) & 0x000000FF;
                int green = (rgb >> 8) & 0x000000FF;
                int blue = (rgb) & 0x000000FF;
        
                resAlpha = alpha;
                resRed += colorDelta(red, i, j, half_length);
                resGreen += colorDelta(green, i, j, half_length);
                resBlue += colorDelta(blue, i, j, half_length);
        
                //System.out.println("Red: " + red + "Green: " + green + "Blue: " + blue + "i "+ i + " j" + j + " " + mask[i+half_length][j+half_length]);
            }
        }
        
        resRed = colorRes(resRed); 
        resGreen = colorRes(resGreen);
        resBlue = colorRes(resBlue);
        
        int res = (resAlpha << 24) | (resRed << 16) | (resGreen << 8) | (resBlue);
        int rgb = image.getRGB(x, y);
        int alpha = (rgb >> 24) & 0x000000FF;
        int red = (rgb >> 16) & 0x000000FF;
        int green = (rgb >> 8) & 0x000000FF;
        int blue = (rgb) & 0x000000FF;
        //System.out.println("Old: " + rgb + "Alpha: " + alpha + "Red: " + red + "Green: " + green + "Blue: " + blue);
        //System.out.println("New: " + res + "Alpha: " + resAlpha + "Red: " + resRed + "Green: " + resGreen + "Blue: " + resBlue);

        return res;
    }

    public int colorDelta(int color, int i, int j, int mid){
        return color * mask[i+mid][j+mid];        
    }
    
    public int colorRes(int color){
        return (color / div) % 256;
    }

    public int[][] getMask() {
        return mask;
    }

    public void setMask(int[][] mask) {
        this.mask = mask;
    }

    public int getOffset() {
        return offset;
    }

    public final void setOffset(int offset) {
        this.offset = offset;
    }

    public int getDiv() {
        return div;
    }

    public final void setDiv(int div) {
        if(div == 0) {
            throw new IllegalArgumentException("div == 0");
        }
        this.div = div;
    }
    
}
