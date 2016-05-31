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
public class Edge extends MatrixFilter
{
    
    public  Edge(int maskSize) {
        super(maskSize, 1, 0);
        this.mask = new int [maskSize][maskSize];
        int mid = maskSize / 2;
        for(int i = 0; i < maskSize; ++i) {
            for(int j = 0; j < maskSize; ++j) {
                mask[i][mid] = 1;
                mask[mid][i] = 1;
            }
        }
    } 
    
}
