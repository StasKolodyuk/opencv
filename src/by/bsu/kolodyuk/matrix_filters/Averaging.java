/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsu.kolodyuk.matrix_filters;
import by.bsu.kolodyuk.imageprocessingfunctions.MatrixFilter;

/**
 *
 * @author Pavel
 */
public class Averaging extends MatrixFilter
{
    
    public  Averaging(int maskSize) {
        super(maskSize, maskSize*maskSize, 0);
        this.mask = new int [maskSize][maskSize];
        for(int i = 0; i < maskSize; ++i) {
            for(int j = 0; j < maskSize; ++j) {
                this.mask[i][j] = 1;
            }
        }
    } 
    
}
