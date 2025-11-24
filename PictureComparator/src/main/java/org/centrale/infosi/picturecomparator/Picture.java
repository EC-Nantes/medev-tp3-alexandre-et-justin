/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.infosi.picturecomparator;

/**
 * Classe représentant une image
 * @author jujus
 */
public class Picture {
    private int width;
    private int height;
    private int maxGray;
    private int[][] pixels;
    private String commentaire;
    
    // Constructeurs
    
    /**
     * Constructeur d'une image noire à partir de ses dimensions
     * @param width Largeur
     * @param height Hauteur
     */
    public Picture(int width, int height) {
        this.width = width;
        this.height = height;
        this.maxGray = 255;
        this.pixels = new int[height][width];
    }

    /**
     * Constructeur par défaut
     */
    public Picture() {
        this.width = 50;
        this.height = 50;
        this.maxGray = 255;
        this.pixels = new int[height][width];
    }
    
    /**
     * Constructeur d'une image à partir des dimensions et de la couleur de gris max
     * @param width largeur
     * @param height hauteur
     * @param maxGray max de gris
     */
    public Picture(int width, int height, int maxGray) {
        this.width = width;
        this.height = height;
        this.maxGray = maxGray;
        this.pixels = new int[height][width];
    }

    /**
     * Constructeur quasi-complet de l'image
     * @param width largeur
     * @param height hauteur
     * @param maxGray niveau de gris
     * @param com commentaire
     */
    public Picture(int width, int height, int maxGray, String com) {
        this.width = width;
        this.height = height;
        this.maxGray = maxGray;
        this.pixels = new int[height][width];
        this.commentaire = com;
    }

    /**
     * Constructeur de copie
     * @param pic image copiée
     */
    public Picture(Picture pic) {
        this.width = pic.width;
        this.height = pic.height;
        this.maxGray = pic.maxGray;
        this.pixels = pic.pixels;
        this.commentaire = pic.commentaire;
    }

    //Méthodes
    
    /**
     * méthode pour modifier un pixel précis d'une image
     * @param row ligne du pixel
     * @param col colonne du pixel
     * @param value valeur du niveau de gris du pixel
     */
    public void setPixelPrecis(int row, int col, int value){
        if (row >= 0 && row < height && col >= 0 && col < width) {
            pixels[row][col] = value;
        }
    }
    
    /**
     * Méthode pour obtenir la valeur d'un pixel spécifique
     * @param row la ligne du pixel
     * @param col la colonne du pixel
     * @return la valeur du pixel en question
     */
    public int getPixelPrecis(int row, int col) {
        if (row >= 0 && row < height && col >= 0 && col < width) {
            return pixels[row][col];
        }
        return -1; // valeur invalide
    }

    
    //Guetteurs et Setteurs

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getMaxGray() {
        return maxGray;
    }

    public void setMaxGray(int maxGray) {
        this.maxGray = maxGray;
    }

    public int[][] getPixels() {
        return pixels;
    }

    public void setPixels(int[][] pixels) {
        this.pixels = pixels;
    }

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
    
}