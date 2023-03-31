package com.detect.webcam;

import org.opencv.core.Core;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class CamFrame extends JFrame {
   // JPanel contentPane;
    CamPanel camPanel;

    public CamFrame() {
        setTitle("Video Capture");
        this.setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void initUI(){

        camPanel = new CamPanel();
        JPanel controlPane = createControlPanel();
        JComponent [] components = new JComponent[2];
        components[0] = camPanel;
        components[1] = controlPane;
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

    public static void main(String[] args) {
        //OpenCV.loadLocally();
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        EventQueue.invokeLater(() -> {
            try {
                Dimension ss = Toolkit.getDefaultToolkit ().getScreenSize ();
                Dimension frameSize = new Dimension ( 500, 300 );
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
