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
	
	
	Picture greyToBlackAndWhite(Picture pic, int seuil) {
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
	
	
}
