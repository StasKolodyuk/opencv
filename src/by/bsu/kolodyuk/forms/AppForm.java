package by.bsu.kolodyuk.forms;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import by.bsu.kolodyuk.chooser.ImageFilter;
import by.bsu.kolodyuk.functions.Functions;
import by.bsu.kolodyuk.imagefunctions.ExtendedImage;
import by.bsu.kolodyuk.imagefunctions.Pattern;
import by.bsu.kolodyuk.imagefunctions.PatternGenerator;
import by.bsu.kolodyuk.matrix_filters.Averaging;
import by.bsu.kolodyuk.matrix_filters.Gaussian;
import by.bsu.kolodyuk.matrix_filters.SharpenFilter;
import by.bsu.kolodyuk.matrix_filters.MotionBlur;
import by.bsu.kolodyuk.functions.Hystogram;
import by.bsu.kolodyuk.imageprocessingfunctions.Binarization;
import by.bsu.kolodyuk.imageprocessingfunctions.Contour;
import by.bsu.kolodyuk.imageprocessingfunctions.Direction;
import by.bsu.kolodyuk.imageprocessingfunctions.MatrixFilter;
import by.bsu.kolodyuk.imageprocessingfunctions.Grayscale;
import by.bsu.kolodyuk.imagefunctions.ImageRecognizer;
import by.bsu.kolodyuk.matrix_filters.MedianFilter;

public class AppForm extends javax.swing.JFrame {

    private static AppForm mainForm;
    private JFileChooser fc;
    private BufferedImage image;
    private String imagename;
    private int maskSize = 3;

    public AppForm() {
        initComponents();
    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem22 = new javax.swing.JMenuItem();
        desktopPane = new javax.swing.JDesktopPane(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        cutMenuItem = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem20 = new javax.swing.JMenuItem();
        jMenuItem27 = new javax.swing.JMenuItem();
        jMenuItem26 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem25 = new javax.swing.JMenuItem();
        medianFilterMenuItem = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem21 = new javax.swing.JMenuItem();
        jMenuItem24 = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        contentMenuItem = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem23 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        jMenu3.setText("jMenu3");

        jMenuItem22.setText("jMenuItem22");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        fileMenu.setMnemonic('f');
        fileMenu.setText("File");

        openMenuItem.setMnemonic('o');
        openMenuItem.setText("Open");
        openMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(openMenuItem);

        saveAsMenuItem.setMnemonic('a');
        saveAsMenuItem.setText("Save As ...");
        saveAsMenuItem.setDisplayedMnemonicIndex(5);
        saveAsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveAsMenuItem);

        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        editMenu.setMnemonic('e');
        editMenu.setText("Convert Image");

        cutMenuItem.setMnemonic('t');
        cutMenuItem.setText("Grayscale");
        cutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grayscaleMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(cutMenuItem);

        jMenuItem3.setText("Hystogram");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showHystogramMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(jMenuItem3);

        jMenu5.setText("Filters");

        jMenuItem20.setText("Averaging Filter");
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                averagingFilterActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem20);

        jMenuItem27.setText("Motion Blur");
        jMenuItem27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                motionBlurActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem27);

        jMenuItem26.setText("Sharpen Filter");
        jMenuItem26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sharpenFilterMenuClicked(evt);
            }
        });
        jMenu5.add(jMenuItem26);

        jMenuItem14.setText("Gaussian blur");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gaussianMenuItemActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem14);

        jMenuItem25.setText("Negative");
        jMenuItem25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                negativeFilterMenuClicked(evt);
            }
        });
        jMenu5.add(jMenuItem25);

        medianFilterMenuItem.setText("Median Filter");
        medianFilterMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                medianFilterMenuItemActionPerformed(evt);
            }
        });
        jMenu5.add(medianFilterMenuItem);

        jMenu6.setText("Mask size");

        jMenuItem16.setText("3 x 3");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mask33MenuActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem16);

        jMenuItem17.setText("5 x 5");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mask55MenuItemActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem17);

        jMenuItem18.setText("7 x 7");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mask77MenuItemActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem18);

        jMenuItem19.setText("9 x 9");
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mask99MenuItemActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem19);

        jMenu5.add(jMenu6);

        editMenu.add(jMenu5);

        jMenu2.setText("Segmentation");

        jMenuItem8.setText("Simple");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpleSegmentationMenuItemActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem8);

        editMenu.add(jMenu2);

        jMenuItem21.setText("Emphasize contours");
        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emphasizeContourMenuActionPerformed(evt);
            }
        });
        editMenu.add(jMenuItem21);

        jMenuItem24.setText("Recognize text");
        jMenuItem24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textRecognizeMenuActionPerformed(evt);
            }
        });
        editMenu.add(jMenuItem24);

        menuBar.add(editMenu);

        helpMenu.setMnemonic('h');
        helpMenu.setText("Help");

        contentMenuItem.setMnemonic('c');
        contentMenuItem.setText("Contents");
        helpMenu.add(contentMenuItem);

        jMenu7.setText("TODO");

        jMenuItem5.setText("Invert");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                invertMenuItemActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem5);

        jMenu1.setText("Binarization");

        jMenuItem4.setText("Manual global rift");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manualGlobalRiftMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem6.setText("Auto global rift");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                autoGlobalRiftMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);

        jMenuItem7.setText("Auto local rift");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                autoLocalRiftMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem7);

        jMenu7.add(jMenu1);

        jMenu4.setText("Contour");

        jMenuItem11.setText("For binarized image");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contourForBinarizedMenuItemActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem11);

        jMenuItem12.setText("For grayscale image");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contourForGrayscaleMenuItemActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem12);

        jMenuItem13.setText("Zhuk algorithm for binarized");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contourZhukAlgoMenuItemActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem13);

        jMenu7.add(jMenu4);

        jMenuItem23.setText("Contrast Image(grayscale)");
        jMenuItem23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contrastMenuActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem23);

        jMenuItem9.setText("Smooth Hystogram 1");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smoothHyst1MenuItemActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem9);

        jMenuItem10.setText("Smooth Hystogram 2");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smoothHyst2MenuItemActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem10);

        helpMenu.add(jMenu7);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(desktopPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 213, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(desktopPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItemActionPerformed
        if (fc == null) {
            fc = new JFileChooser("D:\\Programming\\4 course\\ОЦИ\\ImageProcesser\\images\\");
            fc.addChoosableFileFilter(new ImageFilter());
            fc.setAcceptAllFileFilterUsed(false);
        }
        int returnVal = fc.showDialog(AppForm.this, "Open");

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File imagefile = fc.getSelectedFile();
            String path = imagefile.getAbsolutePath();
            System.out.println("Opening file: " + path);
            image = null;
            try {
                image = ImageIO.read(imagefile);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
            imagename = imagefile.getName();
            Functions.createInternalFrame(image, imagefile.getName());
        }
    }//GEN-LAST:event_openMenuItemActionPerformed

    private void saveAsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsMenuItemActionPerformed
        fc = new JFileChooser("D:\\Programming\\4 course\\ОЦИ\\ImageProcesser\\SavedImages\\");
        fc.addChoosableFileFilter(new ImageFilter());
        fc.setAcceptAllFileFilterUsed(false);

        int returnVal = fc.showSaveDialog(AppForm.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File outputfile = fc.getSelectedFile();
            try {
                ImageIO.write(Functions.getSelectedInternalFrame().getImage(), "jpg", outputfile);
            } catch (IOException ex) {
                Logger.getLogger(AppForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Saving: " + outputfile.getName() + ".");
        } else {
            System.out.println("Save command cancelled by user.");
        }
        System.out.println("Image saved!");
    }//GEN-LAST:event_saveAsMenuItemActionPerformed

    private void grayscaleMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grayscaleMenuItemActionPerformed
        WritableRaster raster = null;
        InternalAwesomeFrame selectedFrame = Functions.getSelectedInternalFrame();
        selectedFrame.setImageName("");
        BufferedImage b = Functions.copyImage(selectedFrame.getImage());
        raster = b.getRaster();
        int[] data = new int[4];
        Dimension size = new Dimension(selectedFrame.getDimension());
        for (int x = 0; x < size.width; x++) {
            for (int y = 0; y < size.height; y++) {
                data = raster.getPixel(x, y, new int[4]);
                Color color = new Color(data[0], data[1], data[2], data[3]);
                Color color2 = Grayscale.convertGrayscale(color);
                data = new int[]{color2.getRed(), color2.getGreen(), color2.getBlue(), color2.getAlpha()};
                raster.setPixel(x, y, data);
            }
        }
        Functions.createInternalFrame(b, imagename + "Grayscaled image result");
        //Functions.createHystogramInternalFrame(b, imagename + " Grayscale image hystogram");
    }//GEN-LAST:event_grayscaleMenuItemActionPerformed

    private void invertMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_invertMenuItemActionPerformed
        WritableRaster raster = null;
        //int index = Functions.findSelectedFrameIndex(interFramesList);
        InternalAwesomeFrame interFrame = Functions.getSelectedInternalFrame();
        BufferedImage b = Functions.copyImage(interFrame.getImage());
        raster = b.getRaster();
        int[] data = new int[4];
        Dimension size = new Dimension(interFrame.getDimension());
        for (int x = 0; x < size.width; x++) {
            for (int y = 0; y < size.height; y++) {
                data = raster.getPixel(x, y, new int[4]);
                Color color = new Color(255 - data[0], 255 - data[1], 255 - data[2], 255 - data[3]);
                data = new int[]{color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()};
                raster.setPixel(x, y, data);
            }
        }
        Functions.createInternalFrame(b, imagename + " Inverted image");
    }//GEN-LAST:event_invertMenuItemActionPerformed

    private void showHystogramMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showHystogramMenuItemActionPerformed
        BufferedImage b = Functions.copyImage(Functions.getSelectedInternalFrame().getImage());
        Functions.createHystogramInternalFrame(b, imagename + " Image Hystogram");
    }//GEN-LAST:event_showHystogramMenuItemActionPerformed

    private void manualGlobalRiftMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manualGlobalRiftMenuItemActionPerformed
        int rift = Integer.parseInt(Functions.newJDialog(mainForm,
                "Please, input the rift you prefer", "Manual global rift dialog"));
        //System.out.println("The rift is: " + rift);
        WritableRaster raster = null;
        InternalAwesomeFrame interFrame = Functions.getSelectedInternalFrame();
        BufferedImage b = Functions.copyImage(interFrame.getImage());
        raster = b.getRaster();
        int[] data = new int[4];
        Dimension size = new Dimension(interFrame.getDimension());
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
        Functions.createInternalFrame(b, imagename + " Manual rift choosing binarization result");

    }//GEN-LAST:event_manualGlobalRiftMenuItemActionPerformed

    private void autoGlobalRiftMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_autoGlobalRiftMenuItemActionPerformed
        WritableRaster raster = null;
        InternalAwesomeFrame interFrame = Functions.getSelectedInternalFrame();
        BufferedImage b = Functions.copyImage(interFrame.getImage());
        raster = b.getRaster();
        int[] hData = Functions.calcHystogramData(b);
        hData = Binarization.smoothHystogram(hData);
        Functions.createHystogramInternalFrame(hData, "Smoothed hystogram");
        int rift = Binarization.findGlobalRift(hData);
        Binarization.makeBinarizadImage(raster, rift, interFrame.getDimension());
        Functions.createInternalFrame(b, imagename + " Auto rift choosing binarization result");
    }//GEN-LAST:event_autoGlobalRiftMenuItemActionPerformed

    private void autoLocalRiftMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_autoLocalRiftMenuItemActionPerformed
        WritableRaster raster = null;
        InternalAwesomeFrame interFrame = Functions.getSelectedInternalFrame();
        BufferedImage b = Functions.copyImage(interFrame.getImage());
        raster = b.getRaster();
        interFrame.repaint();
        int w = interFrame.getSecond().x - interFrame.getFirst().x;
        int h = interFrame.getSecond().y - interFrame.getFirst().y;
        System.out.println("W" + w);
        System.out.println("H" + h);
        Dimension partDimension = new Dimension(w, h);
        Rectangle r = new Rectangle(interFrame.getFirst(), partDimension);

        BufferedImage part = b.getSubimage(interFrame.getFirst().x, interFrame.getFirst().y, w, h);
        int[] hData = Functions.calcHystogramData(part);
        hData = Binarization.smoothHystogram(hData);
        Functions.createHystogramInternalFrame(hData, imagename + " Local area smoothed hystogram");
        int rift = Binarization.findGlobalRift(hData);
        Binarization.makeBinarizadImage(raster, rift, partDimension, interFrame.getFirst());
        Functions.createInternalFrame(b, imagename + " Local area binarization result");
    }//GEN-LAST:event_autoLocalRiftMenuItemActionPerformed

    private void simpleSegmentationMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpleSegmentationMenuItemActionPerformed
        int numberOfSegments = Integer.parseInt(Functions.newJDialog(mainForm,
                "Please, input the number of segments", "Manual number of segments dialog"));
        double lengthOfSegment = 255.0 / numberOfSegments;
        int[] segments = new int[256];

        for (int i = 1; i <= numberOfSegments; i++) {
            int avg = (int) (i * lengthOfSegment - (i * lengthOfSegment - (i - 1) * lengthOfSegment) / 2 + 1);
            for (int j = (int) ((i - 1) * lengthOfSegment); j < i * lengthOfSegment; j++) {
                segments[j] = avg;
            }
        }

        WritableRaster raster = null;
        InternalAwesomeFrame interFrame = Functions.getSelectedInternalFrame();
        BufferedImage b = Functions.copyImage(interFrame.getImage());
        raster = b.getRaster();
        int[] data = new int[4];
        Dimension size = new Dimension(interFrame.getDimension());
        for (int x = 0; x < size.width; x++) {
            for (int y = 0; y < size.height; y++) {
                data = raster.getPixel(x, y, new int[4]);
                Color color = new Color(segments[data[1]], segments[data[1]], segments[data[1]], segments[data[1]]);
                data = new int[]{color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()};
                raster.setPixel(x, y, data);
            }
        }
        Functions.createInternalFrame(b, imagename + " " + numberOfSegments + " segments smooth result");

    }//GEN-LAST:event_simpleSegmentationMenuItemActionPerformed

    private void smoothHyst1MenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smoothHyst1MenuItemActionPerformed
        int numberOfSmooths = Integer.parseInt(Functions.newJDialog(mainForm,
                "Please, input the number of times to smooth hystogram",
                "Manual number of smooths dialog"));

        WritableRaster raster = null;
        InternalAwesomeFrame interFrame = Functions.getSelectedInternalFrame();
        BufferedImage b = Functions.copyImage(interFrame.getImage());
        raster = b.getRaster();
        int[] hData = Functions.calcHystogramData(b);
        for (int i = 1; i <= numberOfSmooths; i++) {
            Functions.smoothHystogram(hData);
        }
        Functions.createHystogramInternalFrame(hData, imagename + " Smoothed hystogram");

        int[] maxs = Functions.findLocalHystMaximums(hData);
        ArrayList<Integer> max = new ArrayList<Integer>();
        max.add(-1);

        for (int i = 0; i < maxs.length; i++) {
            if (maxs[i] != -1) {
                max.add(Functions.getElementIndex(hData, maxs[i]));
            }
        }
        max.add(255);

        int[] segm = new int[256];

        for (int i = 1; i < max.size(); i++) {
            int min = Functions.findLocalMinInHystogramByMaxInd(hData, max.get(i - 1) + 1, max.get(i));
            for (int j = max.get(i - 1) + 1; j <= max.get(i); j++) {
                segm[j] = Functions.getElementIndex(hData, min);
            }
        }
        raster = b.getRaster();
        int[] data = new int[4];
        Dimension size = new Dimension(interFrame.getDimension());
        for (int x = 0; x < size.width; x++) {
            for (int y = 0; y < size.height; y++) {
                data = raster.getPixel(x, y, new int[4]);
                Color color = new Color(segm[data[1]], segm[data[1]], segm[data[1]], segm[data[1]]);
                data = new int[]{color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()};
                raster.setPixel(x, y, data);
            }
        }
        Functions.createInternalFrame(b, imagename + " " + numberOfSmooths + " times smooth result");
    }//GEN-LAST:event_smoothHyst1MenuItemActionPerformed

    private void smoothHyst2MenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smoothHyst2MenuItemActionPerformed
        int numberOfSegments = Integer.parseInt(Functions.newJDialog(mainForm,
                "Please, input the number of segments",
                "Manual number of segments dialog"));
        WritableRaster raster = null;
        InternalAwesomeFrame interFrame = Functions.getSelectedInternalFrame();
        BufferedImage b = Functions.copyImage(interFrame.getImage());
        raster = b.getRaster();
        int[] hData = Functions.calcHystogramData(b);

        int[] maxs = Functions.findLocalHystMaximums(hData);
        int curNumOfMaxs = 0;
        boolean flag = false;
        //count num of maxs, if not numOfSegments-1 => smooth again
        while (!(flag)) {
            System.out.println(curNumOfMaxs);
            if (curNumOfMaxs == numberOfSegments - 1) {
                flag = true;
            } else {
                hData = Functions.smoothHystogram(hData);
                maxs = Functions.findLocalHystMaximums(hData);
            }
            int count = 0;
            for (int i = 0; i < maxs.length; i++) {
                if (maxs[i] != -1) {
                    count++;
                }
            }
            curNumOfMaxs = count;
        }
        Functions.createHystogramInternalFrame(hData, imagename + " Smoothed hystogram");

        maxs = Functions.findLocalHystMaximums(hData);
        ArrayList<Integer> max = new ArrayList<Integer>();
        max.add(-1);

        for (int i = 0; i < maxs.length; i++) {
            if (maxs[i] != -1) {
                max.add(Functions.getElementIndex(hData, maxs[i]));
            }
        }
        max.add(255);

        int[] segm = new int[256];

        for (int i = 1; i < max.size(); i++) {
            int min = Functions.findLocalMinInHystogramByMaxInd(hData, max.get(i - 1) + 1, max.get(i));
            for (int j = max.get(i - 1) + 1; j <= max.get(i); j++) {
                segm[j] = Functions.getElementIndex(hData, min);
            }
        }
        raster = b.getRaster();
        int[] data = new int[4];
        Dimension size = new Dimension(interFrame.getDimension());
        for (int x = 0; x < size.width; x++) {
            for (int y = 0; y < size.height; y++) {
                data = raster.getPixel(x, y, new int[4]);
                Color color = new Color(segm[data[1]], segm[data[1]], segm[data[1]], segm[data[1]]);
                data = new int[]{color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()};
                raster.setPixel(x, y, data);
            }
        }
        Functions.createInternalFrame(b, imagename + " Auto smooth for " + numberOfSegments + " segments");

    }//GEN-LAST:event_smoothHyst2MenuItemActionPerformed

    private void contourForBinarizedMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contourForBinarizedMenuItemActionPerformed
        WritableRaster raster = null;
        InternalAwesomeFrame selectedFrame = Functions.getSelectedInternalFrame();
        BufferedImage b = Functions.copyImage(selectedFrame.getImage());
        raster = b.getRaster();

        ArrayList<Contour> contour = new ArrayList<Contour>();
        int[] curData, prevData = new int[4];
        Dimension size = new Dimension(selectedFrame.getDimension());

        for (int x = 0; x < size.width; x++) {
            for (int y = 1; y < size.height; y++) {
                Contour c = new Contour(x, y, 255);
                contour.add(c);
            }
        }

        for (int x = 0; x < size.width; x++) {
            for (int y = 1; y < size.height; y++) {
                curData = raster.getPixel(x, y, new int[4]);
                prevData = raster.getPixel(x, y - 1, new int[4]);
                if (prevData[0] == 0 && curData[0] == 255) {
                    Contour c = new Contour(x, y - 1, 0);
                    contour.add(c);
                } else if (prevData[0] == 255 && curData[0] == 0) {
                    Contour c = new Contour(x, y, 0);
                    contour.add(c);
                }
            }
        }

        for (int x = 1; x < size.width; x++) {
            for (int y = 0; y < size.height; y++) {
                curData = raster.getPixel(x, y, new int[4]);
                prevData = raster.getPixel(x - 1, y, new int[4]);
                if (prevData[0] == 0 && curData[0] == 255) {
                    Contour c = new Contour(x - 1, y, 0);
                    contour.add(c);
                } else if (prevData[0] == 255 && curData[0] == 0) {
                    Contour c = new Contour(x, y, 0);
                    contour.add(c);
                }
            }
        }
        for (Contour c : contour) {
            int[] data = new int[]{c.getValue(), c.getValue(), c.getValue()};
            raster.setPixel(c.getX(), c.getY(), data);
        }
        Functions.createInternalFrame(b, imagename + " Contour for binarized image");
    }//GEN-LAST:event_contourForBinarizedMenuItemActionPerformed

    private void contourForGrayscaleMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contourForGrayscaleMenuItemActionPerformed
        int pixelDiapason = Integer.parseInt(Functions.newJDialog(mainForm,
                "Please, input pixel diapason", "Manual pixel diapason dialog"));
        int brightnessDifference = Integer.parseInt(Functions.newJDialog(mainForm,
                "Please, input the brightness difference for diapason", "Manual brightness difference dialog"));

        WritableRaster raster = null;
        InternalAwesomeFrame selectedFrame = Functions.getSelectedInternalFrame();
        BufferedImage b = Functions.copyImage(selectedFrame.getImage());
        raster = b.getRaster();

        ArrayList<Contour> contour = new ArrayList<Contour>();
        //int[] curData, prevData = new int[4];
        int[] data = new int[4];
        Dimension size = new Dimension(selectedFrame.getDimension());

        for (int x = 0; x < size.width; x++) {
            for (int y = 1; y < size.height; y++) {
                Contour c = new Contour(x, y, 255);
                contour.add(c);
            }
        }

        int[][] imBrightnesses = new int[size.width + 1][size.height + 1];
        for (int x = 0; x < size.width; x++) {
            for (int y = 0; y < size.height; y++) {
                data = raster.getPixel(x, y, new int[4]);
                imBrightnesses[x][y] = data[0];
            }
        }

        for (int x = 0; x < size.width; x++) {
            for (int y = 0; y < size.height - pixelDiapason; y++) {
                int min = Functions.findRowLocalMinElement(imBrightnesses, y, y + pixelDiapason, x);
                int max = Functions.findRowLocalMaxElement(imBrightnesses, y, y + pixelDiapason, x);
                if ((max - min) > brightnessDifference) {
                    Contour c = new Contour(x, Functions.getElementIndexOnRowDiapason(imBrightnesses, max, y + pixelDiapason, max, x), 0);
                    contour.add(c);
                }
            }
        }

        for (int x = 0; x < size.width - pixelDiapason; x++) {
            for (int y = 0; y < size.height; y++) {
                int min = Functions.findColumnLocalMinElement(imBrightnesses, x, x + pixelDiapason, y);
                int max = Functions.findColumnLocalMaxElement(imBrightnesses, x, x + pixelDiapason, y);
                if ((max - min) > brightnessDifference) {
                    Contour c = new Contour(Functions.getElementIndexOnColumnDiapason(imBrightnesses, max, x, x + pixelDiapason, y), y, 0);
                    contour.add(c);
                }
            }
        }

        for (Contour c : contour) {
            int[] d = new int[]{c.getValue(), c.getValue(), c.getValue()};
            raster.setPixel(c.getX(), c.getY(), d);
        }
        Functions.createInternalFrame(b, imagename + " Contour for grayscale image");
    }//GEN-LAST:event_contourForGrayscaleMenuItemActionPerformed

    private void contourZhukAlgoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contourZhukAlgoMenuItemActionPerformed
        WritableRaster raster = null;
        InternalAwesomeFrame selectedFrame = Functions.getSelectedInternalFrame();
        BufferedImage b = Functions.copyImage(selectedFrame.getImage());
        raster = b.getRaster();

        ArrayList<Contour> contour = new ArrayList<Contour>();
        int[] data = new int[4];
        int[] data2 = new int[4];
        Dimension size = new Dimension(selectedFrame.getDimension());

        for (int x = 0; x < size.width; x++) {
            for (int y = 0; y < size.height; y++) {
                Contour c = new Contour(x, y, 255);
                contour.add(c);
            }
        }

        Direction direction = Direction.North;
        int currentX = 0;
        int currentY = 0;
        for (int i_ = 0; i_ < size.height; i_++) {
            for (int j_ = 0; j_ < size.width; j_++) {
                data = raster.getPixel(j_, i_, new int[4]);
                if ((int) (data[0]) == 0) {
                    currentX = j_;
                    currentY = i_;
                    break;
                }
            }
            if (currentY != 0 || currentX != 0) {
                break;
            }
        }

        int j = currentX;
        int i = currentY;
        data = raster.getPixel(j, i, new int[4]);
        //founding first black pixel
        if (data[0] == 0) {
            if ((j != 0) && (i != 0)) {
                currentX = j;//end X coordinate
                currentY = i - 1;//end Y coordinate
            }

            direction = Direction.North;
            while ((currentX != j) || (currentY != i)) {
                data = raster.getPixel(currentX, currentY, new int[4]);
                switch (direction) {
                    case North: {
                        if (data[0] == 0) {
                            direction = Direction.West;
                            currentX--;
                            Contour c = new Contour(currentX, currentY, 0);
                            contour.add(c);
                        } else {
                            direction = Direction.East;
                            currentX++;
                        }
                        break;
                    }
                    case East: {
                        if (data[0] == 0) {
                            direction = Direction.North;
                            currentY--;
                            Contour c = new Contour(currentX, currentY, 0);
                            contour.add(c);
                        } else {
                            direction = Direction.South;
                            currentY++;
                        }
                        break;
                    }
                    case South: {
                        if (data[0] == 0) {
                            direction = Direction.East;
                            currentX++;
                            Contour c = new Contour(currentX, currentY, 0);
                            contour.add(c);
                        } else {
                            direction = Direction.West;
                            currentX--;
                        }
                        break;
                    }
                    case West: {
                        if (data[0] == 0) {
                            direction = Direction.South;
                            currentY++;
                            Contour c = new Contour(currentX, currentY, 0);
                            contour.add(c);
                        } else {
                            direction = Direction.North;
                            currentY--;
                        }
                        break;
                    }
                }
            }
        }


        for (Contour c : contour) {
            int[] d = new int[]{c.getValue(), c.getValue(), c.getValue()};
            raster.setPixel(c.getX(), c.getY(), d);
        }
        Functions.createInternalFrame(b, imagename + " Zhuk algorythm result image");
    }//GEN-LAST:event_contourZhukAlgoMenuItemActionPerformed

    private void gaussianMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gaussianMenuItemActionPerformed
        WritableRaster raster = null;
        InternalAwesomeFrame selectedFrame = Functions.getSelectedInternalFrame();
        BufferedImage b = Functions.copyImage(selectedFrame.getImage());        
        Dimension size = new Dimension(selectedFrame.getDimension()); 
        
        ExtendedImage image = new ExtendedImage((Image)selectedFrame.getImage());
        ExtendedImage resultImage = (ExtendedImage) image.clone();
       
        Gaussian filter = new Gaussian(5);
        
        if(filter != null) {
            resultImage = filter.applyToImage(image, resultImage);
        }
        
        Functions.createInternalFrame(resultImage, imagename + " Gaussian filter result image");
    }//GEN-LAST:event_gaussianMenuItemActionPerformed

    private void mask55MenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mask55MenuItemActionPerformed
        this.setMaskSize(5);
    }//GEN-LAST:event_mask55MenuItemActionPerformed

    private void mask77MenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mask77MenuItemActionPerformed
        this.setMaskSize(7);
    }//GEN-LAST:event_mask77MenuItemActionPerformed

    private void mask99MenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mask99MenuItemActionPerformed
        this.setMaskSize(9);
    }//GEN-LAST:event_mask99MenuItemActionPerformed

    private void averagingFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_averagingFilterActionPerformed
        WritableRaster raster = null;
        InternalAwesomeFrame selectedFrame = Functions.getSelectedInternalFrame();
        BufferedImage b = Functions.copyImage(selectedFrame.getImage());        
        Dimension size = new Dimension(selectedFrame.getDimension()); 
        
        ExtendedImage image = new ExtendedImage((Image)selectedFrame.getImage());
        ExtendedImage resultImage = (ExtendedImage) image.clone();
       
        MatrixFilter filter = new Averaging(this.getMaskSize());
        
        if(filter != null) {
            resultImage = filter.applyToImage(image, resultImage);
        }
        
        Functions.createInternalFrame(resultImage, imagename + " Averaging filter result image");
    }//GEN-LAST:event_averagingFilterActionPerformed

    private void mask33MenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mask33MenuActionPerformed
        this.setMaskSize(3);
    }//GEN-LAST:event_mask33MenuActionPerformed

    private void emphasizeContourMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emphasizeContourMenuActionPerformed
        int pixelDiapason = Integer.parseInt(Functions.newJDialog(mainForm,
                "Please, input pixel diapason", "Manual pixel diapason dialog"));
        int brightnessDifference = Integer.parseInt(Functions.newJDialog(mainForm,
                "Please, input the brightness difference for diapason", "Manual brightness difference dialog"));
        WritableRaster raster = null;
        InternalAwesomeFrame selectedFrame = Functions.getSelectedInternalFrame();
        BufferedImage b = Functions.copyImage(selectedFrame.getImage());        
        Dimension size = new Dimension(selectedFrame.getDimension()); 
        
        Set<Point2D> contour = Functions.extractContourFromGrayscale(b, pixelDiapason, brightnessDifference);
        
        Functions.emphasizeContour(b, contour);
        
        Functions.createInternalFrame(b, imagename + " Emphasized contours result image");
    }//GEN-LAST:event_emphasizeContourMenuActionPerformed

    private void contrastMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contrastMenuActionPerformed
        WritableRaster raster = null;
        InternalAwesomeFrame selectedFrame = Functions.getSelectedInternalFrame();
        BufferedImage b = Functions.copyImage(selectedFrame.getImage());        
        Dimension size = new Dimension(selectedFrame.getDimension()); 
        
        int width = image.getWidth();
        int height = image.getHeight();
        Hystogram histogram = new Hystogram(b);
        int minX = histogram.getIndexOfMinValue();
        int maxX = histogram.getIndexOfMaxValue();
        for(int i = 0; i < width; ++i) {
            for(int j = 0; j < height; ++j) {
                int rgb = image.getRGB(i, j);
                int alpha = (rgb >> 24) & 0x000000FF;
                int oldGrey = (rgb >> 16) & 0x000000FF;
                
                int newGrey = (int)(255 * ((double)(oldGrey - minX))/ (double)(maxX - minX));
                
                int newRGB = (alpha << 24) | (newGrey << 16) | (newGrey << 8) | newGrey;
                b.setRGB(i, j, newRGB);
            }
        }
        
        Functions.createInternalFrame(b, imagename + " Contrast result image");
    }//GEN-LAST:event_contrastMenuActionPerformed

    private void textRecognizeMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textRecognizeMenuActionPerformed
        WritableRaster raster = null;
        InternalAwesomeFrame selectedFrame = Functions.getSelectedInternalFrame();
        //BufferedImage b = Functions.copyImage(selectedFrame.getImage());
        ExtendedImage image = new ExtendedImage((Image)selectedFrame.getImage());
        Dimension size = new Dimension(selectedFrame.getDimension()); 
        Set<Pattern> patterns = PatternGenerator.readFrom(new ImageIcon("images/symbols.png").getImage());
        ImageRecognizer recognizer = new ImageRecognizer(patterns);
        String result = recognizer.applyTo(image); 
        JPanel panel = new JPanel(new FlowLayout());
        JTextArea recognitionResultTA = new JTextArea();
        recognitionResultTA.setText(result);
        JInternalFrame interFrame = new JInternalFrame("Text recognition result image", false, true, false, false);
        //interFrame.setImage(b);
//        interFrame.set
        interFrame.add(recognitionResultTA);
        interFrame.setSize(600, 400);                
        interFrame.setVisible(true);
        AppForm.desktopPane.add(interFrame);
        try {
            interFrame.setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Functions.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Functions.createInternalFrame(b, imagename + " Text recognition result image");
    }//GEN-LAST:event_textRecognizeMenuActionPerformed

    private void negativeFilterMenuClicked(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_negativeFilterMenuClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_negativeFilterMenuClicked

    private void sharpenFilterMenuClicked(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sharpenFilterMenuClicked
        WritableRaster raster = null;
        InternalAwesomeFrame selectedFrame = Functions.getSelectedInternalFrame();
        BufferedImage b = Functions.copyImage(selectedFrame.getImage());        
        Dimension size = new Dimension(selectedFrame.getDimension()); 
        
        ExtendedImage image = new ExtendedImage((Image)selectedFrame.getImage());
        ExtendedImage resultImage = (ExtendedImage) image.clone();
       
        MatrixFilter filter = new SharpenFilter(this.getMaskSize());
        
        if(filter != null) {
            resultImage = filter.applyToImage(image, resultImage);
        }
        
        Functions.createInternalFrame(resultImage, imagename + " Sharpen filter result image");
    }//GEN-LAST:event_sharpenFilterMenuClicked

    private void motionBlurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_motionBlurActionPerformed
        WritableRaster raster = null;
        InternalAwesomeFrame selectedFrame = Functions.getSelectedInternalFrame();
        BufferedImage b = Functions.copyImage(selectedFrame.getImage());        
        Dimension size = new Dimension(selectedFrame.getDimension()); 
        
        ExtendedImage image = new ExtendedImage((Image)selectedFrame.getImage());
        ExtendedImage resultImage = (ExtendedImage) image.clone();
       
        MatrixFilter filter = new MotionBlur(this.getMaskSize());
        
        if(filter != null) {
            resultImage = filter.applyToImage(image, resultImage);
        }
        
        Functions.createInternalFrame(resultImage, imagename + " Motion blur result image");
    }//GEN-LAST:event_motionBlurActionPerformed

    private void medianFilterMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_medianFilterMenuItemActionPerformed
        WritableRaster raster = null;
        InternalAwesomeFrame selectedFrame = Functions.getSelectedInternalFrame();
        BufferedImage b = Functions.copyImage(selectedFrame.getImage());        
        Dimension size = new Dimension(selectedFrame.getDimension()); 
        
        ExtendedImage image = new ExtendedImage((Image)selectedFrame.getImage());
        ExtendedImage resultImage = (ExtendedImage) image.clone();
       
        MatrixFilter filter = new MedianFilter(this.getMaskSize());
        
        if(filter != null) {
            resultImage = filter.applyToImage(image, resultImage);
        }
        
        Functions.createInternalFrame(resultImage, imagename + " Median filter result image");
    }//GEN-LAST:event_medianFilterMenuItemActionPerformed

    public int getMaskSize() {
        return maskSize;
    }

    public void setMaskSize(int maskSize) {
        this.maskSize = maskSize;
    }
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;




                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AppForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AppForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AppForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AppForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                mainForm = new AppForm();
                Toolkit tk = Toolkit.getDefaultToolkit();
                int xSize = ((int) tk.getScreenSize().getWidth());
                int ySize = ((int) tk.getScreenSize().getHeight());
                mainForm.setSize(xSize, ySize);
                mainForm.setTitle("Image processer");
                mainForm.setVisible(true);
                Dimension size = mainForm.getSize();
                mainForm.desktopPane.setSize(size);
                mainForm.desktopPane.setPreferredSize(size);
                mainForm.setBackground(Color.black);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem contentMenuItem;
    private javax.swing.JMenuItem cutMenuItem;
    public static javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem25;
    private javax.swing.JMenuItem jMenuItem26;
    private javax.swing.JMenuItem jMenuItem27;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JMenuItem medianFilterMenuItem;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem saveAsMenuItem;
    // End of variables declaration//GEN-END:variables
}
