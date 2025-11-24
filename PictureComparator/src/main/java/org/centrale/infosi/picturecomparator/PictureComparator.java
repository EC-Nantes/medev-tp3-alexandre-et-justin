/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.centrale.infosi.picturecomparator;

import java.io.IOException;

/**
 *
 * @author Catherine
 */
public class PictureComparator {

    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");
		
		PictureReading reading = new PictureReading("ImagesTestPGM/coins.pgm");
		Picture p = new Picture(reading.readFile());
		
		System.out.println("width:   " + p.getWidth());
		System.out.println("height   " + p.getHeight());
		System.out.println("max gray " + p.getMaxGray());
    }
}
