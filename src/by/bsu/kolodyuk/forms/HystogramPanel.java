/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsu.kolodyuk.forms;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import by.bsu.kolodyuk.functions.Functions;

/**
 *
 * @author Pavel
 */
public class HystogramPanel extends javax.swing.JPanel {
    private int[] hystData;

    public int[] getHystData() {
        return hystData;
    }

    public void setHystData(int[] hystData) {
        this.hystData = hystData;
    }

    public HystogramPanel(int[] hData) {
        this.setSize(400, 600);
        this.hystData = hData;
        this.repaint();
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        
        int max = Functions.findMaxElement(this.getHystData());
        //System.out.println("Max is " + max);
        double scale = 300.0 / max;
        //System.out.println("scale is " + scale);

        for (int i = 0; i < this.getHystData().length; i++) {
            g2.drawRect(20 + 2 * i, 300 - (int)(this.getHystData()[i] * scale), 2, (int)(this.getHystData()[i] * scale));
            g2.setColor(Color.BLACK);
            g2.fillRect(20 + 2 * i, 300 - (int)(this.getHystData()[i] * scale), 2, (int)(this.getHystData()[i] * scale));
        }

    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
