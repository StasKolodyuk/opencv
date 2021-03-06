package by.bsu.kolodyuk;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import by.bsu.kolodyuk.imagefunctions.*;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.SVGPath;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;
import javax.swing.*;

public class ImageController
{
    @FXML
    private BorderPane pane;
	@FXML
	private ImageView imageView;
	private Stage stage;
	private FileChooser fileChooser;
    private File file;
	private Mat image;
    private boolean grayscaled;

	protected void init() {
		this.fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("C:/Users/Stanislau_Kaladziuk/Desktop"));
		this.image = new Mat();
	}

	@FXML
	public void onLoadImageButtonPressed() {
		file = this.fileChooser.showOpenDialog(this.stage);
		if (file != null) {
			image = Imgcodecs.imread(file.getAbsolutePath());
			imageView.setImage(mat2Image(image));
		}
	}

    @FXML
    public void onShowImageButtonPressed() {
        imageView.setImage(mat2Image(image));
    }

    @FXML
    public void onShowHistogramButtonPressed() {
        showHistogram(image, grayscaled);
    }

    @FXML
    public void onGrayscaleButtonPressed() {
        Imgproc.cvtColor(image, image, Imgproc.COLOR_BGR2GRAY);
        imageView.setImage(mat2Image(image));
        grayscaled = true;
    }

    @FXML
    public void onBinarizateButtonPressed() {
        Imgproc.threshold(image, image, 127, 255, Imgproc.THRESH_BINARY);
        imageView.setImage(mat2Image(image));
    }

    @FXML
    public void onRemoveBinaryNoiseButtonPressed() {
        Mat element = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new  Size(5, 5));
        Imgproc.erode(image, image, element);
        imageView.setImage(mat2Image(image));
    }

    @FXML
    public void onRemoveGrayscaleAverageNoiseButtonPressed() {
        Imgproc.blur(image, image, new Size(5, 5));
        imageView.setImage(mat2Image(image));
    }

    @FXML
    public void onRemoveGrayscaleMedianNoiseButtonPressed() {
        Imgproc.medianBlur(image, image, 5);
        imageView.setImage(mat2Image(image));
    }

    @FXML
    public void onBinaryBorderButtonPressed() {
        List<MatOfPoint> contours = new ArrayList<>();
        Imgproc.findContours(image, contours, new Mat(), Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);
        Imgproc.drawContours(image, contours, -1, new Scalar(255, 0, 0), 3);
        imageView.setImage(mat2Image(image));
    }

    @FXML
    public void onCannyBorderButtonPressed() {
        Mat borderImage = new Mat();
        Imgproc.Canny(image, borderImage, 50, 150, 3, true);
        imageView.setImage(mat2Image(borderImage));
    }

    @FXML
    public void onLaplacianBorderButtonPressed() {
        Mat borderImage = new Mat();
        Imgproc.Laplacian(image, borderImage, image.depth(), 5, 1, 0, Core.BORDER_DEFAULT);
        imageView.setImage(mat2Image(borderImage));
    }

    @FXML
    public void onErodeButtonPressed() {
        Mat element = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new  Size(5, 5));
        Imgproc.erode(image, image, element);
        imageView.setImage(mat2Image(image));
    }

    @FXML
    public void onDilateButtonPressed() {
        Mat element = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new  Size(5, 5));
        Imgproc.dilate(image, image, element);
        imageView.setImage(mat2Image(image));
    }

    @FXML
    public void onOpeningButtonPressed() {
        Mat element = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new  Size(5, 5));
        Imgproc.morphologyEx(image, image, Imgproc.MORPH_OPEN, element);
        imageView.setImage(mat2Image(image));
    }

    @FXML
    public void onClosingButtonPressed() {
        Mat element = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new  Size(5, 5));
        Imgproc.morphologyEx(image, image, Imgproc.MORPH_CLOSE, element);
        imageView.setImage(mat2Image(image));
    }

    @FXML
    public void onSegmentationButtonPressed() {
        WritableImage writableImage = SwingFXUtils.toFXImage(KMeans.doAlg(file.getAbsolutePath(), 3, "-i"), null);
        imageView.setImage(writableImage);
    }

    @FXML
    public void onRecognizeButtonPressed() {
        Set<Pattern> patterns = PatternGenerator.readFrom(new ImageIcon("symbols.png").getImage());
        ImageRecognizer recognizer = new ImageRecognizer(patterns);
        String result = recognizer.applyTo(new ExtendedImage(getImageFromPath()));
        Platform.runLater(() -> JOptionPane.showMessageDialog(null, result.isEmpty() ? "Couldn't recognize text" : result));
    }

    @FXML
    public void onVectorizeButtonPressed() throws Exception {
        String result = ImageTracer.imageToSVG(file.getAbsolutePath(), null, null).replace(">", ">\n");
        Platform.runLater(() -> JOptionPane.showMessageDialog(null, result));
    }

    public java.awt.Image getImageFromPath() {
        try {
            return ImageIO.read(file.getAbsoluteFile());
        } catch (IOException e) {
            return null;
        }
    }

	public void setStage(Stage stage)
	{
		this.stage = stage;
	}

	private Image mat2Image(Mat frame) {
		MatOfByte buffer = new MatOfByte();
        Imgcodecs.imencode(".png", frame, buffer);
		return new Image(new ByteArrayInputStream(buffer.toArray()));
	}

    private void showHistogram(Mat frame, boolean gray)
    {
        // split the frames in multiple images
        List<Mat> images = new ArrayList<Mat>();
        Core.split(frame, images);

        // set the number of bins at 256
        MatOfInt histSize = new MatOfInt(256);
        // only one channel
        MatOfInt channels = new MatOfInt(0);
        // set the ranges
        MatOfFloat histRange = new MatOfFloat(0, 256);

        // compute the histograms for the B, G and R components
        Mat hist_b = new Mat();
        Mat hist_g = new Mat();
        Mat hist_r = new Mat();

        // B component or gray image
        Imgproc.calcHist(images.subList(0, 1), channels, new Mat(), hist_b, histSize, histRange, false);

        // G and R components (if the image is not in gray scale)
        if (!gray)
        {
            Imgproc.calcHist(images.subList(1, 2), channels, new Mat(), hist_g, histSize, histRange, false);
            Imgproc.calcHist(images.subList(2, 3), channels, new Mat(), hist_r, histSize, histRange, false);
        }

        // draw the histogram
        int hist_w = (int)imageView.getFitWidth(); // width of the histogram image
        int hist_h = (int)imageView.getFitHeight(); // height of the histogram image
        int bin_w = (int) Math.round(hist_w / histSize.get(0, 0)[0]);

        Mat histImage = new Mat(hist_h, hist_w, CvType.CV_8UC3, new Scalar(0, 0, 0));
        // normalize the result to [0, histImage.rows()]
        Core.normalize(hist_b, hist_b, 0, histImage.rows(), Core.NORM_MINMAX, -1, new Mat());

        // for G and R components
        if (!gray)
        {
            Core.normalize(hist_g, hist_g, 0, histImage.rows(), Core.NORM_MINMAX, -1, new Mat());
            Core.normalize(hist_r, hist_r, 0, histImage.rows(), Core.NORM_MINMAX, -1, new Mat());
        }

        // effectively draw the histogram(s)
        for (int i = 1; i < histSize.get(0, 0)[0]; i++)
        {
            // B component or gray image
            Imgproc.line(histImage, new Point(bin_w * (i - 1), hist_h - Math.round(hist_b.get(i - 1, 0)[0])),
                    new Point(bin_w * (i), hist_h - Math.round(hist_b.get(i, 0)[0])), new Scalar(255, 0, 0), 2, 8, 0);
            // G and R components (if the image is not in gray scale)
            if (!gray)
            {
                Imgproc.line(histImage, new Point(bin_w * (i - 1), hist_h - Math.round(hist_g.get(i - 1, 0)[0])),
                        new Point(bin_w * (i), hist_h - Math.round(hist_g.get(i, 0)[0])), new Scalar(0, 255, 0), 2, 8,
                        0);
                Imgproc.line(histImage, new Point(bin_w * (i - 1), hist_h - Math.round(hist_r.get(i - 1, 0)[0])),
                        new Point(bin_w * (i), hist_h - Math.round(hist_r.get(i, 0)[0])), new Scalar(0, 0, 255), 2, 8,
                        0);
            }
        }

        // display the histogram...
        Image histImg = mat2Image(histImage);
        imageView.setImage(histImg);
    }



}
