/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsu.kolodyuk.matrix_filters;
import by.bsu.kolodyuk.imageprocessingfunctions.MatrixFilter;

/**
 *
 * @author Pavel
 */
public class MotionBlur extends MatrixFilter
{
    
    public  MotionBlur(int maskSize) {
        super(maskSize, maskSize, 0);
        this.mask = new int [maskSize][maskSize];
        for(int i = 0; i < maskSize; ++i) {
            for(int j = 0; j < maskSize; ++j) {
                this.mask[i][j] = 0;
                if (i == j) this.mask[i][j] = 1;
            }
        }
    } 
    
}
