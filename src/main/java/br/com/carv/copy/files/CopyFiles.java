package br.com.carv.copy.files;

/**
 *
 * @author joao
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class CopyFiles {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.print("Enter a directory name to be copied: ");
        String originalDirectoryName = scan.nextLine();

        System.out.print("Enter the name of new directory: ");
        String newDirectoryName = scan.next();

        System.out.print("Enter the path to save the new directory: ");
        String pathNewDirectoryName = scan.next();

        System.out.println("Validating information...");

        if (newDirectoryName.isEmpty() || newDirectoryName.isBlank()
                || originalDirectoryName.isBlank() || originalDirectoryName.isEmpty()
                || pathNewDirectoryName.isBlank() || pathNewDirectoryName.isEmpty()) {

            throw new IllegalArgumentException("Entered names are invalid for the requested fields.");
        }

        try {

            File originDir = new File(originalDirectoryName);
            File newDir = new File(pathNewDirectoryName, newDirectoryName);

            String[] filesToBeCopied = originDir.list();

            System.out.print("Number of files found to be copied: " + filesToBeCopied.length);
            System.out.println();

            for (String file : filesToBeCopied) {
                System.out.println("File name: " + file);
            }

            if (newDir.mkdir()) {
                System.out.println("New Directory was created with name: " + newDir.getName() + "\nPath: " + newDir.getAbsolutePath());

                for (int i = 0; i < filesToBeCopied.length; i++) {

                    File originFile = new File(originDir.getAbsolutePath(), filesToBeCopied[i]);
                    File finalFile = new File(newDir.getAbsolutePath(), filesToBeCopied[i]);

                    BufferedReader reader = new BufferedReader(new FileReader(originFile));
                    BufferedWriter writer = new BufferedWriter(new FileWriter(finalFile));

                    String line = null;

                    while ((line = reader.readLine()) != null) {
                        writer.write(line);
                        writer.newLine();
                    }
                    
                    reader.close();
                    writer.close();
                }

                System.out.println("Files successfully copied!");
                System.out.println("The files were saved in: " + newDir.getAbsolutePath());
            }

        } catch (Exception ex) {
            Logger.getLogger(CopyFiles.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
}
