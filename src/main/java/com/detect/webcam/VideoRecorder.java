package com.detect.webcam;

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

public class VideoRecorder {
    private VideoCapture videoCapture=null;
    private Mat frame = new Mat();
    private MatOfByte mem = new MatOfByte();
    private VideoWriter writer;
    public VideoRecorder(){
        videoCapture= new VideoCapture(0);
        Size size = new Size(videoCapture.get(Videoio.CAP_PROP_FRAME_WIDTH), videoCapture.get(Videoio.CAP_PROP_FRAME_HEIGHT));
        int fourcc = VideoWriter.fourcc('M', 'J','P','G');
        writer = new VideoWriter("E:\\videos\\vid_001.avi", fourcc, 20, size,true);
    }
    public void release(){
        videoCapture.release();
        writer.release();
    }
    public void recordVideo(CamPanel component, CamPanel.DaemonThread runnable){
        synchronized (this) {
            while (runnable.isRunnable()) {
                if (videoCapture.grab()) {
                    try {
                        videoCapture.read(frame);
                        if (frame.empty())
                            break;
                        writer.write(frame);
                        Imgcodecs.imencode(".bmp", frame, mem);
                        Image im = ImageIO.read(new ByteArrayInputStream(mem.toArray()));
                        BufferedImage buff = (BufferedImage) im;
                        Graphics g = component.getGraphics();
                        boolean isImageDrawn = ImageDrawer.drawScaledImage(buff,component,g);

                        if (isImageDrawn) {
                            if (runnable.isRunnable() == false) {
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
    }
}
