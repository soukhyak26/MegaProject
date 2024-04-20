package com.detect.webcam;

import com.detect.webcam.ui.CamFrame;
import nu.pattern.OpenCV;

import java.awt.*;

public class Application {
    public static void main(String[] args) {
        OpenCV.loadLocally();
        //System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        EventQueue.invokeLater(() -> {
            try {
                Dimension ss = Toolkit.getDefaultToolkit ().getScreenSize ();
                Dimension frameSize = new Dimension ( 800, 600 );
                CamFrame camFrame = new CamFrame();
                camFrame.setBounds ( ss.width / 2 - frameSize.width / 2,
                        ss.height / 2 - frameSize.height / 2,
                        frameSize.width, frameSize.height);
                camFrame.initUI();
                camFrame.setVisible(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
}
