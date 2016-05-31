package by.bsu.kolodyuk.functions;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import by.bsu.kolodyuk.forms.HystogramPanel;
import by.bsu.kolodyuk.forms.AppForm;
import static by.bsu.kolodyuk.forms.AppForm.desktopPane;

import by.bsu.kolodyuk.forms.InternalAwesomeFrame;

public class Functions {

    public static int findSelectedFrameIndex(ArrayList<JInternalFrame> l) {
        int index = 0;
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).isSelected()) {
                index = i;
                System.out.println("Selected frame found");
            }
        }
        return index;
    }

    public static BufferedImage copyImage(BufferedImage b) {
        int heightImage = b.getHeight();
        int widthImage = b.getWidth();
//        BufferedImage bufImage = new BufferedImage(widthImage, heightImage, BufferedImage.TYPE_INT_RGB);
        BufferedImage bufImage = new BufferedImage(widthImage, heightImage, b.getType());
        Graphics g = bufImage.createGraphics();
        g.drawImage(b, 0, 0, null);
        g.dispose();
        return bufImage;
    }

    public static void createInternalFrame(BufferedImage b, String imageName) {
        InternalAwesomeFrame interFrame = new InternalAwesomeFrame(b, imageName, false, true, false, false);
        interFrame.setImage(b);
        interFrame.setSize(interFrame.getDimension());                
        interFrame.setVisible(true);
        AppForm.desktopPane.add(interFrame);

        try {
            interFrame.setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Functions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void createHystogramInternalFrame(BufferedImage b, String title){
        int[] hData = Functions.calcHystogramData(b);
        createHystogramInternalFrame(hData, title);
    }
    
    public static void createHystogramInternalFrame(int[] hData, String title){
        HystogramPanel h = new HystogramPanel(hData);
        h.setVisible(true);
        JInternalFrame interFrame = new JInternalFrame(title, false, true, false, false);
        interFrame.setSize(600, 320);
        interFrame.add(h);
        interFrame.setVisible(true);
        AppForm.desktopPane.add(interFrame);
        try {
            interFrame.setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Functions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static InternalAwesomeFrame getSelectedInternalFrame(){
        InternalAwesomeFrame selectedFrame = new InternalAwesomeFrame();
        JInternalFrame[] interFrames = desktopPane.getAllFrames();
        for(JInternalFrame f : interFrames){
            if(f.isSelected()){
                selectedFrame = (InternalAwesomeFrame) f;
            }
        }
        return selectedFrame;
    }

    public static int[] calcHystogramData(BufferedImage b) {
        WritableRaster raster = null;
        raster = b.getRaster();
        int[] data = new int[4];
        int[] hyst = new int[256];
        for (int i = 0; i < hyst.length; i++) {
            hyst[i] = 0;
        }
        Dimension size = new Dimension(b.getWidth(), b.getHeight());
        for (int x = 0; x < size.width; x++) {
            for (int y = 0; y < size.height; y++) {
                data = raster.getPixel(x, y, new int[4]);
                int brightness = (int)(data[0] * 0.3 + data[1] * 0.59 + data[2] * 0.11);
                hyst[brightness] += 1;
            }
        }
        return hyst;
    }

    public static int findMinElement(int[] m) {
        int min = 10000;
        for (int i = 0; i < m.length; i++) {
            if (m[i] < min) {
                min = m[i];
            }
        }
        return min;
    }

    public static int findLocalMinElement(int[] m, int ind1, int ind2) {
        int min = 10000;
        for (int i = ind1; i < ind2; i++) {
            if (m[i] < min) {
                min = m[i];
            }
        }
        return min;
    }
    
    public static int findRowLocalMinElement(int[][] m, int ind1, int ind2, int i) {
        int min = 10000;
        for (int j = ind1; j < ind2; j++) {
            if (m[i][j] < min) {
                min = m[i][j];
            }
        }
        return min;
    }
    
    public static int findColumnLocalMinElement(int[][] m, int ind1, int ind2, int j) {
        int min = 10000;
        for (int i = ind1; i < ind2; i++) {
            if (m[i][j] < min) {
                min = m[i][j];
            }
        }
        return min;
    }

    public static int findMaxElement(int[] m) {
        int max = -1;
        for (int i = 0; i < m.length; i++) {
            if (m[i] > max) {
                max = m[i];
            }
        }
        return max;
    }
    
    public static int findLocalMaxElement(int[] m, int ind1, int ind2) {
        int max = -1;
        for (int i = ind1; i < ind2; i++) {
            if (m[i] > max) {
                max = m[i];
            }
        }
        return max;
    }
    
    public static int findRowLocalMaxElement(int[][] m, int ind1, int ind2, int i) {
        int max = -1;
        for (int j = ind1; j < ind2; j++) {
            if (m[i][j] > max) {
                max = m[i][j];
            }
        }
        return max;
    }
    
    public static int findColumnLocalMaxElement(int[][] m, int ind1, int ind2, int j) {
        int max = -1;
        for (int i = ind1; i < ind2; i++) {
            if (m[i][j] > max) {
                max = m[i][j];
            }
        }
        return max;
    }

    public static int[] smoothHystogram(int[] hData) {
        for (int i = 1; i < hData.length - 1; i++) {
            //hData[i] = (hData[i - 1] + hData[i] + hData[i + 1]) / 3;
            hData[i] = (int) (0.2 * hData[i - 1] + 0.6 * hData[i] + 0.2 * hData[i + 1]);
            //0.2*a[i-1]+0.6*a[i]+0.2*a[i+1]
        }
        return hData;
    }

    public static int[] sortArray(int[] a) {
        int temp = 0;
        for (int i = 1; i < a.length; i++) {
            for (int j = 0; j < a.length - i; j++) {
                if (a[j] > a[j + 1]) {
                    temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
        return a;
    }

    public static int findSecondMax(int[] a, int max) {
        int max2 = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] > max2 && a[i] != max) {
                max2 = a[i];
            }
        }
        return max2;
    }

    public static int getElementIndex(int[] a, int element) {
        int index = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == element) {
                index = i;
                break;
            }
        }
        return index;
    }
    
    public static int getElementIndexOnRowDiapason(int[][] a, int element, int ind1, int ind2, int i) {
        int index = 0;
        for (int j = ind1; j <= ind2; j++){
            if (a[i][j] == element){
                index = j;
                break;
            }
        }
        return index;
    }
    
    public static int getElementIndexOnColumnDiapason(int[][] a, int element, int ind1, int ind2, int j) {
        int index = 0;
        for (int i = ind1; i <= ind2; i++){
            if (a[i][j] == element){
                index = i;
                break;
            }
        }
        return index;
    }

    public static int findLocalMinInHystogram(int[] a, int max1, int max2) {
        int min = 0;
        if (getElementIndex(a, max2) < getElementIndex(a, max1)) {
            int tmp = max2;
            max2 = max1;
            max1 = tmp;
        }
        min = findLocalMinElement(a, getElementIndex(a, max1), getElementIndex(a, max2));
        return min;
    }
    
    public static int findLocalMinInHystogramByMaxInd(int[] a, int ind1, int ind2) {
        int min = 0;
        min = findLocalMinElement(a, ind1, ind2);
        return min;
    }

    public static int[] findLocalHystMaximums(int[] a) {
        int[] maxs = new int[100];
        for (int i = 0; i < maxs.length; i++) {
            maxs[i] = -1;
        }
        boolean raise = false;
        int max = 0;
        int j = 0;
        for (int i = 0; i < a.length; i++) {
            if (max < a[i]) {
                raise = true;
            } else if (raise && max > a[i]) {
                maxs[j] = max;
                raise = false;
                j++;
            }
            max = a[i];
        }
        return maxs;
    }
    
    public static String newJDialog(Component parent, String msg, String title){
        Object[] options = {"OK", "Cancel"};
        String answer = JOptionPane.showInputDialog(parent,
                msg, title, JOptionPane.QUESTION_MESSAGE);
        if (answer == null) {
            JOptionPane.showInputDialog(parent,
                    "You must input the something!",
                    "Error", JOptionPane.ERROR_MESSAGE);
        } else if (Integer.parseInt(answer) < 0) {
            JOptionPane.showInputDialog(parent,
                    "The number cannot be negative!",
                    "Error", JOptionPane.ERROR_MESSAGE);
        } 
        return answer;
    }
    
    public static Set<Point2D> extractContourFromGrayscale(BufferedImage image,  int rangeSize, int brightnessDelta) {
        Set<Point2D> contour = new HashSet<Point2D>();

        int width = image.getWidth();
        int height = image.getHeight();

        List<Integer> currentHorizontalRange = new ArrayList<Integer>();
        List<Integer> currentVerticalRange = new ArrayList<Integer>();

        for(int i = 0; i < width - rangeSize; ++i) {
            for(int j = 0; j < height; ++j) {
                currentHorizontalRange.clear();
                for(int k =0; k < rangeSize; ++k) {
                    currentHorizontalRange.add((image.getRGB(i+k, j)) & 0x000000FF);
                }
                int maxHorizontalBrightness = Collections.max(currentHorizontalRange);
                int minHorizontalBrightness = Collections.min(currentHorizontalRange);
                

                if(maxHorizontalBrightness - minHorizontalBrightness >= brightnessDelta) {
                    contour.add(new Point2D.Double(i+ rangeSize/2,j));
                }

            }
        }
        
        for(int j = 0; j < height - rangeSize; ++j) {
            for(int i = 0; i < width; ++i) {
                currentVerticalRange.clear();
                for(int k =0; k < rangeSize; ++k) {
                    currentVerticalRange.add((image.getRGB(i, j+k)) & 0x000000FF);
                }
                
                int maxVerticalBrightness = Collections.max(currentVerticalRange);
                int minVerticalBrightness = Collections.min(currentVerticalRange);

                if(maxVerticalBrightness - minVerticalBrightness >= brightnessDelta) {
                    contour.add(new Point2D.Double(i,j + rangeSize/2));
                }
            }
        }


        return contour;
    }
    
    public static BufferedImage emphasizeContour(BufferedImage image, Set<Point2D> contourPoints) {
        //BufferedImage resultImage = (ExtendedImage) image.clone();
        int width = image.getWidth();
        int height = image.getHeight();
        int blackRGB = (255 << 24);
        for(int i = 0; i < width; ++i) {
            for(int j = 0; j < height; ++j) {
                if(contourPoints.contains(new Point2D.Double(i,j))) {
                    image.setRGB(i, j, blackRGB);
                }
            }
        }
        return image;
    } 
}
