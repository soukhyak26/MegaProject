package com.detect.webcam.capture;

import java.awt.*;

//A Utility class to reshape the image to be shown in CamPanel as per the size fo CamPanel
public class ImageDrawer {

    //The method receives image to be shown, panel in which the image is to be show and the graphics handle
    // It first check the size ( width and height) of image
    //it calculates aspect ratio of image as height/width
    //Then it captures height and width of a panel/canvas, in which the image is to be displayed
    //It calculates the aspect ration of the canvas/panel
    //In case image width and height is less than canvas width and height then
    //It resizes the dimensions of the image to fit to the canvas
    // After fitting the image ot the canvas the reshaped image is drawn onto the canvas using graphics object.
    //Thus when user changes the window size of the UI, which also changes the Cam Panel size,
    // the image is automatically resized to fit to the resized canvas
    public static boolean drawScaledImage(Image image, Component canvas, Graphics g) {
        int imgWidth = image.getWidth(null);
        int imgHeight = image.getHeight(null);

        double imgAspect = (double) imgHeight / imgWidth;

        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();

        double canvasAspect = (double) canvasHeight / canvasWidth;

        int x1 = 0; // top left X position
        int y1 = 0; // top left Y position
        int x2 = 0; // bottom right X position
        int y2 = 0; // bottom right Y position

        if (imgWidth < canvasWidth && imgHeight < canvasHeight) {
            // the image is smaller than the canvas
            x1 = (canvasWidth - imgWidth)  / 2;
            y1 = (canvasHeight - imgHeight) / 2;
            x2 = imgWidth + x1;
            y2 = imgHeight + y1;

        } else {
            if (canvasAspect > imgAspect) {
                y1 = canvasHeight;
                // keep image aspect ratio
                canvasHeight = (int) (canvasWidth * imgAspect);
                y1 = (y1 - canvasHeight) / 2;
            } else {
                x1 = canvasWidth;
                // keep image aspect ratio
                canvasWidth = (int) (canvasHeight / imgAspect);
                x1 = (x1 - canvasWidth) / 2;
            }
            x2 = canvasWidth + x1;
            y2 = canvasHeight + y1;
        }

        return g.drawImage(image, x1, y1, x2, y2, 0, 0, imgWidth, imgHeight, null);
    }
}
