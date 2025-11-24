/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.infosi.picturecomparator;
import java.io.*;

/**
 * La classe permettant d'écrire 
 * @author jujus
 */
public class PictureWriting {
    private Picture pic;
    
    // Constructeurs
    public PictureWriting(Picture pic){
        this.pic = pic;
    }
    
    public PictureWriting(){
        pic = new Picture();
    }
    
    // Écriture d'une image en PGM
    public void ecrirePGM(String filename) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            pw.println("P2");
            pw.println("# Fichier de l'image : "+ filename +" crée par le programme de Justin et Alexandre !");
            pw.println(pic.getWidth() + " " + pic.getHeight());
            pw.println(pic.getMaxGray());

            int count = 0;
            int[][] pixels = pic.getPixels();

            for (int i = 0; i < pic.getHeight(); i++) {
                for (int j = 0; j < pic.getWidth(); j++) {
                    String val = pixels[i][j] + " ";
                    pw.print(val);
                    count += val.length();

                    // Respect de la contrainte : max 70 caractères par ligne
                    if (count >= 70) {
                        pw.println();
                        count = 0;
                    }
                }
                pw.println();
                count = 0;
            }
        }
    }
}
