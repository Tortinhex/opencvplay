package com.omega.controll;

import java.awt.image.BufferedImage;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;

public class Matriz {
	Mat matriz;
	MatOfByte matrizByte, MatrizByteAux;
	String extensionFile;
	
	/**
	 * Construtor vazio
	 * @author Danilo Dorotheu
	 */
	public Matriz(){
		
	}
	/**
	 * Construtor parametrizado
	 * @param matriz
	 * @param extensionFile
	 */
	public Matriz(Mat matriz, String extensionFile){
		setMatriz(matriz, extensionFile);
	}
	/**
	 * Seta a matriz e cria nova instancia de MatOfByte
	 * @param matriz
	 * @param extensionFile
	 */
	public void setMatriz(Mat matriz, String extensionFile) {
		this.matriz = matriz;
		this.extensionFile = extensionFile;
		matrizByte = new MatOfByte();
	}
	
}
