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
public class Gaussian extends MatrixFilter{
    
    private int sigma;
    
    public  Gaussian(int s) {
        this.sigma = s;
    } 
    
    public int colorDelta(int color, int i, int j, int mid){
        return (int) (Math.exp( - (i*i + j*j) / ( 2 * sigma * sigma ) ) * color); 
    }
    
    public int colorRes(int color){
        return (int)(color / (Math.sqrt(2*Math.PI) * sigma)) % 256;
    }
    
}
