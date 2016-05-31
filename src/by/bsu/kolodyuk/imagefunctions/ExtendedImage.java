package by.bsu.kolodyuk.imagefunctions;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
import java.util.Hashtable;
import by.bsu.kolodyuk.functions.Hystogram;

public class ExtendedImage extends BufferedImage {

    public ExtendedImage(ColorModel cm, WritableRaster raster, boolean isRasterPremultiplied, Hashtable<?, ?> properties) {
        super(cm, raster, isRasterPremultiplied, properties);
    }

    public ExtendedImage(int width, int height, int imageType, IndexColorModel cm) {
        super(width, height, imageType, cm);
    }

    public ExtendedImage(int width, int height, int imageType) {
        super(width, height, imageType);
    }

    public ExtendedImage(Image image) {
        this(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics g = this.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
    }

    public ExtendedImage getSubimage(Rectangle2D rect) {
        int x = (int) rect.getX();
        int y = (int) rect.getY();
        int width = (int) rect.getWidth();
        int height = (int) rect.getHeight();

        if(x + width > this.getWidth()) {
            width = this.getWidth() - x;
        }
        if(y + height > this.getHeight()) {
            height = this.getHeight() - y;
        }
                
        return new ExtendedImage(super.getSubimage(x, y, width, height));
    }

    public void setSubimage(ExtendedImage subimage, Rectangle2D frame) {
        int startX = (int) frame.getX();
        int startY = (int) frame.getY();
        int endX = (int) Math.min(frame.getWidth(), subimage.getWidth()) + startX;
        int endY = (int) Math.min(frame.getHeight(), subimage.getHeight()) + startY;

        for(int i = startX; i < endX; ++i) {
            for(int j = startY; j < endY; ++j) {
                this.setRGB(i, j, subimage.getRGB(i - startX, j - startY));
            }
        }
    }



    public Hystogram getHistogram() {
        return new Hystogram(this);
    }

    @Override
    public Object clone(){
       int heightImage = this.getHeight();
        int widthImage = this.getWidth();
        ExtendedImage bufImage = new ExtendedImage(widthImage, heightImage, this.getType());
        Graphics g = bufImage.createGraphics();
        g.drawImage(this, 0, 0, null);
        g.dispose();
        return bufImage;
    }
    
}
