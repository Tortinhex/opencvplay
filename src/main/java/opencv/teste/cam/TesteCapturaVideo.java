/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencv.teste.cam;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.VideoCapture;

/**
 *
 * @author thiago
 */
public class TesteCapturaVideo extends JPanel {
    private BufferedImage imagemCamTemp;

    public TesteCapturaVideo() {
        super();
    }

    private void setimage(BufferedImage imagemNova) {
        this.imagemCamTemp = imagemNova;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.imagemCamTemp == null) return;
        g.drawImage(this.imagemCamTemp, 0, 0, this.imagemCamTemp.getWidth(), this.imagemCamTemp.getHeight(), null);
    }

    public static void main(String args[]) throws Exception {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        TesteCapturaVideo TesteCapturaVideo = new TesteCapturaVideo();

        JFrame janela = new JFrame("OpenCV - Captura de Video");
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setContentPane(TesteCapturaVideo);
        janela.setVisible(true);

        Mat imagemCam = new Mat();
        MatrizImagemTemp matrizImagemTemp = new MatrizImagemTemp();
        VideoCapture capturaVideo = new VideoCapture(0);
        
        if (capturaVideo.open(0)) {

            while (true) {
                capturaVideo.read(imagemCam);
                
                if (!imagemCam.empty()) {
                    janela.setSize(imagemCam.width(), imagemCam.height());
                    matrizImagemTemp.setMatrix(imagemCam, ".jpg");
                    TesteCapturaVideo.setimage(matrizImagemTemp.getBufferedImage());
                    TesteCapturaVideo.repaint();
                } else {
                    System.out.println("Problemas com a imagem da webcam");
                    break;
                }

            }

        }
        capturaVideo.release();
    }  
}
