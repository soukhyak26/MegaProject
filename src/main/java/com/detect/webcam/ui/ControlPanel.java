package com.detect.webcam.ui;

import com.detect.webcam.capture.TimerActionListener;
import com.detect.webcam.capture.VideoRecorder;
import com.detect.webcam.execute.ArduinoExecutor;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel {
    private final VideoRecorder videoRecorder;
    private final ArduinoExecutor arduinoExecutor;
    private JButton start;
    private JButton stop;
    private Timer timer;

    public ControlPanel(CamPanel camPanel) {
        this.videoRecorder = new VideoRecorder();
        this.arduinoExecutor = new ArduinoExecutor();
        setLayout();
        initializeAction(camPanel);
    }

    private void setLayout(){
        this.setLayout(new GridBagLayout());
        Border blackline = BorderFactory.createLineBorder(Color.black);
        this.setBorder(blackline);
        this.start = new JButton("Start");
        this.start.setBackground(Color.yellow);
        this.add(start);
        this.stop = new JButton("Stop");
        this.stop.setBackground(Color.green);
        this.add(stop);
    }

    private void initializeAction(CamPanel camPanel) {
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer = new Timer(10000, new TimerActionListener(videoRecorder, arduinoExecutor, camPanel));
                timer.start();
            }
        });
        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("****action being performed after " + System.currentTimeMillis());
                stopFrame();
            }
        });
    }

    public void stopFrame() {
        videoRecorder.isRunnable = false;
        this.videoRecorder.release();
        timer.stop();
    }
}
