package com.detect.webcam.ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
//The Class representing top frame of the User Interface
//The frame encompasses two panels
//Cam Panel for displaying the video
//Control Panel containing buttons 'Start' and 'Stop" to control the process
public class CamFrame extends JFrame {
    CamPanel camPanel;
    //ControlPanel controlPanel;

    public CamFrame() {
        setTitle("Video Capture");
        this.setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public JPanel createControlPanel() {
        JPanel controlPanel = new JPanel(new GridBagLayout());
        Border blackline = BorderFactory.createLineBorder(Color.black);
        controlPanel.setBorder(blackline);
        JButton start = new JButton("Start");
        start.setBackground(Color.yellow);
        controlPanel.add(start);
        start.addActionListener(e -> camPanel.startFrame());

        JButton stop = new JButton("Stop");
        stop.setBackground(Color.green);
        controlPanel.add(stop);
        stop.addActionListener(e -> camPanel.stopFrame());
        return controlPanel;
    }

    public void initUI() {
        camPanel = new CamPanel();
        JPanel controlPane = createControlPanel();
        JComponent[] components = new JComponent[2];
        components[0] = camPanel;
        components[1] = controlPane;
        createLayout(components);

    }

    //A Private Method to create GridBag layout
    //to place to Panels in such a way that first panel gets on top
    // Second Panel below the first panel, both being vertically aligned
    //Upper(Cam Panel) occupies 70% of the frame, Lower(Control) Panel occupies 30% of the frame
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
