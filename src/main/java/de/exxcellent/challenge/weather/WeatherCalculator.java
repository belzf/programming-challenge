package de.exxcellent.challenge.weather;

import de.exxcellent.challenge.io.CSVReader;
import de.exxcellent.challenge.util.TableUtil;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;

/**
 * This class can be used to calculated stuff based on weather data. The import of this data happens in the constructor
 * via the given Path object.
 *
 * @author Felix Belz
 */
public class WeatherCalculator {

    private static final String CSV_DELIMITER = ",";
    private static final String[] WEATHER_DATA_HEADERS = {"Day","MxT","MnT","AvT","AvDP","1HrP TPcpn","PDir","AvSp","Dir","MxS","SkyC","MxR","Mn","R AvSLP"};

    private final String[][] data;

    /**
     * Constructor which automatically imports the weather data used by this object. Valid weather data is a table
     * of data with a header like {@link #WEATHER_DATA_HEADERS}. The weather data will be imported
     * from the CSV file saved under the given path. The day, the maximum and minimum temperature data will be saved
     * in {@link #data}.
     * @param dataFilePath The path to the csv file with the weather data.
     * @throws IllegalArgumentException if weather data could not be imported or is not valid.
     */
    public WeatherCalculator(Path dataFilePath) throws IllegalArgumentException {

        CSVReader reader = new CSVReader();
        String[][] csvData;

        try {
            // import the weather data
            csvData = reader.importCSVFile(dataFilePath, CSV_DELIMITER);

            // check if the import was successfully
            if (csvData == null || csvData.length == 0) {
                throw new IllegalArgumentException("No data could be loaded for data file under " + dataFilePath);

            } else {
                String[] dataHeaders = csvData[0];

                if (!Arrays.equals(dataHeaders, WEATHER_DATA_HEADERS)) {
                    throw new IllegalArgumentException("The imported weather data is not valid.");
                }

                // save the weather data without headers and only with the first three columns
                this.data = new String[csvData.length - 1][3];
                for (int i = 1; i < csvData.length; i++) {
                    data[i - 1] = Arrays.copyOfRange(csvData[i], 0, 3);
                }
            }

        } catch (IOException e) {
            throw new IllegalArgumentException("Data file path is invalid: \n " + e);
        }
    }

    /**
     * Calculates and returns the day of the currently imported weather data with the smallest spread between the
     * maximum temperature and the minimum temperature of that day.
     * @return The respective day number as a String
     */
    public String getDayWithSmallestSpread() {

        return TableUtil.getRowWithMinimalDifference(
                this.data,
                0,
                1,
                2,
                (column1, column2) -> column1 - column2);
    }
}
