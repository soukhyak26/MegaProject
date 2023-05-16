package com.detect.webcam.capture;

import com.detect.webcam.execute.ArduinoExecutor;
import com.detect.webcam.ui.CamPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class TimerActionListener implements ActionListener {

    private final ArduinoExecutor arduinoExecutor;
    private final CamPanel camPanel;
    private VideoRecorder videoRecorder = null;

    public TimerActionListener(VideoRecorder videoRecorder, ArduinoExecutor arduinoExecutor, CamPanel camPanel) {
        this.videoRecorder = videoRecorder;
        this.arduinoExecutor = arduinoExecutor;
        this.camPanel = camPanel;
        try {
            this.arduinoExecutor.setUp();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("****action being performed after " + System.currentTimeMillis());
        startFrame(camPanel);
    }

    public void startFrame(CamPanel camPanel) {
        videoRecorder.initialize();
        long startTime = System.currentTimeMillis();
        long endTime = 0;
        long timeLapsed = 0;
        java.util.List<Integer> frameWiseHumanCount = new ArrayList<>();

        while (timeLapsed < 3000) {
            videoRecorder.isRunnable = true;
            Integer humanCount = videoRecorder.recordVideo(camPanel);
            frameWiseHumanCount.add(humanCount);
            endTime = System.currentTimeMillis();
            timeLapsed = endTime - startTime;
        }
        Integer majorityHumanCount = computeMajorityHumanCount(frameWiseHumanCount);
        arduinoExecutor.communicateWithArduino(majorityHumanCount);
        System.out.println("****Majority human count found in the video is *****" + majorityHumanCount);
        videoRecorder.isRunnable = false;

    }


    public int computeMajorityHumanCount(java.util.List<Integer> frameWiseHumanCount) {
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
        for (int i = 0; i < frameWiseHumanCount.size(); i++) {
            if (hm.containsKey(frameWiseHumanCount.get(i))) {
                int count = hm.get(frameWiseHumanCount.get(i)) + 1;
                if (count >= frameWiseHumanCount.size() / 2) {
                    return frameWiseHumanCount.get(i);
                } else {
                    hm.put(frameWiseHumanCount.get(i), count);
                }
            } else {
                hm.put(frameWiseHumanCount.get(i), 1);
            }
        }
        return -1;

    }
}
