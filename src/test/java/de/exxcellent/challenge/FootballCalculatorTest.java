package de.exxcellent.challenge;

import de.exxcellent.challenge.calculators.FootballCalculator;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static de.exxcellent.challenge.helpers.TestFiles.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FootballCalculatorTest {

    @Test
    public void testConstructorInvalidPath() {
        assertThrows(IllegalArgumentException.class, () -> new FootballCalculator(Path.of("random/path/to/nothing")));
    }

    @Test
    public void testConstructorInvalidHeader() {
        assertThrows(IllegalArgumentException.class, () -> new FootballCalculator(VALID_FILE_PATH));
    }

    @Test
    public void testGetTeamWithSmallestGoalSpreadPositiveGoalDifference() {
        FootballCalculator calculator = new FootballCalculator(VALID_FOOTBALL_POSITIVE_GOALS_FILE_PATH);
        String testTeam = calculator.getTeamWithSmallestGoalSpread();

        assertEquals("Test_The_Best", testTeam);
    }

    @Test
    public void testGetTeamWithSmallestGoalSpreadNegativeGoalDifference() {
        FootballCalculator calculator = new FootballCalculator(VALID_FOOTBALL_NEGATIVE_GOALS_FILE_PATH);
        String testTeam = calculator.getTeamWithSmallestGoalSpread();

        assertEquals("Liverpool", testTeam);
    }

    @Test
    public void testGetTeamWithBestGoalDifferencePositiveGoalDifference() {
        FootballCalculator calculator = new FootballCalculator(VALID_FOOTBALL_POSITIVE_GOALS_FILE_PATH);
        String testTeam = calculator.getTeamWithBestGoalDifference();

        assertEquals("Arsenal", testTeam);
    }
}
