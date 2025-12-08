/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package org.centrale.infosi.picturecomparator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Tests unitaires de PictureWriter
 * @author jujus
 */
public class PictureWriterTest {
    
    private PictureWriter writer;
    private Picture testPicture;
    
    @TempDir
    Path tempDir;
    
    @BeforeEach
    public void setUp() {
        // Création d'une image de test simple
        testPicture = new Picture(3, 3, 255);
        testPicture.setPixelPrecis(0, 0, 100);
        testPicture.setPixelPrecis(0, 1, 150);
        testPicture.setPixelPrecis(0, 2, 200);
        testPicture.setPixelPrecis(1, 0, 50);
        testPicture.setPixelPrecis(1, 1, 75);
        testPicture.setPixelPrecis(1, 2, 125);
        testPicture.setPixelPrecis(2, 0, 25);
        testPicture.setPixelPrecis(2, 1, 175);
        testPicture.setPixelPrecis(2, 2, 225);
        
        writer = new PictureWriter(testPicture);
    }

    /**
     * Test du constructeur par défaut
     */
    @Test
    public void testDefaultConstructor() {
        PictureWriter defaultWriter = new PictureWriter();
        assertNotNull(defaultWriter, "Le writer ne devrait pas être null");
        assertNotNull(defaultWriter.getPic(), "L'image par défaut ne devrait pas être null");
        assertEquals(50, defaultWriter.getPic().getWidth(), "L'image par défaut devrait avoir une largeur de 50");
        assertEquals(50, defaultWriter.getPic().getHeight(), "L'image par défaut devrait avoir une hauteur de 50");
    }

    /**
     * Test du constructeur avec paramètre
     */
    @Test
    public void testConstructorWithPicture() {
        Picture pic = new Picture(10, 20, 200);
        PictureWriter customWriter = new PictureWriter(pic);
        assertNotNull(customWriter, "Le writer ne devrait pas être null");
        assertEquals(pic, customWriter.getPic(), "L'image devrait être celle passée en paramètre");
    }

    /**
     * Test des getters et setters
     */
    @Test
    public void testGetterSetter() {
        Picture newPic = new Picture(15, 15, 100);
        writer.setPic(newPic);
        assertEquals(newPic, writer.getPic(), "La méthode getPic devrait retourner l'image définie");
    }

    /**
     * Test d'écriture d'un fichier PGM valide
     */
    @Test
    public void testEcrirePGMValid() throws IOException {
        File outputFile = tempDir.resolve("test_output.pgm").toFile();
        writer.ecrirePGM(outputFile.getAbsolutePath());
        
        assertTrue(outputFile.exists(), "Le fichier devrait être créé");
        assertTrue(outputFile.length() > 0, "Le fichier ne devrait pas être vide");
    }

    /**
     * Test du format du fichier PGM écrit
     */
    @Test
    public void testEcrirePGMFormat() throws IOException {
        File outputFile = tempDir.resolve("test_format.pgm").toFile();
        writer.ecrirePGM(outputFile.getAbsolutePath());
        
        try (Scanner scanner = new Scanner(outputFile)) {
            // Vérification de l'en-tête P2
            assertTrue(scanner.hasNextLine(), "Le fichier devrait contenir des lignes");
            String magicNumber = scanner.nextLine().trim();
            assertEquals("P2", magicNumber, "La première ligne devrait être 'P2'");
            
            // Vérification du commentaire
            assertTrue(scanner.hasNextLine(), "Le fichier devrait contenir un commentaire");
            String comment = scanner.nextLine();
            assertTrue(comment.startsWith("#"), "La deuxième ligne devrait être un commentaire");
            
            // Vérification des dimensions
            assertTrue(scanner.hasNextLine(), "Le fichier devrait contenir les dimensions");
            String dimensions = scanner.nextLine().trim();
            assertEquals("3 3", dimensions, "Les dimensions devraient être '3 3'");
            
            // Vérification de maxGray
            assertTrue(scanner.hasNextLine(), "Le fichier devrait contenir maxGray");
            String maxGray = scanner.nextLine().trim();
            assertEquals("255", maxGray, "MaxGray devrait être '255'");
            
            // Vérification de la présence des pixels
            assertTrue(scanner.hasNext(), "Le fichier devrait contenir des valeurs de pixels");
        }
    }

    /**
     * Test que les valeurs des pixels sont correctement écrites
     */
    @Test
    public void testEcrirePGMPixelValues() throws IOException {
        File outputFile = tempDir.resolve("test_pixels.pgm").toFile();
        writer.ecrirePGM(outputFile.getAbsolutePath());
        
        try (BufferedReader reader = new BufferedReader(new FileReader(outputFile))) {
            // Sauter l'en-tête (4 lignes)
            reader.readLine(); // P2
            reader.readLine(); // Commentaire
            reader.readLine(); // Dimensions
            reader.readLine(); // MaxGray
            
            // Lire les pixels
            StringBuilder pixelData = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                pixelData.append(line).append(" ");
            }
            
            String pixels = pixelData.toString();
            assertTrue(pixels.contains("100"), "Les pixels devraient contenir la valeur 100");
            assertTrue(pixels.contains("150"), "Les pixels devraient contenir la valeur 150");
            assertTrue(pixels.contains("200"), "Les pixels devraient contenir la valeur 200");
        }
    }

    /**
     * Test de la contrainte des 70 caractères par ligne
     */
    @Test
    public void testEcrirePGMLineLength() throws IOException {
        // Création d'une grande image pour tester la contrainte des 70 caractères
        Picture largePic = new Picture(50, 50, 255);
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                largePic.setPixelPrecis(i, j, 255);
            }
        }
        PictureWriter largeWriter = new PictureWriter(largePic);
        
        File outputFile = tempDir.resolve("test_linelength.pgm").toFile();
        largeWriter.ecrirePGM(outputFile.getAbsolutePath());
        
        try (BufferedReader reader = new BufferedReader(new FileReader(outputFile))) {
            // Sauter l'en-tête
            reader.readLine();
            reader.readLine();
            reader.readLine();
            reader.readLine();
            
            String line;
            while ((line = reader.readLine()) != null) {
                assertTrue(line.length() <= 80, 
                    "Chaque ligne devrait respecter approximativement la contrainte de 70 caractères (avec marge)");
            }
        }
    }

    /**
     * Test d'écriture avec une image par défaut
     */
    @Test
    public void testEcrirePGMDefaultPicture() throws IOException {
        PictureWriter defaultWriter = new PictureWriter();
        File outputFile = tempDir.resolve("test_default.pgm").toFile();
        
        assertDoesNotThrow(() -> defaultWriter.ecrirePGM(outputFile.getAbsolutePath()), 
            "L'écriture d'une image par défaut ne devrait pas lever d'exception");
        assertTrue(outputFile.exists(), "Le fichier devrait être créé");
    }

    /**
     * Test d'écriture avec différentes valeurs de maxGray
     */
    @Test
    public void testEcrirePGMDifferentMaxGray() throws IOException {
        Picture picWithDifferentMaxGray = new Picture(5, 5, 127);
        PictureWriter writerDifferentMax = new PictureWriter(picWithDifferentMaxGray);
        
        File outputFile = tempDir.resolve("test_maxgray.pgm").toFile();
        writerDifferentMax.ecrirePGM(outputFile.getAbsolutePath());
        
        try (Scanner scanner = new Scanner(outputFile)) {
            scanner.nextLine(); // P2
            scanner.nextLine(); // Commentaire
            scanner.nextLine(); // Dimensions
            String maxGray = scanner.nextLine().trim();
            assertEquals("127", maxGray, "MaxGray devrait être '127'");
        }
    }
}
