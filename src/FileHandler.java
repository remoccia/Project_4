import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * The type File handler.
 */
public class FileHandler {
    private final String surveyFile = "survey_results.csv";
    private FileWriter fileOutput = new FileWriter(surveyFile);
    private PrintWriter printWriter = new PrintWriter(fileOutput);

    /**
     * Get date string.
     *
     * @return the string
     */
    public static String getDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date date = new Date();
        return (dateFormat.format(date));
    }

    /**
     * Instantiates a new File handler.
     *
     * @throws IOException the io exception
     */
    public FileHandler() throws IOException {
        try{
            fileOutput.write("DateTime,FirstName,LastName,PhoneNumber,Email,Sex," +
                    "Water,Wheat,Sugar,Dairy,Miles,Weight\n");
            printWriter.close();
            fileOutput.close();
        } catch (Exception ignored) {
        }


    }

    /**
     * Write results.
     *
     * @param surveyData the survey data
     * @throws IOException the io exception
     */
    public void writeResults(String surveyData) throws IOException {
        try (FileWriter fileOutput = new FileWriter(surveyFile, true);
             PrintWriter printWriter = new PrintWriter(fileOutput)) {
            printWriter.append(getDate()).append(",").append(surveyData).append("\n");
        } catch (Exception ignored) {

        }
    }

}

