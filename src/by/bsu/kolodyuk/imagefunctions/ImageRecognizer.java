
package by.bsu.kolodyuk.imagefunctions;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ImageRecognizer {
    
    private double minMatchPersantage = 0.99;
    private int symbolWidth = Font.SYMBOL_WIDTH;
    private int symbolHeight = Font.BODY_HEIGHT;
    private Set<Pattern> patterns;
    
    public ImageRecognizer(Set<Pattern> patterns, double minMatchPersantage) {
        this.patterns = patterns;
        this.minMatchPersantage = minMatchPersantage;
    }
    
    public ImageRecognizer(Set<Pattern> patterns) {
        this.patterns = patterns;
    }
    
    public String applyTo(ExtendedImage image){
        
        List<Line> lines = preAnalysis(image);
        
        StringBuilder resultStr = new StringBuilder();
        
        StringBuilder maxMatchLineStr = new StringBuilder();
        double maxMatch = 0;
        
        for(Line line: lines) {
            
            if(line == null) {
                resultStr.append(maxMatchLineStr);
                resultStr.append("\n");
                maxMatch = 0;
                continue;
            }
            
            StringBuilder lineStr = new StringBuilder();
            int summ = 0;
            
            for(int i = line.getStartX(); i < line.getEndX(); i += symbolWidth) {
                
                ExtendedImage subimage = image.getSubimage(new Rectangle2D.Double(i, line.getStartY(), symbolWidth, symbolHeight));
                Pattern p = findPattern(subimage);
                if(p != null) {
                    summ += p.getMatchPercentage();
                    lineStr.append(p.getSymbol());
                }
            }
            
            if(summ > maxMatch) {
                maxMatch = summ;
                maxMatchLineStr = lineStr;
            }
        }
        return resultStr.toString();
    }
    
    private Pattern findPattern(ExtendedImage image){
        double maxPercentageMatch = 0;
        Pattern result = null;
        for(Pattern p: patterns) {
            double percentageMatch = ImagePixelIntersection.percentageMatch(image, p.getImage());
            if(percentageMatch >= minMatchPersantage && percentageMatch > maxPercentageMatch) {
                maxPercentageMatch = percentageMatch;
                result = p.clone();
                result.setMatchPercentage(percentageMatch);
            }
        }
        return result;
    }
    
    private List<Line> preAnalysis(ExtendedImage image) {
        ////
        List<Line> result = new ArrayList<Line>();
        
        int startY = -1;
        
        int startX = Integer.MAX_VALUE;
        int endX = -1;
        
        int black = 0;
        
        for(int j = 0; j < image.getHeight(); j++) {
            
            int minBlackX = -1;
            int maxBlackX = -1;
                        
            for(int i = 0; i < image.getWidth(); i++) {  
                int rgb = image.getRGB(i, j);
                int brightness = (rgb >> 16) & 0x000000FF;
                if(brightness == black) {
                    minBlackX = (minBlackX == -1)? i : minBlackX;
                    maxBlackX = i;
                }
            }
            
            if(minBlackX != -1 && maxBlackX != -1) {
                startY = (startY == -1)? j : startY;
                if(startX > minBlackX) {
                    startX = minBlackX;
                }
                if(endX < maxBlackX) {
                    endX = maxBlackX;
                }
            } else if(startY != -1 && j - startY > Font.TOPLINE) {
                Line line = new Line(startX, endX, startY, j - 1);
                List<Line> duplicatesHeight = Font.normalizeLineHeight(line);
                duplicatesHeight.add(line);
                /////
                List<Line> duplicatesWidth = new ArrayList<Line>();
                for(Line d: duplicatesHeight) {
                    duplicatesWidth.addAll(Font.normalizeLineWidth(d, image.getWidth()));
                }
                result.addAll(duplicatesHeight);
                result.addAll(duplicatesWidth);
                result.add(null);
                startY = -1;
            } 
        }
        return result;
        
    }

    public double getMinMatchPersantage() {
        return minMatchPersantage;
    }

    public void setMinMatchPersantage(double minMatchPersantage) {
        this.minMatchPersantage = minMatchPersantage;
    }

    public Set<Pattern> getPatterns() {
        return patterns;
    }

    public void setPatterns(Set<Pattern> patterns) {
        this.patterns = patterns;
    }
    
}
