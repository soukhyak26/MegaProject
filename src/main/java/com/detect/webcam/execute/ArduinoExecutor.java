package com.detect.webcam.execute;

import com.fazecast.jSerialComm.SerialPort;

import java.io.IOException;

public class ArduinoExecutor {
    public static void main(String[] args) throws IOException, InterruptedException {
        int BaudRate = 9600;
        int DataBits = 8;
        int StopBits = SerialPort.ONE_STOP_BIT;
        int Parity = SerialPort.NO_PARITY;

        SerialPort MySerialPort = SerialPort.getCommPort("COM5"); //AvailablePorts[0];

        MySerialPort.setComPortParameters(BaudRate, DataBits, StopBits, Parity);//Sets all serial port parameters at one time
        MySerialPort.openPort(); //open the port
        Thread.sleep(5000);//Delay added to so you can see the Arduino getting Resetted


        if (MySerialPort.isOpen())
            System.out.println("\n" + MySerialPort.getSystemPortName() + "  is Open ");
        else
            System.out.println(" Port not open ");
        Thread.sleep(2000); //Delay introduced because when the SerialPort is opened ,Arduino gets resetted
        // Time for the code in Arduino to rerun after Reset

        try {
            byte[] WriteByte = new byte[1];
            WriteByte[0] = 65; //send A
            int bytesTxed = 0;
            bytesTxed = MySerialPort.writeBytes(WriteByte, 1);
            System.out.print(" Bytes Transmitted -> " + bytesTxed);

        } catch (Exception e) {
            e.printStackTrace();
        }

        MySerialPort.closePort(); //Close the port

        if (MySerialPort.isOpen())
            System.out.println(MySerialPort.getSystemPortName() + " is Open ");
        else
            System.out.println("\n Port closed ");
    }
}
