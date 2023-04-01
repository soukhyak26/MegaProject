package com.detect.webcam;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class CamPanel extends JPanel {
    private DaemonThread myThread = null;
    private VideoRecorder videoRecorder=null;
    public void CamPanel() {
        Border blackLine = BorderFactory.createLineBorder(Color.black);
        this.setBorder(blackLine);
    }

    public void startFrame() {
        this.videoRecorder = new VideoRecorder();
        myThread = new DaemonThread();
        Thread t = new Thread(myThread);
        t.setDaemon(true);
        myThread.runnable = true;
        t.start();
    }

    public void stopFrame() {
        myThread.runnable = false;
        this.videoRecorder.release();
    }

    class DaemonThread implements Runnable {
        protected volatile boolean runnable = false;
        @Override
        public void run() {
            CamPanel.this.videoRecorder.recordVideo(CamPanel.this, this);
        }

        public boolean isRunnable() {
            return runnable;
        }
    }
}
