/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.centrale.infosi.picturecomparator;

import javax.swing.SwingUtilities;

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
        SwingUtilities.invokeLater(() -> {
            Interface gui = new Interface();
            gui.setVisible(true);
        });
    }
}
