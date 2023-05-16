package com.detect.webcam.ui;

import javax.swing.*;
import java.awt.*;

public class CamFrame extends JFrame {
   // JPanel contentPane;
    CamPanel camPanel;
    ControlPanel controlPanel;
    public CamFrame() {
        setTitle("Video Capture");
        this.setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void initUI(){
        camPanel = new CamPanel();
        controlPanel  = new ControlPanel(camPanel);
        JComponent [] components = new JComponent[2];
        components[0] = camPanel;
        components[1] = controlPanel;
        createLayout(components);

    }
    private void createLayout(JComponent... arg) {
        Container pane = getContentPane();
        pane.setLayout(new GridBagLayout());
        GridBagConstraints cns = new GridBagConstraints(); //creating constraint
        cns.gridx = 0;
        cns.gridy = 0;
        cns.weightx = 0.7;
        cns.weighty = 0.7;
        cns.anchor = GridBagConstraints.FIRST_LINE_START;
        cns.fill = GridBagConstraints.BOTH;
        this.setLocationRelativeTo(null); //centering frame
        pane.add(arg[0],cns);
        cns.gridx = 0;
        cns.gridy = 1;
        cns.weightx = 0.3;
        cns.weighty = 0.3;
        cns.anchor = GridBagConstraints.PAGE_START;
        cns.fill = GridBagConstraints.BOTH;
        pane.add(arg[1],cns);
    }
}
