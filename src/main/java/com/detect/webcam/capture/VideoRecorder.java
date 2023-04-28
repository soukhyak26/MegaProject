package com.detect.webcam.capture;

import com.detect.webcam.detect.HumanDetector;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VideoRecorder {
    private VideoCapture videoCapture=null;
    private Mat frame = new Mat();
    private MatOfByte mem = new MatOfByte();
    private VideoWriter writer;
    private HumanDetector humanDetector;
    public Boolean isRunnable;
    public VideoRecorder(){
        this.humanDetector = new HumanDetector();
    }
    public void initialize(){
        videoCapture= new VideoCapture(0);
        Size size = new Size(videoCapture.get(Videoio.CAP_PROP_FRAME_WIDTH), videoCapture.get(Videoio.CAP_PROP_FRAME_HEIGHT));
        int fourcc = VideoWriter.fourcc('M', 'J','P','G');
        writer = new VideoWriter("E:\\videos\\video_"+ System.currentTimeMillis() + ".avi", fourcc, 10, size,true);
    }
    public void release(){
        videoCapture.release();
        writer.release();
    }
    public int recordVideo(CamPanel component){

        Integer humanCount=0;
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
