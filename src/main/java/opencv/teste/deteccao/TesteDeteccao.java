/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package opencv.teste.deteccao;

import opencv.teste.cam.MatrizImagemTemp;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.VideoCapture;
import org.opencv.objdetect.CascadeClassifier;

/**
 *
 * @author OldSpice
 */
public class TesteDeteccao extends JPanel {

    private BufferedImage imagemCamTemp;

    public TesteDeteccao() {
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

        // PERFIL LBP
        //CascadeClassifier detector = new CascadeClassifier("C:\\Program Files\\opencv\\sources\\data\\lbpcascades\\lbpcascade_profileface.xml");
        // ROSTO LBP
        //CascadeClassifier detector = new CascadeClassifier("C:\\Program Files\\opencv\\sources\\data\\lbpcascades\\lbpcascade_frontalface.xml");
        // PEDESTRE HOG
        //CascadeClassifier detector = new CascadeClassifier("C:\\Program Files\\opencv\\sources\\data\\hogcascades\\hogcascade_pedestrians.xml");
        // OLHO HAAR
        //CascadeClassifier detector = new CascadeClassifier("C:\\Program Files\\opencv\\sources\\data\\haarcascades\\haarcascade_eye.xml");
        // PERFIL HAAR
        CascadeClassifier detector = new CascadeClassifier("C:\\Program Files\\opencv\\sources\\data\\haarcascades\\haarcascade_profileface.xml");

        TesteDeteccao testeWebCam = new TesteDeteccao();

        JFrame janela = new JFrame("OpenCV - Detecção");
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setContentPane(testeWebCam);
        janela.setVisible(true);

        Mat imagemCam = new Mat();
        MatrizImagemTemp matrizImagemTemp = new MatrizImagemTemp();
        VideoCapture capturaVideo = new VideoCapture(0);
        
        if (capturaVideo.open(0)) {

            while (true) {
                capturaVideo.read(imagemCam);
                
                if (!imagemCam.empty()) {
                    janela.setSize(imagemCam.width(), imagemCam.height());
                    MatOfRect deteccoes = new MatOfRect();
                    detector.detectMultiScale(imagemCam, deteccoes);
                    
                    for (Rect rect : deteccoes.toArray())
                        Core.rectangle(imagemCam, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
                  
                    System.out.println("Alvos detectados: " + deteccoes.toArray().length);
                  
                    if (deteccoes.toArray().length == 0)
                        System.err.println("Alvo não detectado!");

                    matrizImagemTemp.setMatrix(imagemCam, ".jpg");
                    testeWebCam.setimage(matrizImagemTemp.getBufferedImage());
                    testeWebCam.repaint();

                } else {
                    System.out.println("Problema com as imagens da câmera");
                    break;
                }

            }

        }
        capturaVideo.release();
    }

}
