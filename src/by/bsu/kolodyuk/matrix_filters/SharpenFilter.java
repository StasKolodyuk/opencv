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
public class SharpenFilter extends MatrixFilter{
    
    public  SharpenFilter(int maskSize) {
        super(maskSize, 10, 0);
        this.mask = new int [maskSize][maskSize];
        int mid = maskSize/2;
        for(int i = 0; i < maskSize; ++i) {
            for(int j = 0; j < maskSize; ++j) {
                this.mask[i][j] = -1;
            }
        }
        for(int i = 0; i < maskSize; i++){
            this.mask[i][mid] = -2;
            this.mask[mid][i] = -2;
        }
        this.mask[mid][mid] = 22;
    } 
    
}
