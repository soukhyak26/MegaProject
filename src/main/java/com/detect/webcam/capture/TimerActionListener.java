package com.detect.webcam.capture;

import com.detect.webcam.execute.ArduinoExecutor;
import com.detect.webcam.ui.CamPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
//Action Listener which is invoked/called back by a timer after every 'interval', that is provided in Timer constructor
// The actionPerformed method gets called after a specified interval and perform the desired periodic task implemented in the actionPerformed method
public class TimerActionListener implements ActionListener {

    private final ArduinoExecutor arduinoExecutor;
    private final CamPanel camPanel;
    private VideoRecorder videoRecorder = null;

    //ActionListener Constructor, initializes connection with Arduino through FazeCast lib( through setUp method)
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


    // A callback method of ActionListener
    // Starts Video Capturing, Human Detection, Video Playback on Cam Panel, Video recording, Returning of Human Detection count per video
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("****action being performed after " + System.currentTimeMillis());
        startProcess(camPanel);
    }

    //A Method does following activities
    //Initialize VideoRecorder
    //For 3 seconds (can be changed to any desired time) capture Video
    //For each frame of a video, add te detected human count to a collection
    // determine majority of the human counts per frame entered in a collection
    // pass the majority human count value to the Arduino Executor's communicateWithArduino method
    //When Human count is greater than 0, Arduino Executor will turn on LED,Els it will turn it off
    public void startProcess(CamPanel camPanel) {
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

    //When a video is captured, it is divided into series of frames/images
    // each image is analysed for human count. The Human count value per frame is added to a collection(List)
    // The list is received as an input to this method
    //The method finds out majority of times a number is repeated in the collection
    //The majority human count is considered to be the valid output of human detection process and this number is returned
    //If no majority is found, then -1 is return,informing the caller that the method could not conclude majority
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
