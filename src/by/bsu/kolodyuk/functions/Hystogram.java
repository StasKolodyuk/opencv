/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsu.kolodyuk.functions;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Pavel
 */
public class Hystogram {
    public static final int BEGIN = 0;
    public static final int END = 255;
    public static final int CHANNEL_COUNT = 4;

    public static final HistogramChannel DEFAULT_HISTOGRAM_CHANNEL = HistogramChannel.RGB;


    private Integer[][] brightPixelGradation = new Integer[CHANNEL_COUNT][END+1];

    public Hystogram() {
        for(int i=0; i<CHANNEL_COUNT; ++i) {
            Arrays.fill(brightPixelGradation[i], new Integer(0));
        }
    }

    public Hystogram(BufferedImage image) {
        this();
        if(image == null) {
            throw new IllegalArgumentException("image is null");
        }
        extractValues(image);
    }

    public Hystogram(Integer[][] brightPixelGradation) {
        this();
        if(brightPixelGradation == null) {
            throw new IllegalArgumentException("brightPixelGradation is null");
        }
        for(int i=0; i<brightPixelGradation.length; ++i) {
            this.brightPixelGradation[i] = Arrays.copyOf(brightPixelGradation[i], brightPixelGradation[i].length);
        }
    }
    
    private void extractValues(BufferedImage image) {
        for(int i = 0; i< image.getWidth(); ++i) {
            for(int j = 0; j < image.getHeight(); ++j) {
                int rgb = image.getRGB(i, j);
                int alpha = (rgb >> 24) & 0x000000FF;
                int red = (rgb >> 16) & 0x000000FF;
                int green = (rgb >> 8) & 0x000000FF;
                int blue = (rgb) & 0x000000FF;
                
                int grey = (int) (0.3*red + 0.59*green + 0.11*blue);

                if(red == green && green == blue){
                    brightPixelGradation[HistogramChannel.RGB.ordinal()][red]++;
                } else {
                    brightPixelGradation[HistogramChannel.RGB.ordinal()][grey]++;
                }
                    
                brightPixelGradation[HistogramChannel.RED.ordinal()][red]++;
                brightPixelGradation[HistogramChannel.GREEN.ordinal()][green]++;
                brightPixelGradation[HistogramChannel.BLUE.ordinal()][blue]++;
            }
        }
    }

    public Integer getMaxValue(HistogramChannel channel) {
        return Collections.max(Arrays.asList(brightPixelGradation[channel.ordinal()]));
    }
    
    public Integer[] getValues(HistogramChannel channel) {
        return brightPixelGradation[channel.ordinal()];
    }

    public Integer[][] getBrightPixelGradation() {
        return brightPixelGradation.clone();
    }


    public double findAverage(HistogramChannel channel) {
        double result = 0.0;
        for(int i=BEGIN; i<END+1; ++i) {
            result += brightPixelGradation[channel.ordinal()][i];
        }
        return (double)(result/(END+1));
    }

    public double findStandardDeviation(HistogramChannel channel) {
        double result = 0.0;
        double average = findAverage(channel);
        for(int i=BEGIN; i<END+1; ++i) {
            int value = brightPixelGradation[channel.ordinal()][i];
            result += Math.pow(value - average, 2);
        }
        return Math.pow((double)(result/END), 0.5);
    }

    public double findMedian(HistogramChannel channel) {
        Integer[] temp = brightPixelGradation[channel.ordinal()].clone();
        Arrays.sort(temp);
        return (double)(temp[127]+temp[128])/2;
    }

    public Integer get(int i, int j) {
        return brightPixelGradation[i][j];
    }

    public Integer get(HistogramChannel channel, int j) {
        return get(channel.ordinal(),j);
    }

    public void set(int i, int j, int newCount) {
        brightPixelGradation[i][j] = newCount;
    }

    public void set(HistogramChannel channel, int j, int newCount) {
         set(channel.ordinal(),j, newCount);
    }

    public Map<Extremum, List<Integer> > findLocalExtremums() {
        Map<Extremum, List<Integer> > extremums = new HashMap<Extremum, List<Integer> >();
        extremums.put(Extremum.MINIMUM, new ArrayList<Integer>());
        extremums.put(Extremum.MAXIMUM, new ArrayList<Integer>());

        boolean isIncrease = true;
        for(int j = Hystogram.BEGIN+1; j < Hystogram.END+1; ++j) {
            int prevValue = get(DEFAULT_HISTOGRAM_CHANNEL, j-1);
            int currValue = get(DEFAULT_HISTOGRAM_CHANNEL, j);
            if(j == 1 && currValue - prevValue >= 0) {
                List<Integer> indexes = extremums.get(Extremum.MINIMUM);
                indexes.add(0);
            }
            if(isIncrease^(currValue - prevValue >= 0)) {
                Extremum extremumType = (isIncrease)? Extremum.MAXIMUM : Extremum.MINIMUM;
                List<Integer> indexes = extremums.get(extremumType);
                indexes.add(j-1);
                isIncrease = !isIncrease;
            }
        }
        Extremum extremumType = (isIncrease)? Extremum.MAXIMUM : Extremum.MINIMUM;
        List<Integer> indexes = extremums.get(extremumType);
        indexes.add(Hystogram.END);

        return extremums;
    }
    
    public List<Integer> findLocalMaximums() {
        return findLocalExtremums().get(Extremum.MAXIMUM);
    }
    
    public List<Integer> findLocalMinimums() {
        return findLocalExtremums().get(Extremum.MINIMUM);
    }
    
    public Integer findAverageOnSegment(int begin, int end) {
        Integer sum = 0;
        for(int i = begin; i<end; ++i) {
            sum += get(DEFAULT_HISTOGRAM_CHANNEL,i);
        }
        return sum / (end - begin);
    }
    
    public Integer findMaxOnSegment(int begin, int end) {
        Integer max = Integer.MIN_VALUE;
        int brightness = begin;
        for(int i = begin; i<end; ++i) {
            if(max < get(DEFAULT_HISTOGRAM_CHANNEL,i)) {
                max = get(DEFAULT_HISTOGRAM_CHANNEL,i);
                brightness = i;
            }
        }
        return brightness;
    }


    public Integer findMinOnSegment(int begin, int end) {
        Integer min = Integer.MAX_VALUE;
        int brightness = begin;
        for(int i = begin; i<end; ++i) {
            if(min > get(DEFAULT_HISTOGRAM_CHANNEL,i)) {
                min = get(DEFAULT_HISTOGRAM_CHANNEL,i);
                brightness = i;
            }
        }
        return brightness;
    }
    
    public Integer getIndexOfMinValue() {
        int result = 0;
        for(int i = BEGIN; i < END+1; ++i) {
            if(get(DEFAULT_HISTOGRAM_CHANNEL, i) != 0) {
                result = i;
                break;
            }
        }
        return result;
    }

    
   public Integer getIndexOfMaxValue() {
        int result = 0;
        for(int i = END; i >= BEGIN; --i) {
            if(get(DEFAULT_HISTOGRAM_CHANNEL, i) != 0) {
                result = i;
                break;
            }
        }
        return result;
    }

    @Override
    public Object clone(){
        return new Hystogram(brightPixelGradation);
    }
    
}
