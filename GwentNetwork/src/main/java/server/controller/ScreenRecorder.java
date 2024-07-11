//package server.controller;
//
//import javafx.embed.swing.SwingFXUtils;
//import javafx.scene.image.Image;
//import message.enums.card.Card;
//import org.bytedeco.javacv.FFmpegFrameRecorder;
//import org.bytedeco.javacv.Frame;
//import org.bytedeco.javacv.Java2DFrameConverter;
//
//import java.awt.image.BufferedImage;
//import java.util.ArrayList;
//
//public class ScreenRecorder {
//    public static void main(String[] args) {
//        String outputFilename = "output.mp4";
//        int frameRate = 1; // Frame rate in frames per second
//        int width = 640; // Video width
//        int height = 480; // Video height
//
//        // Create an ArrayList of javafx.scene.image.Image
//        ArrayList<Image> imageList = new ArrayList<>();
//
//        // Add some Image objects to the ArrayList
//        // In practice, you would load these from files or generate them dynamically
//        try {
//            Image img1 = Card.CelaenoHarpy.getImage();
//            Image img2 = Card.CahirMawrDyffrynAepCeallach.getImage();
//            Image img3 = Card.CelaenoHarpy.getImage();
//            imageList.add(img1);
//            imageList.add(img2);
//            imageList.add(img3);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return;
//        }
//
//        // Convert the ArrayList to an array
//        Image[] imageArray = new Image[imageList.size()];
//        imageArray = imageList.toArray(imageArray);
//
//        // Create a frame recorder
//        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(outputFilename, width, height);
//        recorder.setFrameRate(frameRate);
//
//        // Start the recorder
//        try {
//            recorder.start();
//            Java2DFrameConverter converter = new Java2DFrameConverter();
//
//            for (Image image : imageArray) {
//                // Convert javafx.scene.image.Image to BufferedImage
//                BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
//
//                // Resize image if necessary
//                if (bufferedImage.getWidth() != width || bufferedImage.getHeight() != height) {
//                    bufferedImage = resizeImage(bufferedImage, width, height);
//                }
//
//                // Convert BufferedImage to Frame and record it
//                Frame frame = converter.convert(bufferedImage);
//                recorder.record(frame);
//            }
//
//            // Stop the recorder
//            recorder.stop();
//            recorder.release();
//            System.out.println("Video created successfully!");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
//        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_3BYTE_BGR);
//        resizedImage.getGraphics().drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
//        return resizedImage;
//    }
//}
