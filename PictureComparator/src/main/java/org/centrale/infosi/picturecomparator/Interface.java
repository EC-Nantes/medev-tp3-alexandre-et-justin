/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.infosi.picturecomparator;

import javax.swing.*;
import java.awt.*;
import java.io.*;



/**
 * Classe permettant de créer l'interface
 * @author jujus
 */
public class Interface extends JFrame {
    private Picture currentPicture;
    private JLabel imageLabel;

    public Interface() {
        super("Interface PGM");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        // Zone d’affichage
        imageLabel = new JLabel("Aucune image chargée", SwingConstants.CENTER);
        add(imageLabel, BorderLayout.CENTER);

        // Barre de menus
        JMenuBar menuBar = new JMenuBar();

        // Menu Fichier
        JMenu fileMenu = new JMenu("Fichier");
        JMenuItem loadItem = new JMenuItem("Charger PGM");
        JMenuItem saveItem = new JMenuItem("Sauvegarder PGM");
        fileMenu.add(loadItem);
        fileMenu.add(saveItem);

        // Menu Traitements
        JMenu PicTessMenu = new JMenu("Traitements");
        JMenuItem thresholdItem = new JMenuItem("Seuillage");
        JMenuItem diffItem = new JMenuItem("Différence");
        JMenuItem enlargeItem = new JMenuItem("Agrandir");
        JMenuItem reduceItem = new JMenuItem("Réduire");
        PicTessMenu.add(thresholdItem);
        PicTessMenu.add(diffItem);
        PicTessMenu.add(enlargeItem);
        PicTessMenu.add(reduceItem);

        menuBar.add(fileMenu);
        menuBar.add(PicTessMenu);
        setJMenuBar(menuBar);

        // Actions
        loadItem.addActionListener(e -> loadPicture());
        saveItem.addActionListener(e -> savePicture());
        thresholdItem.addActionListener(e -> applyThreshold());
        diffItem.addActionListener(e -> applyDifference());
        enlargeItem.addActionListener(e -> applyEnlarge());
        reduceItem.addActionListener(e -> applyReduce());
    }

    // --- Charger une image ---
    private void loadPicture() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                PictureReading reader = new PictureReading(chooser.getSelectedFile().getAbsolutePath());
                currentPicture = reader.readFile();
                imageLabel.setText("Image chargée : " + chooser.getSelectedFile().getName());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Erreur de lecture : " + ex.getMessage());
            }
        }
    }

    // --- Sauvegarder une image ---
    private void savePicture() {
        if (currentPicture == null) {
            JOptionPane.showMessageDialog(this, "Aucune image à sauvegarder !");
            return;
        }
        JFileChooser chooser = new JFileChooser();
        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                PictureWriter io = new PictureWriter(currentPicture);
                io.ecrirePGM(chooser.getSelectedFile().getAbsolutePath());
                JOptionPane.showMessageDialog(this, "Image sauvegardée !");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Erreur d’écriture : " + ex.getMessage());
            }
        }
    }

    // --- Traitements ---
    private void applyThreshold() {
        if (currentPicture == null) return;
        String input = JOptionPane.showInputDialog(this, "Seuil (0-255) :");
        if (input != null) {
            try {
                int T = Integer.parseInt(input);
                imageLabel.setText("Seuillage appliqué (T=" + T + ")");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Valeur invalide !");
            }
        }
    }

    private void applyDifference() {
        JOptionPane.showMessageDialog(this, "Différence : à implémenter avec une 2e image !");
    }

    private void applyEnlarge() {
        if (currentPicture == null) return;
        String input = JOptionPane.showInputDialog(this, "Facteur d'agrandissement :");
        if (input != null) {
            try {
                int factor = Integer.parseInt(input);
                currentPicture = enlarge(currentPicture, factor);
                imageLabel.setText("Image agrandie x" + factor);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Valeur invalide !");
            }
        }
    }

    private void applyReduce() {
        if (currentPicture == null) return;
        String input = JOptionPane.showInputDialog(this, "Facteur de réduction :");
        if (input != null) {
            try {
                int factor = Integer.parseInt(input);
                currentPicture = reduce(currentPicture, factor);
                imageLabel.setText("Image réduite /" + factor);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Valeur invalide !");
            }
        }
    }

    // --- Méthodes de traitement d'image ---

    /**
     * Agrandit une image d'un facteur donné
     * @param pic l'image source
     * @param factor le facteur d'agrandissement
     * @return une nouvelle image agrandie
     */
    private Picture enlarge(Picture pic, int factor) {
        int newWidth = pic.getWidth() * factor;
        int newHeight = pic.getHeight() * factor;
        Picture result = new Picture(newWidth, newHeight, pic.getMaxGray());
        
        for (int i = 0; i < newHeight; i++) {
            for (int j = 0; j < newWidth; j++) {
                int origRow = i / factor;
                int origCol = j / factor;
                result.setPixelPrecis(i, j, pic.getPixelPrecis(origRow, origCol));
            }
        }
        return result;
    }

    /**
     * Réduit une image d'un facteur donné
     * @param pic l'image source
     * @param factor le facteur de réduction
     * @return une nouvelle image réduite
     */
    private Picture reduce(Picture pic, int factor) {
        int newWidth = pic.getWidth() / factor;
        int newHeight = pic.getHeight() / factor;
        Picture result = new Picture(newWidth, newHeight, pic.getMaxGray());
        
        for (int i = 0; i < newHeight; i++) {
            for (int j = 0; j < newWidth; j++) {
                result.setPixelPrecis(i, j, pic.getPixelPrecis(i * factor, j * factor));
            }
        }
        return result;
    }
}