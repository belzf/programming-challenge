package de.exxcellent.challenge.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains a method to import a CSV file as an array of arrays
 */
public class CSVReader implements Readable{

    private static final String CSV_DELIMITER = ",";

    @Override
    public String[][] readTableData(Path pathToFile) throws IllegalArgumentException, IOException {
        return importCSVFile(pathToFile, CSV_DELIMITER);
    }

    /**
     * Import a given CSV file as an array of arrays of Strings.
     *
     * @param csvFilePath The file path pointing to the CSV file.
     * @param csvDelimiter The delimiter used in the CSV file.
     * @return The content of the CSV file as an array of arrays of String.
     * @throws IllegalArgumentException if not all lines of the csv file have the same number of elements separated by
     * the delimiter
     * @throws IOException if something with the path or the csv file itself is not correct
     *
     * @author Felix Belz
     */
    public String[][] importCSVFile(Path csvFilePath, String csvDelimiter) throws IllegalArgumentException, IOException {
        List<String[]> csvList = new ArrayList<>();

        String line;

        // create a BufferedReader to read the content of the given CSV file
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFilePath.toFile()))) {

            // read headers of csv file
            String headerLine = bufferedReader.readLine();

            if (headerLine == null) {
                throw new IOException("Imported CSV file does not have a header line.");
            }

            String[] headers = headerLine.split(csvDelimiter);
            csvList.add(headers);

            // save header length to validate format
            int headerLength = headers.length;

            // split each line based on the given delimiter and add it as an array to csvList
            while ((line = bufferedReader.readLine()) != null) {

                // Split the line into entries using the delimiter
                String[] entries = line.split(csvDelimiter);

                // check that each row has the length of the header
                if (entries.length != headerLength) {
                    throw new IllegalArgumentException("Table row does not match the header length: " + line);
                }

                csvList.add(entries);
            }
        }

        // Convert the list to an array of arrays
        String[][] csvTable = new String[csvList.size()][];
        csvList.toArray(csvTable);

        return csvTable;
    }
}
