

import java.io.*;

/**
 * This class is used to create CSV files,
 */
public class CSVCreator {

    public static void main(String[] args) {
        createCSV("genres2To6Count", new String[]{"genres", "count"},"genres2To6Count.csv");
        createCSV("genres6To10Count", new String[]{"genres", "count"},"genres6To10Count.csv");
    }

    private static final String ASSETS_PATH = "assets\\";

    private static void createCSV(String inputFileName, String[] fieldNames, String outputFileName){
        StringBuilder sb = new StringBuilder();

        File file = new File(ASSETS_PATH + inputFileName);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis, "utf-8"));
            sb.append(getCSVLine(fieldNames) + "\n");
            String line = "";
            while ((line = br.readLine()) != null){
                String[] tokens = line.trim().split("\t");
                sb.append(getCSVLine(tokens));
                sb.append("\n");
            }
            FileWriter fileWriter = new FileWriter(ASSETS_PATH + "csv\\" + outputFileName);
            fileWriter.write(sb.toString());
            fileWriter.flush();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getCSVLine(String[] values){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.length; ++i){
            sb.append(values[i]);
            if (i + 1 != values.length){
                sb.append(",");
            }
        }
        return sb.toString();
    }
}
