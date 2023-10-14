package de.exxcellent.challenge;

import de.exxcellent.challenge.weather.WeatherCalculator;

import java.nio.file.Path;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 * @author Felix Belz
 */
public final class App {

    /**
     * This is the main entry method of your program.
     * @param args The CLI arguments passed
     */
    public static void main(String... args) {

        if (args.length != 1) {
            System.out.println("You must provide a path to a weather data csv file!");
            return;
        }

        Path weatherCSVPath = Path.of(args[0]);

        try {
            WeatherCalculator weatherCalculator = new WeatherCalculator(weatherCSVPath);
            String dayWithSmallestTempSpread = weatherCalculator.getDayWithSmallestSpread();
            System.out.printf("Day with smallest temperature spread : %s%n", dayWithSmallestTempSpread);

        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println("The weather data could not be imported. Are you sure '" + weatherCSVPath + "' points " +
                    "to a valid weather data CSV file?");
        } catch (Exception e) {
            System.out.println("Something went wrong. Please try again.");
        }

        String teamWithSmallestGoalSpread = "A good team"; // Your goal analysis function call …
        System.out.printf("Team with smallest goal spread       : %s%n", teamWithSmallestGoalSpread);
    }
}
