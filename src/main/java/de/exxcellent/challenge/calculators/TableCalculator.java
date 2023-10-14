package de.exxcellent.challenge.calculators;

import de.exxcellent.challenge.io.CSVReader;
import de.exxcellent.challenge.io.Readable;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;

/**
 * Class holds table data as an array od arrays. The data is imported im the constructor and can be used for
 * further calculations.
 *
 * @author Felix Belz
 */
public abstract class TableCalculator {

    // the table data
    final String[][] data;

    /**
     * Constructor which automatically imports the table data used by this object. Valid table data is a table
     * of data with a header and rows of equal length. The table data will be imported from the file saved
     * under the given path while only the columns specified will be saved.
     *
     * @param dataFilePath The path to the csv file with the weather data.
     * @param validHeader The expected names of the table columns as a String array.
     * @param columnIndices The indices  of the columns which should be saved.
     * @throws IllegalArgumentException if weather data could not be imported or is not valid.
     */
    public TableCalculator(Path dataFilePath, String[] validHeader, int[] columnIndices) throws IllegalArgumentException {

        Readable reader = new CSVReader();
        String[][] csvData;

        try {
            // import the table data
            csvData = reader.readTableData(dataFilePath);

            // check if the import was successfully
            if (csvData == null || csvData.length == 0) {
                throw new IllegalArgumentException("No data could be loaded for data file under " + dataFilePath);

            } else {
                String[] dataHeader = csvData[0];

                if (!Arrays.equals(dataHeader, validHeader)) {
                    throw new IllegalArgumentException("The imported table data does not have a valid header.");
                }

                // save the table data without header and only with the specified columns
                this.data = Arrays.stream(csvData)
                        .skip(1)
                        .map(row -> Arrays.stream(columnIndices)
                                .mapToObj(index -> row[index])
                                .toArray(String[]::new))
                        .toArray(String[][]::new);
            }

        } catch (IOException e) {
            throw new IllegalArgumentException("Data file path is invalid: \n " + e);
        }
    }
}
