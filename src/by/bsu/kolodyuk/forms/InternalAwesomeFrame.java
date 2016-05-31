package by.bsu.kolodyuk.forms;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import by.bsu.kolodyuk.functions.Functions;

public class InternalAwesomeFrame extends JInternalFrame {

    private JPanel panel = new JPanel();
    private String imageName;
    public BufferedImage image;
    public Dimension dimension;
    private Point first = new Point();
    private Point second = new Point();
    private boolean isFirstPointSelected = false;
    private boolean isAreaSelected = false;

    public InternalAwesomeFrame() {
    }

    public InternalAwesomeFrame(BufferedImage image, String title, boolean resizable, boolean closable, boolean maximizable, boolean iconifiable) {
        super(title, resizable, closable, maximizable, iconifiable);
        this.setImageName(imageName);
        this.setImage(image);
        this.setDimension(image);
           
        this.setBackground(Color.red);
        
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (!isFirstPointSelected) {
                    first.setLocation(e.getX(), e.getY());
                    isFirstPointSelected = true;
                    isAreaSelected = false;
                    System.out.println("First point: " + first);
                } else {
                    second.setLocation(e.getX(), e.getY());
                    isFirstPointSelected = false;
                    isAreaSelected = true;
                    System.out.println("2nd point: " + second);
                }
            }
        });
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        BufferedImage b = Functions.copyImage(image);
        this.image = b;
        ImageIcon ic = new ImageIcon();
        ic.setImage(image);
        JLabel l = new JLabel(ic);
        this.add(l);
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(BufferedImage b) {
        Dimension d = new Dimension(b.getWidth(), b.getHeight());
        this.dimension = d;
    }
    
    public Point getFirst() {
        return first;
    }

    public void setFirst(Point first) {
        this.first = first;
    }

    public Point getSecond() {
        return second;
    }

    public void setSecond(Point second) {
        this.second = second;
    }

    public boolean isIsAreaSelected() {
        return isAreaSelected;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }
    
}
