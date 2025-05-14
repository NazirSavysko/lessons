package smartphone_factory.io;

import java.io.FileWriter;
import java.io.PrintWriter;

import static java.lang.System.out;

public enum FileSmartphoneManager{
    INSTANCE;

    private static final String FILE_PATH = "smartphone_order_information.txt";
    private static final String FILE_WRITE_ERROR_MESSAGE = "Error while saving data to file: %s";

    public synchronized  void writeInformationAboutOrderIntoFile(String data) {
        try (final PrintWriter printWriter = new PrintWriter(new FileWriter(FILE_PATH, true))) {
            printWriter.println(data);
        } catch (Exception e) {
            out.printf(FILE_WRITE_ERROR_MESSAGE, e.getMessage());
        }

    }
}

