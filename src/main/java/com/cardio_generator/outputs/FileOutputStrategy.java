package com.cardio_generator.outputs;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ConcurrentHashMap;

// Changed class name to UpperCamelCase
public class FileOutputStrategy implements OutputStrategy {

  // Changed field name 'BaseDirectory' to 'baseDirectory' (lowerCamelCase)
  private String baseDirectory;

  // Changed field name 'file_map' to 'fileMap' (lowerCamelCase)
  public final ConcurrentHashMap<String, String> fileMap = new ConcurrentHashMap<>();

  public FileOutputStrategy(String baseDirectory) {
    // Ensured 'baseDirectory' field is assigned (already lowerCamelCase)
    this.baseDirectory = baseDirectory;
  }

  @Override
  public void output(int patientId, long timestamp, String label, String data) {
    try {
      // Create the directory
      // Adjusted block indentation to +2 spaces
      Files.createDirectories(Paths.get(baseDirectory));
    } catch (IOException e) {
      System.err.println("Error creating base directory: " + e.getMessage());
      return;
    }
    // Set the filePath variable
    // Changed local variable name 'FilePath' to 'filePath' (lowerCamelCase)
    // Wrapped line due to exceeding 100 characters
    // Indented continuation line
    String filePath = fileMap.computeIfAbsent(label,
        k -> Paths.get(baseDirectory, label + ".txt").toString());

    // Write the data to the file
    // Wrapped line due to exceeding 100 characters
    // Indented continuation lines
    try (PrintWriter out = new PrintWriter(
        Files.newBufferedWriter(Paths.get(filePath), StandardOpenOption.CREATE,
            StandardOpenOption.APPEND))) {
      // Wrapped line due to exceeding 100 characters
      // Indented continuation line
      out.printf("Patient ID: %d, Timestamp: %d, Label: %s, Data: %s%n",
          patientId, timestamp, label, data);
    } catch (Exception e) {
      // Used 'filePath' (lowerCamelCase) in error message
      System.err.println("Error writing to file " + filePath + ": " + e.getMessage());
    }
  }
}
