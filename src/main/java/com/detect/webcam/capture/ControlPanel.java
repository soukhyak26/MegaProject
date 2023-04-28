package com.detect.webcam.capture;

import com.detect.webcam.execute.ArduinoExecutor;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class ControlPanel extends JPanel {
    private VideoRecorder videoRecorder=null;
    private ArduinoExecutor arduinoExecutor;
    private JButton start;
    private JButton stop;
    private Timer timer;
    public ControlPanel(CamPanel camPanel){
        this.videoRecorder = new VideoRecorder();
        this.arduinoExecutor = new ArduinoExecutor();
        setLayout();
        initializeAction(camPanel);
    }

    private void initializeAction(CamPanel camPanel){
        start.addActionListener(e -> {
            this.timer = new Timer(15000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("****action being performed after " + System.currentTimeMillis());
                    startFrame(camPanel);
                }
            });
            try {
                this.arduinoExecutor.setUp();
            }catch(InterruptedException ex){
                ex.printStackTrace();
            }
            timer.start();
        });
        stop.addActionListener(e -> stopFrame());
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
    public void startFrame(CamPanel camPanel){
        videoRecorder.initialize();
        long startTime = System.currentTimeMillis();

        long endTime =0;
        long timeLapsed = 0;
        java.util.List<Integer> frameWiseHumanCount = new ArrayList<>();

        while(timeLapsed < 3000){
            videoRecorder.isRunnable=true;
            Integer humanCount = videoRecorder.recordVideo(camPanel);
            frameWiseHumanCount.add(humanCount);
            endTime=System.currentTimeMillis();
            timeLapsed= endTime-startTime;
        }
        Integer majorityHumanCount=computeMajorityHumanCount(frameWiseHumanCount);
        arduinoExecutor.communicateWithArduino(majorityHumanCount);
        System.out.println("****Majority human count found in the video is *****" + majorityHumanCount);
        videoRecorder.isRunnable=false;

    }
    public void stopFrame() {
        videoRecorder.isRunnable=false;
        this.videoRecorder.release();
        this.timer.stop();
    }

    public int computeMajorityHumanCount(java.util.List<Integer> frameWiseHumanCount){
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
        for(int i = 1; i < frameWiseHumanCount.size(); i++)
        {
            if(hm.containsKey(frameWiseHumanCount.get(i)))
            {
                int count = hm.get(frameWiseHumanCount.get(i)) + 1;
                if (count >= frameWiseHumanCount.size() / 2) {
                    return frameWiseHumanCount.get(i);
                } else {
                    hm.put(frameWiseHumanCount.get(i), count);
                }
            }
            else {
                hm.put(frameWiseHumanCount.get(i), 1);
            }
        }
        return -1;

    }

}
