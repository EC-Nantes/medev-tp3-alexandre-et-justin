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
    
    // Constructeurs
    public Picture(int width, int height) {
        this.width = width;
        this.height = height;
        this.maxGray = 255;
        this.pixels = new int[height][width];
    }

    public Picture() {
        this.width = 50;
        this.height = 50;
        this.maxGray = 255;
        this.pixels = new int[height][width];
    }
    
    public Picture(int width, int height, int maxGray) {
        this.width = width;
        this.height = height;
        this.maxGray = maxGray;
        this.pixels = new int[height][width];
    }

    //Méthodes
    
    public void setPixelPrecis(int row, int col, int value){
        if (row >= 0 && row < height && col >= 0 && col < width) {
            pixels[row][col] = value;
        }
    }
    
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
    
}
