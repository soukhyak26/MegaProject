package com.detect.webcam.execute;

import com.detect.webcam.capture.VideoRecorder;
import com.fazecast.jSerialComm.SerialPort;


public class ArduinoExecutor {
    private static SerialPort MySerialPort;

    /*public static void main(String[] args) throws IOException, InterruptedException {
        ArduinoExecutor arduinoExecutor = new ArduinoExecutor();
        arduinoExecutor.setUp();
        arduinoExecutor.communicateWithArduino();
    }*/

    public void setUp() throws InterruptedException{
        int BaudRate = 9600;
        int DataBits = 8;
        int StopBits = SerialPort.ONE_STOP_BIT;
        int Parity = SerialPort.NO_PARITY;

        this.MySerialPort = SerialPort.getCommPort("COM6"); //AvailablePorts[0];

        MySerialPort.setComPortParameters(BaudRate, DataBits, StopBits, Parity);//Sets all serial port parameters at one time
        MySerialPort.openPort(); //open the port
        Thread.sleep(5000);//Delay added to so you can see the Arduino getting Resetted


        if (MySerialPort.isOpen())
            System.out.println("\n" + MySerialPort.getSystemPortName() + "  is Open ");
        else
            System.out.println(" Port not open ");
        Thread.sleep(2000); //Delay introduced because when the SerialPort is opened ,Arduino gets resetted
        // Time for the code in Arduino to rerun after Reset

    }
    public static void communicateWithArduino(){
        try {
            byte[] WriteByte = new byte[1];
            if(VideoRecorder.humanCount>0) {
                WriteByte[0] = 65; //send A
            }else{
                WriteByte[0] = 20;
            }
            int bytesTxed = 0;
            bytesTxed = MySerialPort.writeBytes(WriteByte, 1);
            System.out.print(" Bytes Transmitted -> " + bytesTxed);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //MySerialPort.closePort(); //Close the port

        if (MySerialPort.isOpen())
            System.out.println(MySerialPort.getSystemPortName() + " is Open ");
        else
            System.out.println("\n Port closed ");
    }
}
