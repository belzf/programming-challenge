package de.exxcellent.challenge.calculators;

import de.exxcellent.challenge.util.TableUtil;

import java.nio.file.Path;


/**
 * This class can be used to calculated stuff based on football data. The import of this data happens in the constructor
 * via the given Path object.
 *
 * @author Felix Belz
 */
public class FootballCalculator extends TableCalculator{

    private static final String[] FOOTBALL_DATA_HEADER = {"Team","Games","Wins","Losses","Draws","Goals","Goals Allowed","Points"};
    private static final int[] FOOTBALL_COLUMN_INDICES = {0, 5, 6};

    /**
     * Constructor which automatically imports the football data used by this object. Valid football data is a table
     * of data with a header like {@link #FOOTBALL_DATA_HEADER}. The football data will be imported from the file
     * saved under the given path. The team, the goals and goals allowed columns will be saved in {@link #data}.
     *
     * @param dataFilePath The path to the csv file with the football data.
     * @throws IllegalArgumentException if football data could not be imported or is not valid.
     */
    public FootballCalculator(Path dataFilePath) throws IllegalArgumentException {

        super(dataFilePath, FOOTBALL_DATA_HEADER, FOOTBALL_COLUMN_INDICES);
    }

    /**
     * Calculates and returns the name of the football team of the currently imported football data with the smallest
     * absolut difference between the goals and the goals allowed.
     *
     * @return The respective team name as a String.
     */
    public String getTeamWithSmallestGoalSpread() {

        return TableUtil.getRowWithMinimalDifference(
                this.data,
                0,
                1,
                2,
                (column1, column2) -> Math.abs(column1 - column2));
    }

    /**
     * Calculates and returns the name of the football team of the currently imported football data with the best goal
     * difference.
     *
     * @return The respective team name as a String.
     */
    public String getTeamWithBestGoalDifference() {

        return TableUtil.getRowWithMinimalDifference(
                this.data,
                0,
                1,
                2,
                // invert result to get the team with the highest goal difference as "minimum"
                (column1, column2) -> -1*(column1 - column2));
    }
}
