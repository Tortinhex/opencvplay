/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencv.teste.matriz;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

/**
 * @author thiago
 */
public class TesteMetodos {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		Mat imagem = Highgui.imread("/home/danilo/Imagens/darth.jpg");
		Mat dest = imagem.clone();

		Imgproc.medianBlur(imagem, dest, 15);
		
		Highgui.imwrite("/home/danilo/Imagens/darth2.jpg", dest);
		//imprime("DarthVader", m.dump());
		
		
		Mat matriz = new Mat(5, 5, CvType.CV_8UC1, new Scalar(0));
		imprime("Imprimir matriz", matriz.dump());

		Mat linha2Matriz = matriz.row(2);
		linha2Matriz.setTo(new Scalar(1));
		Mat coluna5Matriz = matriz.col(2);
		coluna5Matriz.setTo(new Scalar(2));

		imprime("Escalar na linha e coluna da matriz", matriz.dump());
		imprime("Clone da matriz", matriz.clone().dump());
		imprime("Linhas da matriz", matriz.rows());
		imprime("Colunas da matriz", matriz.cols());
		imprime("Dimensão da matriz", matriz.dims());

		imprime("Total de elementos da matriz", matriz.total());
		imprime("Está vazia?", matriz.empty());
		imprime("Primeira linha da matriz", matriz.row(0).dump());
		imprime("Primeira coluna da matriz", matriz.col(0).dump());
		imprime("Primeira diagonal da matriz", matriz.diag(0).dump());
		imprime("Primeira à terceira linha da matriz", matriz.rowRange(0, 3).dump());
		imprime("Primeira à terceira coluna da matriz", matriz.colRange(0, 3).dump());
	}

	public static void imprime(String titulo, Object resultado) {
		System.out.println(titulo + ":\n" + resultado + "\n");
	}

}
