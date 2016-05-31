/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsu.kolodyuk.matrix_filters;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import by.bsu.kolodyuk.imageprocessingfunctions.MatrixFilter;

/**
 *
 * @author Pavel
 */
public class MedianFilter extends MatrixFilter
{
    
    public  MedianFilter(int mask_size) {
        super(mask_size, 1, 0);
    }
    
    @Override
    public int getCenterPixelValue(BufferedImage image, int x, int y, int maxWidth, int maxHeight) {
        
        int half_length = mask.length / 2;
        
        int[] red_arr = new int[mask.length*mask.length];
        int[] green_arr = new int[mask.length*mask.length];
        int[] blue_arr = new int[mask.length*mask.length];
        
        int resAlpha = 0;
        int resRed = 0;
        int resGreen = 0;
        int resBlue = 0;
        int k = 0;
        
        for(int i = -half_length; i <= half_length; i++){
            for (int j = -half_length; j <= half_length; j++ ){
                int curX = x + i;
                int curY = y + j;
                if(curX < 0 || curY < 0 || curX >= maxWidth || curY >= maxHeight)
                    continue;
                
                int rgb = image.getRGB(curX, curY);
                
                red_arr[k] = (rgb >> 16) & 0x000000FF;
                green_arr[k] = (rgb >> 8) & 0x000000FF;
                blue_arr[k] = (rgb) & 0x000000FF;
                k++;
            }
        }
        Arrays.sort(red_arr);
        Arrays.sort(green_arr);
        Arrays.sort(blue_arr);
        
        resRed = red_arr[red_arr.length/2]; 
        resGreen = green_arr[green_arr.length/2];
        resBlue = blue_arr[blue_arr.length/2];
        
        int res = (resAlpha << 24) | (resRed << 16) | (resGreen << 8) | (resBlue);
//        int rgb = image.getRGB(x, y);
//        int alpha = (rgb >> 24) & 0x000000FF;
//        int red = (rgb >> 16) & 0x000000FF;
//        int green = (rgb >> 8) & 0x000000FF;
//        int blue = (rgb) & 0x000000FF;
        //System.out.println("Old: " + rgb + "Alpha: " + alpha + "Red: " + red + "Green: " + green + "Blue: " + blue);
        //System.out.println("New: " + res + "Alpha: " + resAlpha + "Red: " + resRed + "Green: " + resGreen + "Blue: " + resBlue);

        return res;
    }
    
}
