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
	private String filename;
	
	public PictureReading() {
		filename = "";
	}
	
	public PictureReading(String filename) {
		this.filename = filename;
	}
	
	public void readFile() throws IOException {
		if (filename.length() != 0) {
			readFile(filename);
		}
	}
	
	public void readFile(String filename) throws IOException {
		BufferedReader file = null;
		
		int width = 0;
		int height = 0;
		String commentaire = "";
		int max_value = 0;
		
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
					return;
				}
			}
			
			// Commentaire
			line = file.readLine();
			if (line != null) {
				if (line.charAt(0) != '#') {
					System.out.println("Mauvais format d'image");
					file.close();
					return;
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
					return;
				}
			}
			
			// Valeur maximal
			line = file.readLine();
			if (line != null) {
				max_value = Integer.parseInt(line);
			}
			
			// Création de l'image
			
			
			// Lecture de l'image
			int i = 0;
			int j = 0;
			line = file.readLine();
			while (line != null) {
				StringTokenizer tokenizer = new StringTokenizer(line, " ");
				
				while (tokenizer.hasMoreTokens()) {
					int pixel = Integer.parseInt(tokenizer.nextToken());
					
					// Modification du pixel sur l'image
					
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
	}
}
