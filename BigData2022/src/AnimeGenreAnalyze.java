import au.com.bytecode.opencsv.CSVReader;

import java.io.*;

public class AnimeGenreAnalyze {
    private final static String ASSETS_PATH = "assets\\";
    private final static int MIN_MEMBERS_NUM = 1000;
    private final static int ID = 0;
    private final static int NAME = 1;
    private final static int RATING = 5;
    private final static int MEMBERS = 6;

    public static void main(String[] args) {
        collectGenres(MEMBERS, "members.txt");
    }

    private static void collectGenres(int key, String outputFileName){
        File animeCsv = new File(ASSETS_PATH + "csv\\anime.csv");
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(animeCsv);
            CSVReader csvReader = new CSVReader(fileReader);
            csvReader.readNext(); // read the first line (useless)
            String[] line = null;
            FileWriter fileWriter = null;
            StringBuilder sb = new StringBuilder();
            int count = 0;
            while ((line = csvReader.readNext()) != null){
                int membersNum = Integer.parseInt(line[6]);
                if (membersNum >= MIN_MEMBERS_NUM) {
                    String keyStr = line[key];
                    if (keyStr.equals("")) {
                        continue;
                    }
                    sb.append(keyStr + "\t");
                    String[] genres = line[2].split(", ");
                    for (String genre : genres){
                        genre = genre.trim().replace(" ", "-");
                        sb.append(genre + " ");
                    }
                    sb.append("\n");
                    ++count;
                }
            }
            System.out.println("The Number of animes considered: " + count);
            File file = new File(ASSETS_PATH + outputFileName);
            fileWriter = new FileWriter(file);
            fileWriter.write(sb.toString());
            fileWriter.flush();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void collectGenres(){
        File animeCsv = new File(ASSETS_PATH + "csv\\anime.csv");
        FileReader fileReader = null;

        try {
            fileReader = new FileReader(animeCsv);
            CSVReader csvReader = new CSVReader(fileReader);
            csvReader.readNext(); // read the first line (useless)
            String[] line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = csvReader.readNext()) != null){
                int membersNum = Integer.parseInt(line[6]);
                if (membersNum >= MIN_MEMBERS_NUM){
                    String ratingStr = line[5];
                    if (ratingStr.equals("")){
                        continue;
                    }
                    double rating = Double.parseDouble(ratingStr);
                    String[] genres = line[2].split(", ");
                    for (String genre : genres){
                        genre = genre.trim().replace(" ", "-");
                        sb.append(genre + " ");
                    }
                    sb.append("\n");
                }
            }
            File file = new File(ASSETS_PATH + "genres.txt");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(sb.toString().trim());
            fileWriter.flush();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    /**
     * Collect the genres of the animes between low and high ratings
     * @param low
     * @param high
     */
    private static void collectGenres(int low, int high){
        File animeCsv = new File(ASSETS_PATH + "csv\\anime.csv");
        FileReader fileReader = null;

        try {
            fileReader = new FileReader(animeCsv);
            CSVReader csvReader = new CSVReader(fileReader);
            csvReader.readNext(); // read the first line (useless)
            String[] line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = csvReader.readNext()) != null){
                int membersNum = Integer.parseInt(line[6]);
                if (membersNum >= MIN_MEMBERS_NUM){
                    String ratingStr = line[5];
                    if (ratingStr.equals("")){
                        continue;
                    }
                    double rating = Double.parseDouble(ratingStr);
                    if (rating <= (double)high && rating >= (double)low){
                        String[] genres = line[2].split(", ");
                        for (String genre : genres){
                            genre = genre.trim().replace(" ", "-");
                            sb.append(genre + " ");
                        }
                        sb.append("\n");
                    }
                }
            }
            File file = new File(ASSETS_PATH + "genres" + low + "To" + high + ".txt");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(sb.toString().trim());
            fileWriter.flush();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
