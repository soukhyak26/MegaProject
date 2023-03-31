package com.detect.webcam;

import nu.pattern.OpenCV;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.VideoWriter;
import org.opencv.videoio.Videoio;


public class VideoStreamCapturer {
    public static void main(String[] args) {
        VideoStreamCapturer capturer = new VideoStreamCapturer();
        capturer.writeVideo();
    }
    public VideoStreamCapturer() {

    }
    private void writeVideo() {
        OpenCV.loadLocally();
        VideoCapture videoCapture = new VideoCapture();
        videoCapture.open(0);
        if( videoCapture.isOpened()) {
            Mat frame = new Mat();
            videoCapture.read(frame);
            VideoWriter writer = new VideoWriter("Users//kulkarnm//videos//vid_001.avi", VideoWriter.fourcc('M', 'J', 'P', 'G'), 30, new Size(videoCapture.get(Videoio.CAP_PROP_FRAME_WIDTH), videoCapture.get(Videoio.CAP_PROP_FRAME_HEIGHT)));
            while (videoCapture.read(frame)) {
                for(int i=0;i<256;i++) {
                    writer.write(frame);
                }
            }
            videoCapture.release(); // release device
            writer.release();
        }

    }

}
