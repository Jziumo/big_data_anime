import au.com.bytecode.opencsv.CSVReader;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class AnimeRecommend {
    private static final java.lang.String ASSETS_PATH = "assets\\";
    private static final int ID = 0;
    private static final int NAME = 1;
    private static final int RATING = 5;
    private static Map<Integer, String> idToName;
    private static Map<Integer, Double> idToRating;

    public static void main(String[] args) {
        init();

        File file = new File(ASSETS_PATH + "recommend\\animeByGenre");
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis, "utf-8"));
            String line = "";
            while ((line = br.readLine()) != null){
                recommend(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void init(){
        idToName = new HashMap<>();
        idToRating = new HashMap<>();

        File animeCsv = new File(ASSETS_PATH + "csv\\anime.csv");
        FileReader fileReader = null;

        try {
            fileReader = new FileReader(animeCsv);
            CSVReader csvReader = new CSVReader(fileReader);
            csvReader.readNext(); // read the first line (useless)
            String[] line = null;
            while ((line = csvReader.readNext()) != null){
                if (line[NAME].equals("") == false && line[RATING].equals("") == false){
                    idToName.put(Integer.parseInt(line[ID]), line[NAME]);
                    idToRating.put(Integer.parseInt(line[ID]), Double.parseDouble(line[RATING]));
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void recommend(String line){
        String genre = line.substring(0, line.indexOf("\t"));
        String idStr = line.substring(line.indexOf("\t")).trim();

        StringBuilder sb = new StringBuilder();
        sb.append(CSVCreator.getCSVLine(new String[]{"anime_name", "rating"}) + "\n");

        String[] ids = idStr.split("\\s+");
        for (String s : ids){
            int id = Integer.parseInt(s);
            if (idToName.containsKey(id) && idToRating.containsKey(id)){
                sb.append(CSVCreator.getCSVLine(new String[]{idToName.get(id), idToRating.get(id).toString()}));
                sb.append("\n");
            }
        }

        try {
            FileWriter fileWriter = new FileWriter(ASSETS_PATH + "recommend\\" + genre + ".csv");
            fileWriter.write(sb.toString());
            fileWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
