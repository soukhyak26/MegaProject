package com.detect.webcam;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

public class CamPanel extends JPanel {
    private VideoCapture videoCapture=null;
    private DaemonThread myThread = null;
    private  Mat frame = new Mat();
    private MatOfByte mem = new MatOfByte();

    public void CamPanel() {
        Border blackline = BorderFactory.createLineBorder(Color.black);
        this.setBorder(blackline);
    }

    public void startFrame() {
        videoCapture = new VideoCapture(0);
        myThread = new CamPanel.DaemonThread();
        Thread t = new Thread(myThread);
        t.setDaemon(true);
        myThread.runnable = true;
        t.start();
    }

    public void stopFrame() {
        myThread.runnable = false;
        videoCapture.release();
    }

    class DaemonThread implements Runnable {
        protected volatile boolean runnable = false;

        @Override
        public void run() {
            synchronized (this) {
                while (runnable) {
                    if (videoCapture.grab()) {
                        try {
                            videoCapture.retrieve(frame);
                            Imgcodecs.imencode(".bmp", frame, mem);
                            Image im = ImageIO.read(new ByteArrayInputStream(mem.toArray()));

                            BufferedImage buff = (BufferedImage) im;
                            Graphics g = CamPanel.this.getGraphics();

                            if (ImageDrawer.drawScaledImage(buff,CamPanel.this,g))
                                if (runnable == false) {
                                    System.out.println("Going to wait()");
                                    this.wait();
                                }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        }

    }

}
