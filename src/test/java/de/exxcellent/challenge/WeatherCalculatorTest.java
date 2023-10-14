package de.exxcellent.challenge;

import de.exxcellent.challenge.weather.WeatherCalculator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static de.exxcellent.challenge.helpers.TestFiles.VALID_FILE_PATH;
import static de.exxcellent.challenge.helpers.TestFiles.VALID_WEATHER_FILE_PATH;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WeatherCalculatorTest {

    private static WeatherCalculator calculator;

    @BeforeAll
    public static void initWeatherCalculator() {
        calculator = new WeatherCalculator(VALID_WEATHER_FILE_PATH);
    }

    @Test
    public void testConstructorInvalidPath() {
        assertThrows(IllegalArgumentException.class, () -> new WeatherCalculator(Path.of("random/path/to/nothing")));
    }

    @Test
    public void testConstructorInvalidHeader() {
        assertThrows(IllegalArgumentException.class, () -> new WeatherCalculator(VALID_FILE_PATH));
    }

    @Test
    public void testGetDayWithSmallestSpread() {
        String testDay = calculator.getDayWithSmallestSpread();

        assertEquals("20", testDay);
    }
}
