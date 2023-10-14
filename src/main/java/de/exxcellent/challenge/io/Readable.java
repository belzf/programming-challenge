package de.exxcellent.challenge.io;

import java.io.IOException;
import java.nio.file.Path;

public interface Readable {

    String[][] readTableData(Path pathToFile) throws IOException;
}
