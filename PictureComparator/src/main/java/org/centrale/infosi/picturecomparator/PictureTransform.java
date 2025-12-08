/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.infosi.picturecomparator;

/**
 *
 * @author Catherine
 */
public class PictureTransform {
	public PictureTransform() {}
	
	
	public Picture greyToBlackAndWhite(Picture pic, int seuil) {
		Picture p = new Picture(pic);
		
		int width = pic.getWidth();
		int height = pic.getHeight();
		int max_value = pic.getMaxGray();
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (pic.getPixelPrecis(i, j) > seuil) {
					p.setPixelPrecis(i, j, max_value);
				} else {
					p.setPixelPrecis(i, j, 0);
				}
			}
		}
		return p;
	}
	
	public Picture pictureDiff(Picture picA, Picture picB) {
		if (picA.getWidth() != picB.getWidth() || picA.getHeight() != picB.getHeight()) {
			return null;
		}
		
		Picture p = new Picture(picA);
		int width = p.getWidth();
		int height = p.getHeight();
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int pixel = picA.getPixelPrecis(i, j) - picB.getPixelPrecis(i, j);
				
				// Si le niveau de gris du pixel est négatif, on met l'opposé
				if (pixel < 0) {
					p.setPixelPrecis(i, j, -pixel);
				} else {
					p.setPixelPrecis(i, j, pixel);
				}
			}
		}
		return p;
	}
	
	
	
	
}
