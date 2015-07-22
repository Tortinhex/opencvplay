/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package opencv.teste.cam;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.highgui.Highgui;

/**
 *
 * @author OldSpice
 */
public class MatrizImagemTemp {

    Mat matriz;
    MatOfByte matrizBytes, matrizBytesAux;
    String extensaoArquivo;

    public MatrizImagemTemp() {
    }

    public MatrizImagemTemp(Mat matriz, String extensaoArquivo) {
        this.matriz = matriz;
        this.extensaoArquivo = extensaoArquivo;
    }

    public void setMatrix(Mat matriz, String extensaoArquivo) {
        this.matriz = matriz;
        this.extensaoArquivo = extensaoArquivo;
        matrizBytes = new MatOfByte();
    }

    public BufferedImage getBufferedImage() {
        Highgui.imencode(extensaoArquivo, matriz, matrizBytes);
        byte[] arranjoBytes = matrizBytes.toArray();
        BufferedImage imagemTemp = null;
        
        try {
            InputStream fluxoEntrada = new ByteArrayInputStream(arranjoBytes);
            imagemTemp = ImageIO.read(fluxoEntrada);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        Image imagemNova = imagemTemp.getScaledInstance(625, 445, java.awt.Image.SCALE_SMOOTH);
        Image imagemAux = new ImageIcon(imagemNova).getImage();
        imagemTemp = new BufferedImage(imagemAux.getWidth(null), imagemAux.getHeight(null), BufferedImage.SCALE_SMOOTH);
        imagemTemp.getGraphics().drawImage(imagemAux, 0, 0, null);
        
        return imagemTemp;
    }
}
