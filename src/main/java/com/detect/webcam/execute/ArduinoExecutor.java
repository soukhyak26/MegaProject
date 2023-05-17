package com.detect.webcam.execute;

import com.fazecast.jSerialComm.SerialPort;

//Class responsible for establishing connection with Arduino Controller Kit
//And send/receive signals to/from Arduino to operate electronic devices connected to Arduino
public class ArduinoExecutor {
    private static SerialPort mySerialPort;

    /*public static void main(String[] args) throws IOException, InterruptedException {
        ArduinoExecutor arduinoExecutor = new ArduinoExecutor();
        arduinoExecutor.setUp();
        arduinoExecutor.communicateWithArduino();
    }*/

    //Method to initialize connection with COM port
    //set com port parameters such as baud rate, data bits,parity bits,stop bits
    //open port
    //wait for 500 ms to open the port
    public void setUp() throws InterruptedException {
        int BaudRate = 9600;
        int DataBits = 8;
        int StopBits = SerialPort.ONE_STOP_BIT;
        int Parity = SerialPort.NO_PARITY;

        mySerialPort = SerialPort.getCommPort("COM6"); //AvailablePorts[0];

        mySerialPort.setComPortParameters(BaudRate, DataBits, StopBits, Parity);//Sets all serial port parameters at one time
        mySerialPort.openPort(); //open the port
        Thread.sleep(5000);//Delay added to so you can see the Arduino getting Resetted


        if (mySerialPort.isOpen()) {
            System.out.println("\n" + mySerialPort.getSystemPortName() + "  is Open ");
        } else {
            System.out.println(" Port not open ");
        }
        Thread.sleep(2000); //Delay introduced because when the SerialPort is opened ,Arduino gets resetted
        // Time for the code in Arduino to rerun after Reset

    }

    //A Method to close connection with Arduino
    public void closeConnection() {
        mySerialPort.closePort();
    }

    // A Method to send appropriate signal to Arduino Kit
    // if detected human count is greater than 0 then send 65
    // else send 20 to Arduino
    //On the Arduino side teh code is implemented which detects the analog number received
    // if it is 65 then it turns the signal to port 7 to HIGH else turn it to LOW
    public void communicateWithArduino(int humanCount) {
        try {
            byte[] WriteByte = new byte[1];
            if (humanCount > 0) {
                WriteByte[0] = 65; //send A
            } else {
                WriteByte[0] = 20;
            }
            int bytesTxed = 0;
            bytesTxed = mySerialPort.writeBytes(WriteByte, 1);
            System.out.print(" Bytes Transmitted -> " + bytesTxed);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //MySerialPort.closePort(); //Close the port

        if (mySerialPort.isOpen())
            System.out.println(mySerialPort.getSystemPortName() + " is Open ");
        else
            System.out.println("\n Port closed ");
    }
}
