package com.detect.webcam.ui;

import com.detect.webcam.capture.TimerActionListener;
import com.detect.webcam.capture.VideoRecorder;
import com.detect.webcam.execute.ArduinoExecutor;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//The Class representing lower panel in the UI, containing 'Start' and 'Stop' buttons
//contained in it
public class ControlPanel extends JPanel {
    private final VideoRecorder videoRecorder;
    private final ArduinoExecutor arduinoExecutor;
    private JButton start;
    private JButton stop;
    private Timer timer;

    //Sets Gridbag Layout
    public ControlPanel(CamPanel camPanel) {
        this.videoRecorder = new VideoRecorder();
        this.arduinoExecutor = new ArduinoExecutor();
        setLayout();
        initializeAction(camPanel);
    }

    //A method to set Layout
    // as gridbag layout,add two buttons 'start' and 'stop'
    private void setLayout() {
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

    //A Method to assign ActionListeners to both the buttons
    //Start Button has action Listener which is responsible for triggering a Timer
    //The Timer once starts, triggers its own action listener 'TimerActionListener'
    //The TimerActionListener is responsible for running for specified time, capture video, detect humans, turn ON/OFF LED and then stop
    //Stop Button has actionListener which is responsible for stopping the Timer, releasing resources
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
                System.out.println("Stopping Process");
                stopProcess();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    //The method to release resources
    //called from ActionListener of Stop button
    //it stops video capturing
    //then stops timer, release video handle and arduino connection
    public void stopProcess() {
        videoRecorder.isRunnable = false;
        timer.stop();

        this.videoRecorder.release();
        this.arduinoExecutor.closeConnection();
    }
}
