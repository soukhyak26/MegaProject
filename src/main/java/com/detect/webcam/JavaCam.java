package com.detect.webcam;

import nu.pattern.OpenCV;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

public class JavaCam extends JFrame {
    private DaemonThread myThread = null;
    private VideoCapture webSource = null;
    int count = 0;
    Mat frame = new Mat();
    MatOfByte mem = new MatOfByte();
    JPanel jPanel1 = new JPanel();
    JButton start = new JButton("Start");
    JButton stop = new JButton("Stop");
    public JavaCam(){
        jPanel1.setBounds(10,10,320,280);
        this.add(jPanel1);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startFrame();
            }
        });

        jPanel1.add(start);
        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopFrame();
            }
        });
        jPanel1.add(stop);


    }
    ////////////////////////////////////////////////////////////
    /// start button
    public void startFrame() {
        webSource =new VideoCapture(0);
        myThread =new DaemonThread();

        Thread t = new Thread(myThread);
        t.setDaemon(true);
        myThread.runnable =true;
        t.start();
        start.setEnabled(false);  //start button
        stop.setEnabled(true);  // stop button
    }
    //////////////////////////////////////////////////////
/// stop button
    public void stopFrame() {
        myThread.runnable = false;
        stop.setEnabled(false);
        start.setEnabled(true);

        webSource.release();
    }

    class DaemonThread implements Runnable{
        protected volatile boolean runnable = false;

        @Override
        public void run() {
            synchronized(this)
            {
                while(runnable)
                {
                    if(webSource.grab())
                    {
                        try
                        {
                            webSource.retrieve(frame);
                            Imgcodecs.imencode(".bmp", frame, mem);
                            Image im = ImageIO.read(new ByteArrayInputStream(mem.toArray()));

                            BufferedImage buff = (BufferedImage) im;
                            Graphics g=jPanel1.getGraphics();

                            if (g.drawImage(buff, 0, 0, getWidth(), getHeight() -150 , 0, 0, buff.getWidth(), buff.getHeight(), null))

                                if(runnable == false)
                                {
                                    System.out.println("Going to wait()");
                                    this.wait();
                                }
                        }
                        catch(Exception ex)
                        {
                            System.out.println("Error");
                        }
                    }
                }
            }
        }

    }

    public static void main(String[] args){
        //System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        OpenCV.loadLocally();
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new JavaCam().setVisible(true);
            }
        });
    }

}