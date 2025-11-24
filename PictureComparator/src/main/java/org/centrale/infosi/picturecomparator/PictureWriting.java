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
    
    /**
     * Constructeur d'un écrivain à partir d'une photo
     * @param pic La photo
     */
    public PictureWriting(Picture pic){
        this.pic = pic;
    }
    
    /**
     * Constructeur par défaut
     * ajoute une image par défaut
     */
    public PictureWriting(){
        pic = new Picture();
    }
    
    // Écriture d'une image en PGM
    
    /**
     * Méthode pour écrire un fichier correspondant à l'image en attribut
     * @param filename le nom du fichier
     * @throws IOException Message d'erreur en cas de problème
     */
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
    
    // Guetteurs et Setteurs

    /**
     * Le Guetteur
     * @return l'image
     */
    public Picture getPic() {
        return pic;
    }

    /**
     * Le setteur
     * @param pic la nouvelle image
     */
    public void setPic(Picture pic) {
        this.pic = pic;
    }
}
