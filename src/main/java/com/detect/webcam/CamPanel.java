package com.detect.webcam;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.VideoWriter;
import org.opencv.videoio.Videoio;

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
    private VideoStreamWriter writer;
    public void CamPanel() {
        Border blackline = BorderFactory.createLineBorder(Color.black);
        this.setBorder(blackline);
        writer = new VideoStreamWriter();
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
                VideoWriter writer = new VideoWriter("/Users/kulkarnm/videos/vid_001.avi", VideoWriter.fourcc('D', 'I', 'V', 'X'), 30, new Size(videoCapture.get(Videoio.CAP_PROP_FRAME_WIDTH), videoCapture.get(Videoio.CAP_PROP_FRAME_HEIGHT)));
                while (runnable) {
                    if (videoCapture.grab()) {
                        try {
                            videoCapture.retrieve(frame);
                            Imgcodecs.imencode(".bmp", frame, mem);
                            Image im = ImageIO.read(new ByteArrayInputStream(mem.toArray()));

                            BufferedImage buff = (BufferedImage) im;
                            Graphics g = CamPanel.this.getGraphics();
                            boolean isImageDrawn = ImageDrawer.drawScaledImage(buff,CamPanel.this,g);
                            writer.write(frame);
                            if (isImageDrawn) {
                                if (runnable == false) {
                                    System.out.println("Going to wait()");
                                    this.wait();
                                }
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
