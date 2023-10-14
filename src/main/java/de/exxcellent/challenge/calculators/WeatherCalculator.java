package de.exxcellent.challenge.calculators;

import de.exxcellent.challenge.util.TableUtil;

import java.nio.file.Path;

/**
 * This class can be used to calculated stuff based on weather data. The import of this data happens in the constructor
 * via the given Path object.
 *
 * @author Felix Belz
 */
public class WeatherCalculator extends TableCalculator {

    private static final String CSV_DELIMITER = ",";
    private static final String[] WEATHER_DATA_HEADER = {"Day","MxT","MnT","AvT","AvDP","1HrP TPcpn","PDir","AvSp","Dir","MxS","SkyC","MxR","Mn","R AvSLP"};
    private static final int[] WEATHER_COLUMN_INDICES = {0, 1, 2};

    /**
     * Constructor which automatically imports the weather data used by this object. Valid weather data is a table
     * of data with a header like {@link #WEATHER_DATA_HEADER}. The weather data will be imported
     * from the CSV file saved under the given path. The day, the maximum and minimum temperature data will be saved
     * in {@link #data}.
     * @param dataFilePath The path to the csv file with the weather data.
     * @throws IllegalArgumentException if weather data could not be imported or is not valid.
     */
    public WeatherCalculator(Path dataFilePath) throws IllegalArgumentException {

        super(dataFilePath, CSV_DELIMITER, WEATHER_DATA_HEADER, WEATHER_COLUMN_INDICES);
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
