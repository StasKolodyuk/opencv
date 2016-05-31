
package by.bsu.kolodyuk.imagefunctions;

/**
 *
 * @author Natalya Klimenkova
 */
public class ImagePixelIntersection {
    
    public static double percentageMatch(ExtendedImage image1, ExtendedImage image2) {
        
        if(image1 == null || image2 == null) {
            return 0;
            //throw new IllegalArgumentException("One of the both images is null");
        }
        int width1 = image1.getWidth();
        int height1 = image1.getHeight();
        
        int width2 = image2.getWidth();
        int height2 = image2.getHeight();
        
        if(width1 != width2 || height1 != height2) {
            return 0;
            //throw new IllegalArgumentException("Incorrect size of images");
        }
        int totalPixelsCount = width1 * height1;
        int matchPixelCount = 0;
        
        for(int i = 0; i < width1; ++i) {
            for(int j = 0; j < height1; ++j) {
                int rgb1 = image1.getRGB(i, j);
                int rgb2 = image2.getRGB(i, j);
                int color1 = (rgb1 >> 16) & 0x000000FF;
                int color2 = (rgb2 >> 16) & 0x000000FF;
                if(color1 == color2) {
                    matchPixelCount++;
                }
//                if(rgb1 == rgb2) {
//                    matchPixelCount++;
//                }
            }
        }
        
        return (double)matchPixelCount / totalPixelsCount;
    }

}
