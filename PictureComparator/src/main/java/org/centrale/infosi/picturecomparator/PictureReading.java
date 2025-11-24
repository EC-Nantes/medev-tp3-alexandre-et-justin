/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.infosi.picturecomparator;

import java.io.*;
import java.util.StringTokenizer;

/**
 *
 * @author Catherine
 */
public class PictureReading {
	private String _filename;
	
	/**
	 * Default constructor for reading a picture file
	 */
	public PictureReading() {
		_filename = "";
	}
	
	/**
	 * Constructor for reading a picture file
	 * @param filename The file name of the picture
	 */
	public PictureReading(String filename) {
		_filename = filename;
	}
	
	/**
	 * Read a picture
	 * @return 
	 * @throws IOException
	 */
	public Picture readFile() throws IOException {
		if (_filename.length() != 0) {
			return readFile(_filename);
		} else {
			return new Picture();
		}
	}
	
	/**
	 * Read a picture
	 * @param filename The file name of th picture
	 * @throws IOException
	 * @return Picture
	 */
	public Picture readFile(String filename) throws IOException {
		BufferedReader file = null;
		
		int width = 0;
		int height = 0;
		String commentaire = "";
		int max_value = 0;
		Picture pic = null;
		
		try {
			String line;
			file = new BufferedReader(new FileReader(filename));	

			/* ***** Lecture de l'entête ***** */
			// Type de fichier
			line = file.readLine();
			if (line != null) {
				if (!line.equals("P2")) {
					System.out.println("Mauvais format d'image");
					file.close();
					return pic;
				}
			}
			
			// Commentaire
			line = file.readLine();
			if (line != null) {
				if (line.charAt(0) != '#') {
					System.out.println("Mauvais format d'image");
					file.close();
					return pic;
				} else {
					commentaire = line.substring(1);
				}
			}
			
			// Taille de l'image
			line = file.readLine();
			if (line != null) {
				StringTokenizer tokenizer = new StringTokenizer(line, " ");
				
				if (tokenizer.countTokens() == 2) {
					width = Integer.parseInt(tokenizer.nextToken());
					height = Integer.parseInt(tokenizer.nextToken());
				} else {
					System.out.println("Mauvais format d'image");
					file.close();
					return pic;
				}
			}
			
			// Valeur maximal
			line = file.readLine();
			if (line != null) {
				max_value = Integer.parseInt(line);
			}
			
			// Création de l'image
			pic = new Picture(width, height, max_value, commentaire);
			
			// Lecture de l'image
			int i = 0;
			int j = 0;
			line = file.readLine();
			while (line != null) {
				StringTokenizer tokenizer = new StringTokenizer(line, " ");
				
				while (tokenizer.hasMoreTokens()) {
					int pixel_value = Integer.parseInt(tokenizer.nextToken());
					
					// Modification du pixel sur l'image
					pic.setPixelPrecis(i, j, pixel_value);
					
					// Changement de l'indice
					i++;
					if (i >= width) {
						i = 0;
						j++;
					}
				}
				
				
				line = file.readLine();			
			}
			
		} catch(IOException | NumberFormatException e) {
			System.out.println("Erreur lors de la lecture de la sauvegarde");
		} finally {
			if (file != null) file.close();
		}
		return pic;
	}
}
