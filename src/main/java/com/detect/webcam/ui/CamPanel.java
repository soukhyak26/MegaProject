package com.detect.webcam.ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
//The class representing an Empty Panel aligned at top iin CamFrame
//Which is used to display the Video

public class CamPanel extends JPanel {
    public void CamPanel() {
        Border blackLine = BorderFactory.createLineBorder(Color.black);
        this.setBorder(blackLine);
    }
}
