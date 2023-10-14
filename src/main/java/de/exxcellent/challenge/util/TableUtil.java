package de.exxcellent.challenge.util;

import java.util.function.BiFunction;

/**
 * Collections of utility functions for working with table data (rows, columns)
 *
 * @author Felix Belz
 */
public class TableUtil {

    /**
     * Find and return the label of the row with the minimal difference compared to the rest of the table. The difference
     * is calculated by the differenceFunction which takes a value from column1 and column2.
     *
     * @param tableData The table data the minimum is searched in.
     * @param rowLabelIndex The index of the column which contains a label to name the row.
     * @param indexColumn1 The index of column1.
     * @param indexColumn2 The index of column2.
     * @param differenceFunction Function which calculates the difference between column1 and column2.
     * @return The label of the row with the minimal difference between column1 and column2.
     */
    public static String getRowWithMinimalDifference(
            String[][] tableData,
            int rowLabelIndex,
            int indexColumn1,
            int indexColumn2,
            BiFunction<Integer, Integer, Integer> differenceFunction
    ) {

        int minDifference = Integer.MAX_VALUE;
        String minRowLabel = "";
        int currentDifference;

        // calculate the difference for each row
        for (String[] row : tableData) {

            // calculate the difference between column1 and column2
            currentDifference = differenceFunction.apply(Integer.parseInt(row[indexColumn1]), Integer.parseInt(row[indexColumn2]));

            System.out.println(row[0] + ": " + currentDifference);

            // save current row label if the difference is smaller as current minimum
            if (currentDifference < minDifference) {
                minDifference = currentDifference;
                minRowLabel = row[rowLabelIndex];
            }
        }

        return minRowLabel;
    }
}
