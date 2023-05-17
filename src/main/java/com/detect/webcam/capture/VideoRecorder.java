package com.detect.webcam.capture;

import com.detect.webcam.detect.HumanDetector;
import com.detect.webcam.ui.CamPanel;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.VideoWriter;
import org.opencv.videoio.Videoio;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
//Class Responsible for Video Capturing, and Recording videos
public class VideoRecorder {
    private VideoCapture videoCapture = null;
    private final Mat frame = new Mat();
    private final MatOfByte mem = new MatOfByte();
    private VideoWriter writer;
    private final HumanDetector humanDetector;
    public Boolean isRunnable;

    public VideoRecorder() {
        this.humanDetector = new HumanDetector();
    }

    //The method to initialize Video Capture Process and Video recorder Process
    //VideoWriter will need path where videos will be stored, image encoding(MPEG), frames per second, frame size etc.
    public void initialize() {
        videoCapture = new VideoCapture(0);
        Size size = new Size(videoCapture.get(Videoio.CAP_PROP_FRAME_WIDTH), videoCapture.get(Videoio.CAP_PROP_FRAME_HEIGHT));
        int fourcc = VideoWriter.fourcc('M', 'J', 'P', 'G');
        writer = new VideoWriter("E:\\videos\\video_" + System.currentTimeMillis() + ".avi", fourcc, 10, size, true);
    }

    //method to release handle of external resources such as webcam and video writer( file handle)
    public void release() {
        humanDetector.setClosed();
        videoCapture.release();
        writer.release();
    }

    //method to
    //capture video
    //split video into frames
    //detect Humans in each frame using Human Detector
    //redraw each frame in cam panel by rectangle surrounding human face
    //record video
    public int recordVideo(CamPanel component) {

        Integer humanCount = 0;
        synchronized (this) {
            if (isRunnable) {
                if (videoCapture.grab()) {
                    try {
                        videoCapture.retrieve(frame);
                        if (frame.empty())
                            return 0;
                        humanCount = humanDetector.detectAndDisplay(frame);
                        System.out.println("humanCount: " + humanCount);

                        Imgcodecs.imencode(".bmp", frame, mem);
                        Image im = ImageIO.read(new ByteArrayInputStream(mem.toArray()));
                        BufferedImage buff = (BufferedImage) im;
                        Graphics g = component.getGraphics();
                        boolean isImageDrawn = ImageDrawer.drawScaledImage(buff,component,g);
                        writer.write(frame);
                        if (isImageDrawn) {
                            if (isRunnable == false) {
                                System.out.println("Going to wait()");
                                this.wait();
                            }
                        }
                        System.out.println("Done capturing and writing");

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
        return humanCount;
    }

}
