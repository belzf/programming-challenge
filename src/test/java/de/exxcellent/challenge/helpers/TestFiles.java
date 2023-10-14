package de.exxcellent.challenge.helpers;

import java.nio.file.Path;

/**
 * Contains the Paths to the different test files
 */
public class TestFiles {

    public static final Path baseDir = Path.of(System.getProperty("user.dir"));

    public static final Path VALID_FILE_PATH = baseDir.resolve(Path.of("src/test/java/de/exxcellent/challenge/resources/valid.csv"));
    public static final Path INVALID_FILE_PATH = baseDir.resolve(Path.of("src/test/java/de/exxcellent/challenge/resources/invalid-row-length.csv"));
    public static final Path SEMICOLON_FILE_PATH = baseDir.resolve(Path.of("src/test/java/de/exxcellent/challenge/resources/semicolon.csv"));
    public static final Path EMPTY_FILE_PATH = baseDir.resolve(Path.of("src/test/java/de/exxcellent/challenge/resources/empty-file.csv"));
    public static final Path VALID_WEATHER_FILE_PATH = baseDir.resolve(Path.of("src/test/java/de/exxcellent/challenge/resources/weather-test1.csv"));
    public static final Path VALID_FOOTBALL_POSITIVE_GOALS_FILE_PATH = baseDir.resolve(Path.of("src/test/java/de/exxcellent/challenge/resources/football-test1.csv"));
    public static final Path VALID_FOOTBALL_NEGATIVE_GOALS_FILE_PATH = baseDir.resolve(Path.of("src/test/java/de/exxcellent/challenge/resources/football-test2.csv"));
}
