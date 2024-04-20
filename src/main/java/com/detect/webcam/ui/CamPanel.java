package com.detect.webcam.ui;

import com.detect.webcam.capture.ImageDrawer;
import com.detect.webcam.detect.HumanDetector;
import com.detect.webcam.execute.ArduinoExecutor;
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
//The class representing an Empty Panel aligned at top iin CamFrame
//Which is used to display the Video

public class CamPanel extends JPanel {
    private final Mat frame = new Mat();
    private final MatOfByte mem = new MatOfByte();
    // private VideoCapture videoCapture=null;
    private DaemonThread myThread = null;
    // private VideoRecorder videoRecorder;
    private HumanDetector humanDetector;
    private VideoCapture videoCapture = null;
    private VideoWriter writer;
    private ArduinoExecutor arduinoExecutor;
    private int finalHumanCount;

    public void CamPanel() {
        Border blackLine = BorderFactory.createLineBorder(Color.black);
        this.setBorder(blackLine);
    }

    public void startFrame() {
        //ideoCapture = new VideoCapture(0);
        //videoRecorder = new VideoRecorder();
        this.humanDetector = new HumanDetector();
        this.arduinoExecutor = new ArduinoExecutor();
        myThread = new CamPanel.DaemonThread();
        initialize();
        try {
            this.arduinoExecutor.setUp();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        Thread t = new Thread(myThread);
        t.setDaemon(true);
        myThread.runnable = true;
        t.start();
    }

    //The method to initialize Video Capture Process and Video recorder Process
    //VideoWriter will need path where videos will be stored, image encoding(MPEG), frames per second, frame size etc.
    public void initialize() {
        videoCapture = new VideoCapture(0);
        Size size = new Size(videoCapture.get(Videoio.CAP_PROP_FRAME_WIDTH), videoCapture.get(Videoio.CAP_PROP_FRAME_HEIGHT));
        int fourcc = VideoWriter.fourcc('M', 'J', 'P', 'G');
        writer = new VideoWriter("E:\\videos\\video_" + System.currentTimeMillis() + ".avi", fourcc, 10, size, true);
    }

    public void stopFrame() {
        myThread.runnable = false;
        videoCapture.release();
        arduinoExecutor.communicateWithArduino(0);
        this.arduinoExecutor.closeConnection();
    }

    public void setFinalHumanCount(int finalHumanCount) {
        this.finalHumanCount = finalHumanCount;
    }

    class DaemonThread implements Runnable {
        protected volatile boolean runnable = false;

        @Override
        public void run() {
            synchronized (this) {
                Integer humanCount = 0;
                while (runnable) {
                    if (videoCapture.grab()) {
                        try {
                            videoCapture.retrieve(frame);
                            if (frame.empty())
                                humanCount = 0;
                            humanCount = humanDetector.detectAndDisplay(frame);
                            System.out.println("humanCount: " + humanCount);

                            Imgcodecs.imencode(".bmp", frame, mem);
                            Image im = ImageIO.read(new ByteArrayInputStream(mem.toArray()));
                            BufferedImage buff = (BufferedImage) im;
                            Graphics g = CamPanel.this.getGraphics();
                            boolean isImageDrawn = ImageDrawer.drawScaledImage(buff, CamPanel.this, g);
                            writer.write(frame);
                            if (isImageDrawn) {
                                if (runnable == false) {
                                    System.out.println("Going to wait()");
                                    this.wait();
                                }
                            }
                            System.out.println("Done capturing and writing");

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    CamPanel.this.setFinalHumanCount(humanCount);
                    arduinoExecutor.communicateWithArduino(humanCount);

                }
            }
        }
    }

}
