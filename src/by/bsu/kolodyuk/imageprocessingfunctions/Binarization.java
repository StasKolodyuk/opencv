package by.bsu.kolodyuk.imageprocessingfunctions;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.WritableRaster;

import by.bsu.kolodyuk.functions.Functions;

public class Binarization {  
    public static int[] smoothHystogram(int[] hData){
        boolean smoothed = false;
        int count = 0;
        while (!smoothed){
            for (int k = 1; k <hData.length - 1; k++ ){
                if (hData[k] > hData[k-1] && hData[k] > hData[k+1]){
                    count++;
                }
            }
            if (count > 2){
                hData = Functions.smoothHystogram(hData);
                count = 0;
            }
            else if (count <= 2){
                smoothed = true;
            }
        }
        return hData;
    }
    
    public static int findGlobalRift(int[] hData){
        int[] maxs = Functions.findLocalHystMaximums(hData);
        maxs = Functions.sortArray(maxs);
        int max1 = maxs[maxs.length-1];
        int max2 = maxs[maxs.length-2];
        int min = Functions.findLocalMinInHystogram(hData, max1, max2);
        return Functions.getElementIndex(hData, min);
    }
        
    public static WritableRaster makeBinarizadImage(WritableRaster raster, int rift, Dimension size){
        int[] data = new int[4];
        Color color = null;
        for (int x = 0; x < size.width; x++) {
            for (int y = 0; y < size.height; y++) {
                data = raster.getPixel(x, y, new int[4]);
                if (data[0] < rift) {
                    color = new Color(0, 0, 0, data[3]);
                } else if (data[0] >= rift) {
                    color = new Color(255, 255, 255, data[3]);
                }
                data = new int[]{color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()};
                raster.setPixel(x, y, data);
            }
        }
        return raster;
    }
    
    public static WritableRaster makeBinarizadImage(WritableRaster raster, int rift, Dimension size, Point p){
        int[] data = new int[4];
        Color color = null;
        for (int x = p.x; x < p.x + size.width; x++) {
            for (int y = p.y; y < p.y + size.height; y++) {
                data = raster.getPixel(x, y, new int[4]);
                if (data[0] < rift) {
                    color = new Color(0, 0, 0, data[3]);
                } else if (data[0] >= rift) {
                    color = new Color(255, 255, 255, data[3]);
                }
                data = new int[]{color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()};
                raster.setPixel(x, y, data);
            }
        }
        return raster;
    }
    
}
