/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package org.centrale.infosi.picturecomparator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.*;

/**
 * Tests unitaires de l'interface
 * @author jujus
 */
public class InterfaceTest {
    
    private Interface interfaceInstance;
    
    @BeforeEach
    public void setUp() {
        // Initialisation de l'interface avant chaque test
        interfaceInstance = new Interface();
    }

    /**
     * Test de la création de l'interface
     */
    @Test
    public void testInterfaceCreation() {
        assertNotNull(interfaceInstance, "L'interface devrait être créée");
        assertEquals("Interface PGM", interfaceInstance.getTitle(), "Le titre devrait être 'Interface PGM'");
        assertEquals(JFrame.EXIT_ON_CLOSE, interfaceInstance.getDefaultCloseOperation(), 
                "L'opération de fermeture devrait être EXIT_ON_CLOSE");
    }

    /**
     * Test de la taille de l'interface
     */
    @Test
    public void testInterfaceSize() {
        assertEquals(600, interfaceInstance.getWidth(), "La largeur devrait être 600");
        assertEquals(400, interfaceInstance.getHeight(), "La hauteur devrait être 400");
    }

    /**
     * Test de la présence de la barre de menus
     */
    @Test
    public void testMenuBarExists() {
        JMenuBar menuBar = interfaceInstance.getJMenuBar();
        assertNotNull(menuBar, "La barre de menus devrait exister");
        assertTrue(menuBar.getMenuCount() >= 2, "Il devrait y avoir au moins 2 menus");
    }

    /**
     * Test de la présence du menu Fichier
     */
    @Test
    public void testFileMenuExists() {
        JMenuBar menuBar = interfaceInstance.getJMenuBar();
        JMenu fileMenu = menuBar.getMenu(0);
        assertNotNull(fileMenu, "Le menu Fichier devrait exister");
        assertEquals("Fichier", fileMenu.getText(), "Le premier menu devrait s'appeler 'Fichier'");
        assertEquals(2, fileMenu.getItemCount(), "Le menu Fichier devrait contenir 2 items");
    }

    /**
     * Test de la présence du menu Traitements
     */
    @Test
    public void testTreatmentsMenuExists() {
        JMenuBar menuBar = interfaceInstance.getJMenuBar();
        JMenu treatmentsMenu = menuBar.getMenu(1);
        assertNotNull(treatmentsMenu, "Le menu Traitements devrait exister");
        assertEquals("Traitements", treatmentsMenu.getText(), "Le second menu devrait s'appeler 'Traitements'");
        assertEquals(4, treatmentsMenu.getItemCount(), "Le menu Traitements devrait contenir 4 items");
    }

    /**
     * Test de la présence des items du menu Fichier
     */
    @Test
    public void testFileMenuItems() {
        JMenuBar menuBar = interfaceInstance.getJMenuBar();
        JMenu fileMenu = menuBar.getMenu(0);
        
        JMenuItem loadItem = fileMenu.getItem(0);
        assertNotNull(loadItem, "L'item 'Charger PGM' devrait exister");
        assertEquals("Charger PGM", loadItem.getText());
        
        JMenuItem saveItem = fileMenu.getItem(1);
        assertNotNull(saveItem, "L'item 'Sauvegarder PGM' devrait exister");
        assertEquals("Sauvegarder PGM", saveItem.getText());
    }

    /**
     * Test de la présence des items du menu Traitements
     */
    @Test
    public void testTreatmentsMenuItems() {
        JMenuBar menuBar = interfaceInstance.getJMenuBar();
        JMenu treatmentsMenu = menuBar.getMenu(1);
        
        JMenuItem thresholdItem = treatmentsMenu.getItem(0);
        assertNotNull(thresholdItem, "L'item 'Seuillage' devrait exister");
        assertEquals("Seuillage", thresholdItem.getText());
        
        JMenuItem diffItem = treatmentsMenu.getItem(1);
        assertNotNull(diffItem, "L'item 'Différence' devrait exister");
        assertEquals("Différence", diffItem.getText());
        
        JMenuItem enlargeItem = treatmentsMenu.getItem(2);
        assertNotNull(enlargeItem, "L'item 'Agrandir' devrait exister");
        assertEquals("Agrandir", enlargeItem.getText());
        
        JMenuItem reduceItem = treatmentsMenu.getItem(3);
        assertNotNull(reduceItem, "L'item 'Réduire' devrait exister");
        assertEquals("Réduire", reduceItem.getText());
    }

    /**
     * Test de la méthode enlarge
     */
    @Test
    public void testEnlarge() throws Exception {
        Picture original = new Picture(10, 10, 255);
        original.setPixelPrecis(0, 0, 100);
        original.setPixelPrecis(5, 5, 200);
        
        // Utilise la réflexion pour accéder à la méthode privée
        java.lang.reflect.Method enlargeMethod = Interface.class.getDeclaredMethod("enlarge", Picture.class, int.class);
        enlargeMethod.setAccessible(true);
        
        Picture enlarged = (Picture) enlargeMethod.invoke(interfaceInstance, original, 2);
        
        assertNotNull(enlarged, "L'image agrandie ne devrait pas être null");
        assertEquals(20, enlarged.getWidth(), "La largeur devrait être doublée");
        assertEquals(20, enlarged.getHeight(), "La hauteur devrait être doublée");
        assertEquals(100, enlarged.getPixelPrecis(0, 0), "Le pixel (0,0) devrait avoir la valeur 100");
        assertEquals(100, enlarged.getPixelPrecis(1, 1), "Le pixel (1,1) devrait avoir la valeur 100");
    }

    /**
     * Test de la méthode reduce
     */
    @Test
    public void testReduce() throws Exception {
        Picture original = new Picture(20, 20, 255);
        original.setPixelPrecis(0, 0, 100);
        original.setPixelPrecis(10, 10, 200);
        
        // Utilise la réflexion pour accéder à la méthode privée
        java.lang.reflect.Method reduceMethod = Interface.class.getDeclaredMethod("reduce", Picture.class, int.class);
        reduceMethod.setAccessible(true);
        
        Picture reduced = (Picture) reduceMethod.invoke(interfaceInstance, original, 2);
        
        assertNotNull(reduced, "L'image réduite ne devrait pas être null");
        assertEquals(10, reduced.getWidth(), "La largeur devrait être divisée par 2");
        assertEquals(10, reduced.getHeight(), "La hauteur devrait être divisée par 2");
        assertEquals(100, reduced.getPixelPrecis(0, 0), "Le pixel (0,0) devrait avoir la valeur 100");
        assertEquals(200, reduced.getPixelPrecis(5, 5), "Le pixel (5,5) devrait avoir la valeur 200");
    }
}
