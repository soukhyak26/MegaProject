package com.detect.webcam.capture;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class CamPanel extends JPanel {
    public void CamPanel() {
        Border blackLine = BorderFactory.createLineBorder(Color.black);
        this.setBorder(blackLine);
    }
}
