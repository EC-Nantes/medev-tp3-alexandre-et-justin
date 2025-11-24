/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.centrale.infosi.picturecomparator;

/**
 * test de la méthode d'écriture
 * @author Catherine
 */
public class PictureComparator {

    /**
     * Main
     * @param args Main
     */
    public static void main(String[] args) {
        // Création d'une image 10x10 avec un dégradé simple
        Picture pic = new Picture(10, 10);

        for (int i = 0; i < pic.getHeight(); i++) {
            for (int j = 0; j < pic.getWidth(); j++) {
                // Dégradé horizontal : valeur proportionnelle à la colonne
                int value = (j * 255) / pic.getWidth();
                pic.setPixelPrecis(i, j, value);
            }
        }

        // Utilisation de PictureWriting pour écrire l'image
        PictureWriting Ecrivain = new PictureWriting(pic);
        try {
            Ecrivain.ecrirePGM("test.pgm");
            System.out.println("Image écrite dans test.pgm !");
        } catch (Exception e) {
            System.err.println("Erreur lors de l'écriture : " + e.getMessage());
        }

    }
}
