package de.exxcellent.challenge;

import de.exxcellent.challenge.io.CSVReader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static de.exxcellent.challenge.helpers.TestFiles.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 test cases for testing the CSVReader class.
 */
class CSVReaderTest {

    private static CSVReader reader;

    @BeforeAll
    static void initReader() {
        reader = new CSVReader();
    }

    @Test
    void testImportValidCSV() throws IOException {

        String[] expectedTableHeader = {"test1", "test2", "test3"};
        String[] fifthRow = {"4","7","8"};

        String[][] importedTable = reader.importCSVFile(VALID_FILE_PATH, ",");

        assertArrayEquals(expectedTableHeader, importedTable[0]);
        assertEquals(6, importedTable.length);
        assertArrayEquals(fifthRow, importedTable[4]);
    }

    @Test
    void testImportInvalidRowLength() {

        assertThrows(IllegalArgumentException.class, () -> reader.importCSVFile(INVALID_FILE_PATH, ","));
    }

    @Test
    void testImportSemicolonDelimiter() throws IOException {
        String[] expectedTableHeader = {"Day","MxT","MnT","AvT","AvDP","1HrP TPcpn","PDir","AvSp","Dir","MxS","SkyC","MxR","Mn","R AvSLP"};
        String[] eighthRow = {"7","73","57","65","53","0","50","9.5","50","17","5.3","90","48","1021.8"};

        String[][] importedTable = reader.importCSVFile(SEMICOLON_FILE_PATH, ";");

        assertArrayEquals(expectedTableHeader, importedTable[0]);
        assertEquals(9, importedTable.length);
        assertArrayEquals(eighthRow, importedTable[7]);
    }

    @Test
    void testImportEmptyFile() {

        assertThrows(IOException.class, () -> reader.importCSVFile(EMPTY_FILE_PATH, ";"));
    }
}