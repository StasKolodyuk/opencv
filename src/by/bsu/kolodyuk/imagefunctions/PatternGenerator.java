package by.bsu.kolodyuk.imagefunctions;

import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.HashSet;
import java.util.Set;
import javax.swing.ImageIcon;

/**
 *
 * @author Natalya Klimenkova
 */
public class PatternGenerator {
    
   //private static final String SYMBOLS = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЬЪЭЮЯЫабвгдеёжзийклмнопрстуфхцчшщьъэюяы0123456789,?!.";
    private static final String SYMBOLS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789,?!.:;'[]{}()-_ ";
    
   public static Set<Pattern> readFrom(String directoryName) {
       
       Set<Pattern> patterns = new HashSet<Pattern>();
       File templateDir = new File(directoryName);
       File[] patternFiles = templateDir.listFiles();
       
       for(File file: patternFiles) {
           String fileName = file.getName();
           int dotIndex = fileName.indexOf(".");
           String symbol = fileNameToPatternName(fileName.substring(0, dotIndex));
           Image image = new ImageIcon(file.getPath()).getImage();
           patterns.add(new Pattern(symbol, image));
       }
       
       return patterns;
       
   }
   
   private static String fileNameToPatternName(String name) {
       if (name == "space"){
           return " ";
       }
       else if (name == "question"){
           return "?";
       }
       else if (name == "dot"){
           return ".";
       }
       else if (name == "exclamation"){
           return "!";
       }
       else if (name == "star"){
           return "*";
       }
       else if (name == "comma"){
           return ",";
       }
       else if (name == "colon"){
           return ":";
       }
       else if (name == "semicolon"){
           return ";";
       }
       else if (name == "quote"){
           return "'";
       }
       else{
           return name;
       }
       
   }
   
   private static String patternNameToFileName(String name) {
       if (name == " "){
           return "space";
       }
       else if (name == "?"){
           return "question";
       }
       else if (name == "."){
           return "dot";
       }
       else if (name == "!"){
           return "exclamation";
       }
       else if (name == "*"){
           return "star";
       }
       else if (name == ","){
           return "comma";
       }
       else if (name == ":"){
           return "colon";
       }
       else if (name == ";"){
           return "semicolon";
       }
       else if (name == "'"){
           return "quote";
       }
       else{
           return name;
       }
   }
   
   public static Set<Pattern> readFrom(Image image) {
       Set<Pattern> patterns = new HashSet<Pattern>();
       ExtendedImage patternsImage = new ExtendedImage(image);
       int k = 0;
       for(int j = 0; j < patternsImage.getHeight(); j += Font.BODY_HEIGHT + Font.LEADING) {
           for(int i = 0; i < patternsImage.getWidth(); i += Font.SYMBOL_WIDTH) {
               ExtendedImage symbolImage = patternsImage.getSubimage(new Rectangle2D.Double(i, j, Font.SYMBOL_WIDTH, Font.BODY_HEIGHT));
               if(k >= SYMBOLS.length() ) {
                   break;
               }
               
               Pattern pattern = new Pattern(SYMBOLS.substring(k, k+1), symbolImage);
               patterns.add(pattern);
//                try {
//                    ImageIO.write(pattern.getImage(), "png", new File("images/Recognition/patterns/" + patternNameToFileName(pattern.getSymbol())+ ".png"));
//                } catch (IOException ex) {
//                    Logger.getLogger(PatternGenerator.class.getName()).log(Level.SEVERE, null, ex);
//                    System.err.println(ex.getMessage());
//                }
               k++;
           }
       }
       
       return patterns;
   }

}
